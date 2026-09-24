package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pure.automation.components.FlashMessage;

public class SecureAreaPage extends BasePage {

    private static final By HEADING = By.tagName("h2");
    private static final By LOGOUT_BUTTON = By.cssSelector("a[href='/logout']");

    private final FlashMessage flashMessage;

    public SecureAreaPage(WebDriver driver) {
        super(driver);
        this.flashMessage = new FlashMessage(driver);
    }

    public LoginPage logout() {
        clickAndWaitForNavigation(LOGOUT_BUTTON);
        return new LoginPage(driver);
    }

    public String heading() {
        return text(HEADING);
    }

    public FlashMessage flashMessage() {
        return flashMessage;
    }
}
