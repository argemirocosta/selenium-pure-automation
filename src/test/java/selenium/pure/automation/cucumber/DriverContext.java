package selenium.pure.automation.cucumber;

import org.openqa.selenium.WebDriver;
import selenium.pure.automation.core.DriverFactory;

/**
 * Holds the driver of the running scenario. PicoContainer creates one instance per scenario
 * and injects the same instance into the hooks and into every step class.
 */
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
