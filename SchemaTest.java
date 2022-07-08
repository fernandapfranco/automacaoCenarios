package br.ce.fernanda.rest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

public class SchemaTest {
	
	
	public void deveObrigarEnvioArquivo( ) {
		given()
		.log().all()
	.when()
		.post("https://restapi.wcaquino.me/upload")
	.then()
	.log().all()
	.statusCode(404) //Deveria ser 400
	.body("error", Matchers.is("Arquivo não enviado"))
	;
			
		
	}

}
