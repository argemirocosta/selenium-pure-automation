package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropdownPage extends BasePage {

    private static final String PATH = "/dropdown";

    private static final By DROPDOWN = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    public DropdownPage open() {
        open(PATH);
        return this;
    }

    public DropdownPage select(String option) {
        dropdown().selectByVisibleText(option);
        return this;
    }

    public String selectedOption() {
        return dropdown().getFirstSelectedOption().getText();
    }

    public List<String> options() {
        return dropdown().getOptions().stream()
                .map(WebElement::getText)
                .toList();
    }

    // Select wraps a <select> element and exposes the dropdown-specific operations
    private Select dropdown() {
        return new Select(waitVisible(DROPDOWN));
    }
}
