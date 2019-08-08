package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import suporte.Generator;
import suporte.Screenshot;



@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")
public class InformacoesUsuarioTest {
	private WebDriver navegador;
    
	@Rule
	public TestName test = new TestName();
	
	
	@Before
	public void setUp() {
		// Abrindo o navegador
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Eduardo\\Documents\\Softwares & Servidores\\CHROMEDRIVER\\chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Navegador para a página do Taskit!
		navegador.get("http://www.juliodelima.com.br/taskit/");

		// Clicar no link que possui o texto "Sign in"
		navegador.findElement(By.linkText("Sign in")).click();
		/*
		 * WebElement linkSignIn = navegador.findElement(By.linkText("Sign in"));
		 * linkSignIn.click();
		 */
		// Identificando o formulário de login
		WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

		// Digitar no campo com name "login" que está dentro do formulário de id
		// "signinbox" o texto "julio0001"
		formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

		// Digitar no campo com name "password" que está dentro do formulário de id
		// "signinbox" o texto "123456"
		formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

		// Clicar no link com texto "SIGN IN"
		navegador.findElement(By.linkText("SIGN IN")).click();

		// Clicar em um link que possuir a class "me"
		navegador.findElement(By.className("me")).click();

		// Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
		navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
	}


	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {

		// Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
		navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

		// Identificar o popup onde está o formulário de id addmoredata
		WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

		// Na combo de name "type" escolhe a opção "Phone" ou "email"
		WebElement campoType = popupAddMoreData.findElement(By.name("type"));
		new Select(campoType).selectByVisibleText(tipo);

		// No campo de name "contact" digitar "+55963894322" // digitar "edu.hitman01.udemy01@gmail.com"
		popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

		// Clicar no link de text "SAVE" que está na popup
		popupAddMoreData.findElement(By.linkText("SAVE")).click();

		// Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
		WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
		String mensagem = mensagemPop.getText(); // Capturando text do mensagemPop
		assertEquals(mensagemEsperada, mensagem); // Comparação de mensagemPop

//		// Validar que dentro do elemento com class "me" está o texto "Hi, Julio"
//		WebElement me = navegador.findElement(By.className("me"));
//		String textoNoElemento = me.getText();
//		assertEquals("Hi, Julio", textoNoElemento);
	}


//  @Test
	public void removerUmContatoDeUmUsuario() {

		// Clicar no elemento pelo seu xpath //span[text()="+12341234"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+5511999999945\"]/following-sibling::a")).click();
		
		// Confirma a janela javascript
        navegador.switchTo().alert().accept();
        
		// Validar que a mensagem apresentada foi "Rest in peace, dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
		String mensagem = mensagemPop.getText(); // Capturando text do mensagemPop
		assertEquals("Rest in peace, dear phone!", mensagem); // Comparação de mensagemPop
		
		//TIRAR PRINT MAS APRESENTA ERRO NA CLASS Screenshot
		String screenshotArquivo = "C:\\Users\\Eduardo\\" + Generator.dataHoraParaArquivo()+ test.getMethodName()+".png";
		Screenshot.tirar(navegador, screenshotArquivo);
		
		// Aguarda até 10 segundos para que a janela desapareça
		WebDriverWait aguardar = new WebDriverWait(navegador, 10);
		aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
		
		// Clicar no link  com  texto "logout "
        navegador.findElement(By.linkText("Logout")).click();
	}

	@After
	public void tearDown() {
		// Fechar o navegador
		// navegador.aquit(); FECHA TODAS AS ABAS
		navegador.close();
	}
}
