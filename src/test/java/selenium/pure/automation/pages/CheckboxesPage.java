package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckboxesPage extends BasePage {

    private static final String PATH = "/checkboxes";

    private static final By CHECKBOXES = By.cssSelector("#checkboxes input[type='checkbox']");

    public CheckboxesPage(WebDriver driver) {
        super(driver);
    }

    public CheckboxesPage open() {
        open(PATH);
        return this;
    }

    /**
     * @param position 1 for the first checkbox, 2 for the second
     */
    public boolean isChecked(int position) {
        return checkbox(position).isSelected();
    }

    public CheckboxesPage toggle(int position) {
        checkbox(position).click();
        return this;
    }

    private WebElement checkbox(int position) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CHECKBOXES)).get(position - 1);
    }
}
