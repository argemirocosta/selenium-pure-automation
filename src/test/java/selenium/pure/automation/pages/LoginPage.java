package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pure.automation.components.FlashMessage;

public class LoginPage extends BasePage {

    private static final String PATH = "/login";

    private static final By HEADING = By.tagName("h2");
    private static final By USERNAME = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");

    private final FlashMessage flashMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.flashMessage = new FlashMessage(driver);
    }

    public LoginPage open() {
        open(PATH);
        return this;
    }

    public SecureAreaPage loginAs(String username, String password) {
        submit(username, password);
        return new SecureAreaPage(driver);
    }

    public LoginPage loginWithInvalidCredentials(String username, String password) {
        submit(username, password);
        return this;
    }

    public String heading() {
        return text(HEADING);
    }

    public FlashMessage flashMessage() {
        return flashMessage;
    }

    private void submit(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        clickAndWaitForNavigation(LOGIN_BUTTON);
    }
}
