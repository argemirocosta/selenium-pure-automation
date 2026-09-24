package selenium.pure.automation.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import selenium.pure.automation.core.DriverFactory;

/**
 * One browser per test: created before each test and closed after it.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void startDriver() {
        driver = DriverFactory.create();
    }

    @AfterEach
    void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
