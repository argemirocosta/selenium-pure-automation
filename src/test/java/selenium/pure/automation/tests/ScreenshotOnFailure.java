package selenium.pure.automation.tests;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import selenium.pure.automation.core.Screenshots;

import java.util.function.Supplier;

public class ScreenshotOnFailure implements AfterTestExecutionCallback {

    private final Supplier<WebDriver> driver;

    public ScreenshotOnFailure(Supplier<WebDriver> driver) {
        this.driver = driver;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        WebDriver current = driver.get();
        if (context.getExecutionException().isPresent() && current != null) {
            String name = context.getRequiredTestClass().getSimpleName() + "_" + context.getRequiredTestMethod().getName();
            Screenshots.save(current, name);
        }
    }
}
