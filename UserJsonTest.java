package br.ce.fernanda.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class UserJsonTest {
	
	@Test
	public void deveVerificarPrimeiroNvel() {
		given()
		.when()
			.get("https://restapi.wcaquino.me/users/1")
		.then()
			.statusCode(200)
			.body("id", is(1))
			.body("name", containsString("Silva"))
			.body("name", containsString("João"))
			.body("age", greaterThan(18))
		;
		
	}
	
	@Test
	public void deveVerificarPrimeiroNvelOutrasFormas() {
		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");
		
		
		//path
		Assert.assertEquals(1, response.path("id"));
		
		//jsonpath
		JsonPath jpath = new JsonPath(response.asString());
		Assert.assertEquals(1, jpath.getInt("id"));
	}
	
	@Test
	public void deveVerificarSegundoNvel() {
		given()
		.when()
			.get("https://restapi.wcaquino.me/users/2")
		.then()
			.statusCode(200)
			.body("name", containsString("Joaquina"))
			.body("endereco.rua", is("Rua dos bobos"))
		;
		}
	
	@Test
	public void deveVerificarLista() {
		given()
		.when()
			.get("https://restapi.wcaquino.me/users/3")
		.then()
			.statusCode(200)
			.body("name", containsString("Ana"))
			.body("filhos", hasSize(2))
			.body("filhos[0].name", is("Zezinho"))
			.body("filhos[1].name", is("Luizinho"))
			;
			
	}
	
	@Test
	public void deveRetornarErroUsuarioInexistente() {
		given()
		.when()
			.get("https://restapi.wcaquino.me/users/4") //get = URL
		.then()
			.statusCode(404)
			.body("error", is("Usuário inexistente"))
			;
	}
	
	@Test
	public void deveVerificarListaRaiz() {
		given()
		.when()
			.get("https://restapi.wcaquino.me/users")
		.then()
			.statusCode(200)
			.body("$", hasSize(3)) //$ = toda a lista / hasSize = tamanho da lista
			.body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho")))
			;
		
	}
	
	@Test
	public void devoFazerVerificacoesAvancadas() {
		given()
		.when()
			.get("https://restapi.wcaquino.me/users")
		.then()
			.statusCode(200)
			.body("$", hasSize(3))
			.body("age.findAll{it <= 25}.size()", is(2)) //findAll = todos os elementos da lista
			.body("age.findAll{it <= 25 && it > 20}.size()", is(1))
			.body("findAll{it.age <= 25 && it.age > 20}.name", hasItem("Maria Joaquina"))
			.body("findAll{it.age <= 25}[0].name", is("Maria Joaquina")) // [0] = primeiro elemento da lista
			.body("find{it.age <= 25}.name", is("Maria Joaquina")) //find = unico registro
			.body("name.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA")) //collect e toUpperCase transforma lista em maiusculo
			.body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA"))
			.body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()", allOf(arrayContaining("MARIA JOAQUINA"), arrayWithSize(1)))//Array = lista
			.body("age.collect{it * 2}", hasItems(60, 50, 40))//Mustiplicar idade por 2
			.body("id.max()", is(3))
			.body("salary.min()", is(1234.5678f))
			;
		
	}
		@Test
		public void devoUnirJsonPathComJava() {
			ArrayList<String> names =
			given()
			.when()
				.get("https://restapi.wcaquino.me/users")
			.then()
				.statusCode(200)
				.extract().path("name.findAll{it.startsWith('Maria')}")
				
				;
		 Assert.assertEquals(1, names.size());
		 Assert.assertTrue(names.get(0).equalsIgnoreCase("mArIa Joaquina"));
		 Assert.assertEquals(names.get(0).toUpperCase(), "maria joaquina".toUpperCase());
		}
	

	
}
