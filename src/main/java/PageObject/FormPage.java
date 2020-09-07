package PageObject;

import core.BaseFunc;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;

public class FormPage {

    private final By COMPANY_NAME = By.xpath(".//input[@name = 'company']");
    private final By FIRST_NAME = By.xpath(".//input[@name = 'name']");
    private final By LAST_NAME = By.xpath(".//input[@name = 'surname']");
    private final By PHONE_NR = By.xpath(".//input[@name = 'phone']");
    private final By EMAIL = By.xpath(".//input[@name = 'email']");
    private final By COMMENT = By.xpath(".//textarea[@name = 'comment']");
    private final By CHECKBOX = By.xpath(".//input[@type='checkbox']");
    private final By APPLY = By.xpath(".//a[@id='popupBtn2']");

    private BaseFunc baseFunc;
    private String url = "bizness.lmt.lv";

    public FormPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void checkUrl() {
        String currentUrl = baseFunc.getCurrentUrl();
        if (!currentUrl.contains(url)) {
            Assertions.fail("Url on page is incorrect.");
        }
    }

    public void companyName(String value) {
        baseFunc.waitForElements(COMPANY_NAME);
        baseFunc.fillInputField(COMPANY_NAME, value, true);
    }

    public void firstName(String value) {
        baseFunc.fillInputField(FIRST_NAME, value, true);
    }

    public void lastName(String value) {
        baseFunc.fillInputField(LAST_NAME, value, true);
    }

    public void phoneNr(String value) {
        baseFunc.fillInputField(PHONE_NR, value, true);
    }

    public void setEmail(String value) {
        baseFunc.fillInputField(EMAIL, value, true);
    }

    public void addComment(String value) {
        baseFunc.fillInputField(COMMENT, value, true);
    }

    public void clickCheckBox() {
        WebElement element = baseFunc.findElement(CHECKBOX);
        baseFunc.waitForElements(CHECKBOX);
        baseFunc.moveMouseToElementAndClick(element);
        baseFunc.waitSeconds();
        if (!element.isSelected()) {
            Assertions.fail("Checkbox wasn't checked");
        }

    }

    public void clickApply() {
        WebElement element = baseFunc.findElement(APPLY);
        try {
            element.click();
        } catch (UnhandledAlertException e) {
            baseFunc.getAlert();
            baseFunc.alertAccept();
        }
    }

}
