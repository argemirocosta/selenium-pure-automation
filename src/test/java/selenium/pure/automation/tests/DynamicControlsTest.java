package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.DynamicControlsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicControlsTest extends BaseTest {

    @Test
    void shouldRemoveAndAddCheckbox() {
        DynamicControlsPage page = new DynamicControlsPage(driver).open().removeCheckbox();

        assertFalse(page.isCheckboxPresent());
        assertEquals("It's gone!", page.checkboxMessage());

        page.addCheckbox();

        assertTrue(page.isCheckboxPresent());
        assertEquals("It's back!", page.checkboxMessage());
    }

    @Test
    void shouldEnableInputAndType() {
        DynamicControlsPage page = new DynamicControlsPage(driver).open();

        assertFalse(page.isInputEnabled());

        page.enableInput().typeInInput("Selenium");

        assertEquals("It's enabled!", page.inputMessage());
        assertEquals("Selenium", page.inputValue());
    }
}
