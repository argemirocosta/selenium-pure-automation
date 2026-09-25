package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.DynamicLoadingPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicLoadingTest extends BaseTest {

    @Test
    void shouldShowHiddenElementAfterLoading() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver).open(1);

        assertTrue(page.isFinishTextInDom());
        assertFalse(page.isFinishTextDisplayed());

        page.start();

        assertEquals("Hello World!", page.finishText());
    }

    @Test
    void shouldRenderNewElementAfterLoading() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver).open(2);

        assertFalse(page.isFinishTextInDom());

        page.start();

        assertEquals("Hello World!", page.finishText());
    }
}
