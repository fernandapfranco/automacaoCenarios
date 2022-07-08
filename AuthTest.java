package br.ce.fernanda.rest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class AuthTest {
	
	@Test
	public void deveAcessarSWAPI( ) {
		given()
		.log().all()
	.when()
		.get("https://swapi.dev/api/people/1/")
	.then()
	.log().all()
	.statusCode(200) 
	.body("name", Matchers.is("Luke Skywalker"))
	;
			
		
	}
	
	//762d1c7104b3a6455130e2fae2dff535
	//https://api.openweathermap.org/data/2.5/weather?q=Salvador,BR&appid=762d1c7104b3a6455130e2fae2dff535&units=metric

	@Test
	public void deveObterClima( ) {
		given()
		.log().all()
		.queryParam("q", "Salvador,BR")
		.queryParam("appid", "762d1c7104b3a6455130e2fae2dff535")
		.queryParam("units",  "metric")
	.when()
		.get("https://api.openweathermap.org/data/2.5/weather")
	.then()
	.log().all()
	.statusCode(200) 
	
	;
			
		
	}
	
	@Test
	public void deveFazerAutenticacaoComToken( ) {
		Map<String, String> login = new HashMap<String, String>();
		login.put("email", "fernanda@franco");
		login.put("senha", "123456");
		
		
	//Login na API e receber o TOKEN	
	String token = given()
		.log().all()
		.body(login)
		.contentType(ContentType.JSON)
	.when()
		.post("https://barrigarest.wcaquino.me/signin")
	.then()
		.log().all()
		.statusCode(200)
		.extract().path("token")
	
	;
	
	//OBTER AS CONTAS
		given()
			.log().all()
			.header("Authorization", "JWT " + token)
		.when()
			.get("https://barrigarest.wcaquino.me/contas")
		.then()
			.log().all()
			.statusCode(200)
					
		;		
		
	}

	
	
}
