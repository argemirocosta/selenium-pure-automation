package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Page structure:
 * <pre>
 * main page
 * ├── frame-top
 * │   ├── frame-left
 * │   ├── frame-middle
 * │   └── frame-right
 * └── frame-bottom
 * </pre>
 * Every method switches into the frames it needs and always goes back to the main page
 * in a finally block, so callers never end up stuck inside a frame.
 */
public class NestedFramesPage extends BasePage {

    private static final String PATH = "/nested_frames";

    private static final By TOP_FRAME = By.name("frame-top");
    private static final By LEFT_FRAME = By.name("frame-left");
    private static final By MIDDLE_FRAME = By.name("frame-middle");
    private static final By RIGHT_FRAME = By.name("frame-right");
    private static final By BOTTOM_FRAME = By.name("frame-bottom");
    private static final By BODY = By.tagName("body");

    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }

    public NestedFramesPage open() {
        open(PATH);
        return this;
    }

    public String middleText() {
        try {
            switchToFrame(TOP_FRAME);
            switchToFrame(MIDDLE_FRAME);
            return text(BODY);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public List<String> leftAndRightTexts() {
        try {
            switchToFrame(TOP_FRAME);
            switchToFrame(LEFT_FRAME);
            String left = text(BODY);

            // One level up, back to frame-top, to reach the sibling frame
            driver.switchTo().parentFrame();
            switchToFrame(RIGHT_FRAME);
            String right = text(BODY);

            return List.of(left, right);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String bottomText() {
        try {
            switchToFrame(BOTTOM_FRAME);
            return text(BODY);
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}
