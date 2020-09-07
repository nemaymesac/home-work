import PageObject.FormPage;
import PageObject.MainPage;
import core.BaseFunc;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EdlusTest {

    private final String URL = "https://edlus.lmt.lv/";
    BaseFunc baseFunc = new BaseFunc();

    @BeforeAll
    void openBrowser() {
        baseFunc.navigateTo(URL);

    }

    @Test
    public void pageTest() throws InterruptedException {

        MainPage mainPage = new MainPage(baseFunc);
        mainPage.clickFunctions();
        baseFunc.waitSeconds();

        mainPage.clickLogo();
        Thread.sleep(1000);

        mainPage.clickApplyById();
        baseFunc.switchWindow();

        FormPage formPage = new FormPage(baseFunc);
        formPage.checkUrl();
        formPage.companyName("test");
        formPage.firstName("test");
        formPage.lastName("test");
        formPage.phoneNr("12345678");
        formPage.setEmail("test");
        formPage.addComment("test");
        formPage.clickCheckBox();
        formPage.clickApply();

    }

    @BeforeAll
    void closeBrowser() {
        baseFunc.closeBrowser();

    }

}
