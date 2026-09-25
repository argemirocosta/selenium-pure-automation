package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Example 1: the finish text is already in the DOM, only hidden until loading ends.
 * Example 2: the finish text is inserted into the DOM only when loading ends.
 */
public class DynamicLoadingPage extends BasePage {

    private static final String PATH = "/dynamic_loading/";

    private static final By START_BUTTON = By.cssSelector("#start button");
    private static final By FINISH_TEXT = By.cssSelector("#finish h4");

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    public DynamicLoadingPage open(int example) {
        open(PATH + example);
        return this;
    }

    public DynamicLoadingPage start() {
        click(START_BUTTON);
        return this;
    }

    /**
     * Waits until the loading ends and the text becomes visible.
     */
    public String finishText() {
        return text(FINISH_TEXT);
    }

    // The two methods below read the current state without waiting

    public boolean isFinishTextInDom() {
        return !driver.findElements(FINISH_TEXT).isEmpty();
    }

    public boolean isFinishTextDisplayed() {
        List<WebElement> elements = driver.findElements(FINISH_TEXT);
        return !elements.isEmpty() && elements.getFirst().isDisplayed();
    }
}
