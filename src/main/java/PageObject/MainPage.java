package PageObject;

import core.BaseFunc;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class MainPage {

    private final By LOGO = xpath(".//li[@class='nav-item scroll has-logo']");
    private final By FUNKCIJAS = xpath(".//li[@data-id='funkcijas']");
    private final By MENU_ITEMS = xpath(".//li[@class='nav-item scroll']");
    private final By APPLY_BUTTON = xpath(".//div[@class='col content']//div//a");

    private BaseFunc baseFunc;

    public MainPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectMenuItem(String menuItem) {
        List<WebElement> elements = baseFunc.findElements(MENU_ITEMS);
        for (WebElement element : elements) {
            String elementName = element.getText();
            if ((elementName.equals(menuItem))) {
                baseFunc.moveMouseToElementAndClick(element);
                break;
            }
        }
    }

    public void clickApplyById() {
        try {

            List<WebElement> elements = baseFunc.findElements(APPLY_BUTTON);
            baseFunc.waitForElements(APPLY_BUTTON);
            baseFunc.waitForElementToBeClickable(elements, "pieteikties");

        } catch (ElementClickInterceptedException e) {
            Assertions.fail("Apply button is not clickable.");
        }

    }

    public void clickFunctions() {
        baseFunc.findElements(FUNKCIJAS).get(1).click();
    }

    public void clickLogo() {
        baseFunc.findElements(LOGO).get(1).click();
    }

}
