package selenium.pure.automation.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Cucumber equivalent of BaseTest: one browser per scenario.
 */
public class Hooks {

    private final DriverContext context;

    public Hooks(DriverContext context) {
        this.context = context;
    }

    @Before
    public void startDriver() {
        context.start();
    }

    @After
    public void quitDriver() {
        context.quit();
    }
}
