package br.ce.fernanda.rest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.ce.fernanda.rest.classes.BaseTest;
import io.restassured.RestAssured;

public class BarrigaTest extends BaseTest {
	
	private String TOKEN;
	
	@Before
	public void login() {
	
		Map<String, String> login = new HashMap<String, String>();
		login.put("email", "fernanda@franco");
		login.put("senha", "123456");
		
		
	//Login na API e receber o TOKEN	
	TOKEN = given()
		.body(login)
	.when()
		.post("/signin")
	.then()
		.statusCode(200)
		.extract().path("token")
	
	;
		
	}
	
	@Test
	
public void naoDeveAcessarAPISemToken() {
		RestAssured.given()
		.when()
			.get("/contas")
		.then()
			.statusCode(401)
				;
	
	}

	@Test
	
public void deveIncluirContaComSucesso() {
		
			
		given()
			.header("Authorization", "JWT " + TOKEN)
			.body("{\"nome\": \"conta qualquer\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201)
			;		
	
			
	}	
	
	
	@Test
	
	public void deveAlterarContaComSucesso() {
			
			given()
				.header("Authorization", "JWT " + TOKEN)
				.body("{\"nome\": \"conta abc\"}")
			.when()
				.put("/contas/1264862")
			.then()
				.statusCode(200)
				;		
		
				
		}	
	
@Test
	
	public void deveIncluirContaComMesmoNome() {
			
			given()
				.header("Authorization", "JWT " + TOKEN)
				.body("{\"nome\": \"conta abc\"}")
			.when()
				.post("/contas")
			.then()
				.statusCode(400)
				;		
		
				
		}	
	
	
	
@Test

public void deveInserirMovimentacaoComSucesso() {
		
		given()
			.header("Authorization", "JWT " + TOKEN)
			.body("{\"nome\": \"conta abc\"}")
		.when()
			.post("/transacoes")
		.then()
			.statusCode(201)
			;		
	
			
	}	
	
	
	
}


