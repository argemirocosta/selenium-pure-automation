package selenium.pure.automation.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Creates WebDriver instances. Driver binaries (and the browser itself, if missing)
 * are resolved by Selenium Manager, bundled with Selenium.
 */
public final class DriverFactory {

    private static final Dimension WINDOW_SIZE = new Dimension(1920, 1080);

    private DriverFactory() {
    }

    public static WebDriver create() {
        WebDriver driver = switch (Config.browser()) {
            case CHROME -> new ChromeDriver(chromeOptions());
            case FIREFOX -> new FirefoxDriver(firefoxOptions());
        };
        // Same size in headed and headless mode, so layouts behave the same
        driver.manage().window().setSize(WINDOW_SIZE);
        return driver;
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (Config.headless()) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (Config.headless()) {
            options.addArguments("-headless");
        }
        return options;
    }
}
