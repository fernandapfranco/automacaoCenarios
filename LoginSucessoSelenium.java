package br.fer.loginsucesso.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;




public class LoginSucessoSelenium {

@Test

public void loginSucesso() {

	//Configuracao ChromeDriver
	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chrome driver\\chromedriver_win32\\chromedriver.exe");
	
	//Criado Objeto chromedriver
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	//deletar cookies
	driver.manage().deleteAllCookies();
	
	//URL especificado
	driver.get("https://www.saucedemo.com/");
	
	//Localizar campo de login
	
	driver.findElement(By.id("user-name"));
	WebElement username=driver.findElement(By.id("user-name"));
	
	
	//Localizar campo de senha
	driver.findElement(By.id("password"));
	WebElement password=driver.findElement(By.id("password"));
	
	//login
	WebElement login = driver.findElement(By.name("login-button"));
	
	//Chaves
	
	username.sendKeys("standard_user");
	password.sendKeys("secret_sauce");
	login.click();
	
	/////////////////////////////////////////////////
	
	//resultados
	
	String actualUrl = "https://www.saucedemo.com/inventory.html";
	String expectedUrl= driver.getCurrentUrl();
	Assert.assertEquals(expectedUrl, actualUrl);

}

@Test
public void loginFalhaSenhaIncorreta() {

	//Configuracao ChromeDriver
	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chrome driver\\chromedriver_win32\\chromedriver.exe");
	
	//Criado Objeto chromedriver
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	//deletar cookies
	driver.manage().deleteAllCookies();
	
	//URL especificado
	driver.get("https://www.saucedemo.com/");
	
	//Localizar campo de login
	
	driver.findElement(By.id("user-name"));
	WebElement username=driver.findElement(By.id("user-name"));
	
	
	//Localizar campo de senha
	driver.findElement(By.id("password"));
	WebElement password=driver.findElement(By.id("password"));
	
	//login
	WebElement login = driver.findElement(By.name("login-button"));
	
	//Chaves
	
	username.sendKeys("standard_user");
	password.sendKeys("almondega");
	login.click();
	
	/////////////////////////////////////////////////
	
	//resultados
	
	String actualUrl = "https://www.saucedemo.com/inventory.html";
	String expectedUrl= driver.getCurrentUrl();
	Assert.assertEquals(expectedUrl, actualUrl);

}

@Test
public void loginFalhaUsuarioIncorreto() {

	//Configuracao ChromeDriver
	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chrome driver\\chromedriver_win32\\chromedriver.exe");
	
	//Criado Objeto chromedriver
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	//deletar cookies
	driver.manage().deleteAllCookies();
	
	//URL especificado
	driver.get("https://www.saucedemo.com/");
	
	//Localizar campo de login
	
	driver.findElement(By.id("user-name"));
	WebElement username=driver.findElement(By.id("user-name"));
	
	
	//Localizar campo de senha
	driver.findElement(By.id("password"));
	WebElement password=driver.findElement(By.id("password"));
	
	//login
	WebElement login = driver.findElement(By.name("login-button"));
	
	//Chaves
	
	username.sendKeys("alfajor_doce");
	password.sendKeys("secret_sauce");
	login.click();
	
	/////////////////////////////////////////////////
	
	//resultados
	
	String actualUrl = "https://www.saucedemo.com/inventory.html";
	String expectedUrl= driver.getCurrentUrl();
	Assert.assertEquals(expectedUrl, actualUrl);
	
}

}
