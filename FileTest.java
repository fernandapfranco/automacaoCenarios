package br.ce.fernanda.rest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.hamcrest.Matchers;
import org.junit.Test;

public class FileTest {
	
	@Test
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
	
	@Test
	public void deveFazerUploadDoArquivo( ) {
		given()
		.log().all()
		.multiPart("arquivo", new File("src/main/resources/users.pdf"))
	.when()
		.post("http://restapi.wcaquino.me/upload")
	.then()
	.log().all()
	.statusCode(200) 
		;
			
		
	}
	
	@Test
	public void deveBaixarArquivo( ) throws IOException {
		byte[] image = given()
		.log().all()
		.when()
		.get("https://restapi.wcaquino.me/download")
	.then()
//	.log().all()
	.statusCode(200) 
	.extract().asByteArray()
		;
		
		File imagem = new File("src/main/resources/file.jpg");
		OutputStream out = new FileOutputStream(imagem);
		out.write(image);
		out.close();
		
		System.out.println(imagem.length());
		
		
		
		
		
		
		
		
		
		
		
	}

}
