package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class InfiniteScrollPage extends BasePage {

    private static final String PATH = "/infinite_scroll";

    private static final By LOADED_BLOCKS = By.cssSelector(".jscroll-added");
    private static final By LOADING = By.cssSelector(".jscroll-loading");

    public InfiniteScrollPage(WebDriver driver) {
        super(driver);
    }

    public InfiniteScrollPage open() {
        open(PATH);
        wait.until(d -> blockCount() > 0 && isIdle());
        return this;
    }

    public InfiniteScrollPage scrollToBottom() {
        int before = blockCount();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        wait.until(d -> blockCount() > before && isIdle());
        return this;
    }

    public int blockCount() {
        return driver.findElements(LOADED_BLOCKS).size();
    }

    private boolean isIdle() {
        return driver.findElements(LOADING).isEmpty();
    }
}
