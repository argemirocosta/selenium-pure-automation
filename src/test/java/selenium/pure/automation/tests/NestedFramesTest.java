package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.NestedFramesPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NestedFramesTest extends BaseTest {

    @Test
    void shouldReadNestedFrame() {
        NestedFramesPage page = new NestedFramesPage(driver).open();

        assertEquals("MIDDLE", page.middleText());
    }

    @Test
    void shouldReadSiblingFrames() {
        NestedFramesPage page = new NestedFramesPage(driver).open();

        assertEquals(List.of("LEFT", "RIGHT"), page.leftAndRightTexts());
    }

    @Test
    void shouldReadBottomFrameAfterNestedFrame() {
        NestedFramesPage page = new NestedFramesPage(driver).open();
        page.middleText();

        // Only works because middleText() went back to the main page
        assertEquals("BOTTOM", page.bottomText());
    }
}
