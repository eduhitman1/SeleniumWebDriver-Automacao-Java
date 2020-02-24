package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import static org.junit.Assert.*;

@Epic("Adicionar Informações do Usuário")
@Feature("Adicionar Informações do Usuári")
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")
public class InformacoesUsuarioPageObjectsTest {

	private WebDriver navegador;

	@Before
	public void setUp() { 
		navegador = Web.createChrome();
	}

	@Test
    @Story("Usuário Adiciona contato")
    @Description("Usuário adiciona contato com sucesso")
	public void testAdicionarUmaInformacaoAdicionalDoUsuario(
			@Param(name = "login") String login,
			@Param(name = "senha") String senha, 
			@Param(name = "tipo") String tipo,
			@Param(name = "contato") String contato, 
			@Param(name = "mensagem") String mensagemEsperada
			) {
		String textoToast = new LoginPage(navegador).clicarSignIn()
				.fazerLogin(login, senha).clicarMe()
				.clicarAbaMoreDataAboutYou().clicarBotaoAddMoreDataAboutYou().adicionarContato(tipo, contato)
				.capturarTextoToast();
		assertEquals(mensagemEsperada, textoToast); 
	}

	@After
	public void tearDown() {
		navegador.quit();
	}
}
