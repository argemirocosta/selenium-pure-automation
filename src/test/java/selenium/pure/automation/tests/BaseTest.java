package selenium.pure.automation.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import selenium.pure.automation.core.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @RegisterExtension
    final ScreenshotOnFailure screenshotOnFailure = new ScreenshotOnFailure(() -> driver);

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
