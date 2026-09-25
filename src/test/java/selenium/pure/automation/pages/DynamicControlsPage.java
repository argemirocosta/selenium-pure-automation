package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicControlsPage extends BasePage {

    private static final String PATH = "/dynamic_controls";

    private static final By CHECKBOX = By.cssSelector("#checkbox-example input[type='checkbox']");
    // The same button toggles between "Remove" and "Add"
    private static final By CHECKBOX_BUTTON = By.cssSelector("#checkbox-example button");
    private static final By CHECKBOX_MESSAGE = By.cssSelector("#checkbox-example #message");

    private static final By INPUT = By.cssSelector("#input-example input[type='text']");
    private static final By INPUT_BUTTON = By.cssSelector("#input-example button");
    private static final By INPUT_MESSAGE = By.cssSelector("#input-example #message");

    public DynamicControlsPage(WebDriver driver) {
        super(driver);
    }

    public DynamicControlsPage open() {
        open(PATH);
        return this;
    }

    public DynamicControlsPage removeCheckbox() {
        click(CHECKBOX_BUTTON);
        // Also satisfied when the element no longer exists in the DOM
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CHECKBOX));
        return this;
    }

    public DynamicControlsPage addCheckbox() {
        click(CHECKBOX_BUTTON);
        waitVisible(CHECKBOX);
        return this;
    }

    public boolean isCheckboxPresent() {
        return !driver.findElements(CHECKBOX).isEmpty();
    }

    public String checkboxMessage() {
        return text(CHECKBOX_MESSAGE);
    }

    public DynamicControlsPage enableInput() {
        click(INPUT_BUTTON);
        // The input is visible the whole time; "clickable" means visible AND enabled
        wait.until(ExpectedConditions.elementToBeClickable(INPUT));
        return this;
    }

    public DynamicControlsPage typeInInput(String text) {
        type(INPUT, text);
        return this;
    }

    public boolean isInputEnabled() {
        return waitVisible(INPUT).isEnabled();
    }

    public String inputValue() {
        return waitVisible(INPUT).getDomProperty("value");
    }

    public String inputMessage() {
        return text(INPUT_MESSAGE);
    }
}
