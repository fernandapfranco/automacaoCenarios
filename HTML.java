package br.ce.fernanda.rest;

import org.junit.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;



public class HTML {
	
	@Test
	
	public void deveFazerBuscasComHTML() {
		
		given()
		.log().all()
	.when()
		.get("https://restapi.wcaquino.me/v2/users")
	.then()
		.log().all()
		.statusCode(200)
		.contentType(ContentType.HTML)
	;
			
	}

}
