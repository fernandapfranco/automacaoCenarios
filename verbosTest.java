package br.ce.fernanda.rest;

import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class verbosTest {
	@Test
	public void deveSalvarUsuario() {
		given()
			.log().all()
			.contentType("application/json")
			.body("{\"name\": \"Jose\", \"age\": 50}")
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
;
}
	
	@Test
	public void naoDeveSalvarUsuarioSemNome() {
	given()
		.log().all()
		.contentType("application/json")
		.body("{\"age\": 50}")
	.when()
		.post("https://restapi.wcaquino.me/users")
	.then()
		.log().all()
		.statusCode(400)
		.body("id", is(nullValue()))
		.body("error", is("Name é um atributo obrigatório"))
;
	
	}
	
	@Test
	public void deveAlterarUsuario() {
	given()
		.log().all()
		.contentType("application/json")
		.body("{\"name\": \"Usuario alterado\",\"age\": 80}")
	.when()
		.put("https://restapi.wcaquino.me/users/1")
	.then()
		.log().all()
//		.statusCode(400)
//		.body("id", is(nullValue()))
//		.body("error", is("Name é um atributo obrigatório"))
;
	
	}
	
	
	@Test
	public void devoCustomizarURL() {
	given()
		.log().all()
		.contentType("application/json")
		.body("{\"name\": \"Usuario alterado\",\"age\": 80}")
	.when()
		.put("https://restapi.wcaquino.me/{entidade}/{userId}", "users", "1")
	.then()
		.log().all()
		.statusCode(200)
		//.body("id", is(nullValue()))
		//.body("error", is("Name é um atributo obrigatório"))
;
	
	}
	
	@Test
	public void devoCustomizarURLParte2() {
	given()
		.log().all()
		.contentType("application/json")
		.body("{\"name\": \"Usuario alterado\",\"age\": 80}")
		.pathParam("entidade", "users")
		.pathParam("userId", 1)
	.when()
		.put("https://restapi.wcaquino.me/{entidade}/{userId}")
	.then()
		.log().all()
		.statusCode(200)
		//.body("id", is(nullValue()))
		//.body("error", is("Name é um atributo obrigatório"))
;
	
	}
	
	@Test
	public void deveRemoverUsuario() {
	given()
		.log().all()
		.contentType("application/json")
	.when()
		.delete("https://restapi.wcaquino.me/users/1")
	.then()
		.log().all()
		//.statusCode(200)
		//.body("id", is(nullValue()))
		//.body("error", is("Name é um atributo obrigatório"))
;
	
	}
	
	@Test
	public void deveSalvarUsuarioUsandoMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Usuario via map");
		params.put("age", 25);
		
		given()
			.log().all()
			.contentType("application/json")
			.body(params)
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Usuario via map"))
			.body("age", is(25))
;
}
	
	
	@Test
	public void deveSalvarUsuarioUsandoObjeto() {
		Users user = new Users("Usuario via objeto", 35);
		
		given()
			.log().all()
			.contentType("application/json")
			.body(user)
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Usuario via objeto"))
			.body("age", is(35))
;
}
	
	@Test
	public void deveDeseralizarObjetoSalvarUsuario() {
		Users user = new Users("Usuario Deseralizado", 35);
		
		Users usuarioInserido = given()
			.log().all()
			.contentType("application/json")
			.body(user)
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.extract().body().as(Users.class)
;
		
		System.out.print(usuarioInserido);
		Assert.assertThat(usuarioInserido.getId(), notNullValue());
		Assert.assertEquals("Usuario Deseralizado", usuarioInserido.getName());
		Assert.assertThat(usuarioInserido.getAge(), is(35));
		
}
	
	
}

