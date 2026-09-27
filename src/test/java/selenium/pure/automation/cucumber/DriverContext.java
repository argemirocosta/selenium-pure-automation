package selenium.pure.automation.cucumber;

import org.openqa.selenium.WebDriver;
import selenium.pure.automation.core.DriverFactory;

public class DriverContext {

    private WebDriver driver;

    public void start() {
        driver = DriverFactory.create();
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver driver() {
        return driver;
    }
}
