package selenium.pure.automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pure.automation.core.Config;

/**
 * The green (success) or red (error) notification shown at the top of several pages.
 */
public class FlashMessage {

    private static final By FLASH = By.id("flash");
    private static final String CLOSE_ICON = "×";

    private final WebDriverWait wait;

    public FlashMessage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Config.timeout());
    }

    public String text() {
        // The element text includes the close icon, e.g. "You logged into a secure area!\n×"
        return element().getText().replace(CLOSE_ICON, "").trim();
    }

    public boolean isSuccess() {
        return hasClass("success");
    }

    public boolean isError() {
        return hasClass("error");
    }

    private boolean hasClass(String cssClass) {
        return element().getDomAttribute("class").contains(cssClass);
    }

    private WebElement element() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FLASH));
    }
}
