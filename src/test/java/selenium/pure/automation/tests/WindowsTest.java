package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.NewWindowPage;
import selenium.pure.automation.pages.WindowsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WindowsTest extends BaseTest {

    @Test
    void shouldSwitchToNewWindow() {
        NewWindowPage newWindow = new WindowsPage(driver).open().openNewWindow();

        assertEquals("New Window", newWindow.heading());
        assertEquals(2, newWindow.windowCount());
    }

    @Test
    void shouldCloseNewWindowAndReturnToOriginal() {
        WindowsPage original = new WindowsPage(driver).open()
                .openNewWindow()
                .close();

        assertEquals("Opening a new window", original.heading());
        assertEquals(1, original.windowCount());
    }

    @Test
    void shouldOpenNewTab() {
        NewWindowPage newTab = new WindowsPage(driver).open().openNewTab();

        assertEquals("New Window", newTab.heading());
        assertEquals(2, newTab.windowCount());
    }
}
