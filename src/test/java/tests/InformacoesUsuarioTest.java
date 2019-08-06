package tests;


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InformacoesUsuarioTest {
    private WebDriver navegador;

	@Before
	public void setUp() {
        // Abrindo o navegador
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eduardo\\Documents\\Softwares & Servidores\\CHROMEDRIVER\\chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Navegador para a página do Taskit!
		navegador.get("http://www.juliodelima.com.br/taskit/");
	}
	
	
	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        // Clicar no link que possui o texto "Sign in"
		navegador.findElement(By.linkText("Sign in")).click();
		/*
		 * WebElement linkSignIn = navegador.findElement(By.linkText("Sign in"));
		 * linkSignIn.click();
		 */

		// Identificando o formulário de login
		WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

		// Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o texto "julio0001"
		formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

		// Digitar no campo com name "password" que está dentro do formulário de id "signinbox" o texto "123456"
		formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

		// Clicar no link com texto "SIGN IN"
		navegador.findElement(By.linkText("SIGN IN")).click();

		// Clicar em um link que possuir a class "me"
		navegador.findElement(By.className("me")).click();
		
		// Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
		navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
		
		// Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
		navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();;
		
		// Identificar o popup onde está o formulário de id addmoredata
		WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));
		
		// Na combo de name "type" escolhe a opção "Phone" ou "email"
		WebElement campoType = popupAddMoreData.findElement(By.name("type"));
		new Select(campoType).selectByVisibleText("E-mail");
		
		// No campo de name "contact" digitar "+55963894322" // digitar "edu.hitman01.udemy01@gmail.com"
        popupAddMoreData.findElement(By.name("contact")).sendKeys("edu.hitman01.udemy02@gmail.com");
		
		
		// Clicar no link de text "SAVE" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();
        
        
		// Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
		
		
//		// Validar que dentro do elemento com class "me" está o texto "Hi, Julio"
//		WebElement me = navegador.findElement(By.className("me"));
//		String textoNoElemento = me.getText();
////		assertEquals("Hi, Julio", textoNoElemento);
}   
	

	
//	@After
//	public void tearDown() {
//		// Fechar o navegador
//		// navegador.aquit(); FECHA TODAS AS ABAS
//	     navegador.close();
//	}
}
