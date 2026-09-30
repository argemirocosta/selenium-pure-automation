package selenium.pure.automation.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import selenium.pure.automation.core.Screenshots;

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
    public void quitDriver(Scenario scenario) {
        try {
            if (scenario.isFailed() && context.driver() != null) {
                Screenshots.save(context.driver(), scenario.getName());
            }
        } finally {
            context.quit();
        }
    }
}
