package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewWindowPage extends BasePage {

    private static final String PATH = "/windows/new";

    private static final By HEADING = By.tagName("h3");

    private final String originalWindow;

    public NewWindowPage(WebDriver driver, String originalWindow) {
        super(driver);
        this.originalWindow = originalWindow;
    }

    public NewWindowPage open() {
        open(PATH);
        return this;
    }

    public WindowsPage close() {
        driver.close();
        driver.switchTo().window(originalWindow);
        return new WindowsPage(driver);
    }

    public String heading() {
        return text(HEADING);
    }
}
