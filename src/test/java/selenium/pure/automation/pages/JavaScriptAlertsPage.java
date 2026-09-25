package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaScriptAlertsPage extends BasePage {

    private static final String PATH = "/javascript_alerts";

    private static final By ALERT_BUTTON = By.cssSelector("button[onclick='jsAlert()']");
    private static final By CONFIRM_BUTTON = By.cssSelector("button[onclick='jsConfirm()']");
    private static final By PROMPT_BUTTON = By.cssSelector("button[onclick='jsPrompt()']");
    private static final By RESULT = By.id("result");

    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    public JavaScriptAlertsPage open() {
        open(PATH);
        return this;
    }

    public JavaScriptAlertsPage openAlert() {
        click(ALERT_BUTTON);
        return this;
    }

    public JavaScriptAlertsPage openConfirm() {
        click(CONFIRM_BUTTON);
        return this;
    }

    public JavaScriptAlertsPage openPrompt() {
        click(PROMPT_BUTTON);
        return this;
    }

    public String popupText() {
        return waitForAlert().getText();
    }

    // After accept or dismiss the popup closes and the driver is back on the page;
    // no switch back is needed (unlike frames and windows)

    public JavaScriptAlertsPage accept() {
        waitForAlert().accept();
        return this;
    }

    public JavaScriptAlertsPage dismiss() {
        waitForAlert().dismiss();
        return this;
    }

    public JavaScriptAlertsPage typeInPrompt(String text) {
        waitForAlert().sendKeys(text);
        return this;
    }

    public String result() {
        return text(RESULT);
    }
}
