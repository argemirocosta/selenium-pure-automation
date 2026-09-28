package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.InfiniteScrollPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InfiniteScrollTest extends BaseTest {

    @Test
    void shouldLoadMoreContentOnScroll() {
        InfiniteScrollPage page = new InfiniteScrollPage(driver).open();
        int before = page.blockCount();

        page.scrollToBottom();

        assertTrue(page.blockCount() > before);
    }

    @Test
    void shouldLoadSeveralBatches() {
        InfiniteScrollPage page = new InfiniteScrollPage(driver).open();
        int before = page.blockCount();

        page.scrollToBottom().scrollToBottom().scrollToBottom();

        assertTrue(page.blockCount() >= before + 3);
    }
}
