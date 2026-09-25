package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddRemoveElementsPage extends BasePage {

    private static final String PATH = "/add_remove_elements/";

    private static final By ADD_BUTTON = By.cssSelector("button[onclick='addElement()']");
    private static final By DELETE_BUTTONS = By.cssSelector("#elements .added-manually");

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }

    public AddRemoveElementsPage open() {
        open(PATH);
        return this;
    }

    public AddRemoveElementsPage addElements(int quantity) {
        for (int i = 0; i < quantity; i++) {
            click(ADD_BUTTON);
        }
        return this;
    }

    /**
     * @param position 1 for the first delete button, 2 for the second, and so on
     */
    public AddRemoveElementsPage removeElement(int position) {
        driver.findElements(DELETE_BUTTONS).get(position - 1).click();
        return this;
    }

    public int elementCount() {
        // findElements returns an empty list when nothing matches;
        // findElement would throw NoSuchElementException instead
        return driver.findElements(DELETE_BUTTONS).size();
    }
}
