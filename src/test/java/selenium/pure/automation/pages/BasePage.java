package selenium.pure.automation.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pure.automation.core.Config;

/**
 * Common behavior for all pages. Every interaction goes through an explicit wait.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Config.timeout());
    }

    protected void open(String path) {
        driver.get(Config.baseUrl() + path);
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Clicks an element that loads a new page and waits until the old page is gone.
     * Without this, the next lookup could still find elements of the previous page.
     */
    protected void clickAndWaitForNavigation(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        // While the page is being replaced, Chrome may answer with a generic WebDriverException
        // ("Node with given id does not belong to the document") instead of a stale element error.
        // A dedicated wait ignores it and keeps polling until the element is reported as stale.
        new WebDriverWait(driver, Config.timeout())
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.stalenessOf(element));
    }

    protected void type(By locator, String text) {
        WebElement element = waitVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String text(By locator) {
        return waitVisible(locator).getText();
    }

    /**
     * Waits for a native JavaScript popup (alert, confirm or prompt) and switches to it.
     * Popups are not part of the DOM, so findElement cannot reach them.
     */
    protected Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
