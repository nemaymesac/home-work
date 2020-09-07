package core;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseFunc {

    private WebDriver driver;
    private WebDriverWait wait;
    Actions actions;


    public BaseFunc() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 10);

    }

    public void navigateTo(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        driver.get(url);
    }

    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void waitSeconds(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void switchWindow() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.close();
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(1000);
    }

    public void waitForElementToBeClickable(List<WebElement> elements, String name) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        for (WebElement element : elements) {
            if (element.getText().toLowerCase().contains(name)) {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;
            }

        }
    }

    public void waitForElements(By locator) {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement findElement(By locator) {
        Assertions.assertFalse(findElements(locator).isEmpty(), "");
        return driver.findElement(locator);
    }

    public void moveMouseToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void moveMouseToElementAndClick(WebElement element) {
        actions.moveToElement(element).click().build().perform();
    }

    public void clickOnElementByName(List<WebElement> elements, String name) {
        for (WebElement element : elements) {
            if (element.getText().toLowerCase().contains(name)) {
                waitSeconds();
                element.click();
                break;
            }
        }
    }

    public void clickIfEnabled(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        if (!element.isEnabled()) {
            Assertions.fail("Element is disabled.");
        } else {
            element.click();
        }

    }

    public void fillInputField (By locator, String value, boolean clear){
        WebElement inputText = findElement(locator);
        if (clear) {
            inputText.clear();
        }
        inputText.sendKeys(value);
    }

    public void getAlert() {
        String message = driver.switchTo().alert().getText();
        System.out.println(message);
    }

    public void alertAccept() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void scrollToElement(WebElement element) {
        actions.moveToElement(element);
        try {
            actions.perform();
        } catch (Exception ex) {
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scroll() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
