package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.CheckboxesPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckboxesTest extends BaseTest {

    @Test
    void shouldHaveFirstUncheckedAndSecondCheckedByDefault() {
        CheckboxesPage page = new CheckboxesPage(driver).open();

        assertFalse(page.isChecked(1));
        assertTrue(page.isChecked(2));
    }

    @Test
    void shouldToggleCheckboxes() {
        CheckboxesPage page = new CheckboxesPage(driver).open()
                .toggle(1)
                .toggle(2);

        assertTrue(page.isChecked(1));
        assertFalse(page.isChecked(2));
    }
}
