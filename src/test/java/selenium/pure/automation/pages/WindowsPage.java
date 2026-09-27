package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WindowsPage extends BasePage {

    private static final String PATH = "/windows";

    private static final By HEADING = By.tagName("h3");
    private static final By NEW_WINDOW_LINK = By.linkText("Click Here");

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    public WindowsPage open() {
        open(PATH);
        return this;
    }

    public NewWindowPage openNewWindow() {
        String originalWindow = driver.getWindowHandle();
        click(NEW_WINDOW_LINK);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String newWindow = driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(originalWindow))
                .findFirst()
                .orElseThrow();
        driver.switchTo().window(newWindow);

        return new NewWindowPage(driver, originalWindow);
    }

    public NewWindowPage openNewTab() {
        String originalWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        return new NewWindowPage(driver, originalWindow).open();
    }

    public String heading() {
        return text(HEADING);
    }
}
