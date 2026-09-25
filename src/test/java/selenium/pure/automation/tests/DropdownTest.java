package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.DropdownPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DropdownTest extends BaseTest {

    @Test
    void shouldHaveNoOptionSelectedByDefault() {
        DropdownPage page = new DropdownPage(driver).open();

        assertEquals("Please select an option", page.selectedOption());
    }

    @Test
    void shouldSelectOption1() {
        DropdownPage page = new DropdownPage(driver).open().select("Option 1");

        assertEquals("Option 1", page.selectedOption());
    }

    @Test
    void shouldSelectOption2() {
        DropdownPage page = new DropdownPage(driver).open().select("Option 2");

        assertEquals("Option 2", page.selectedOption());
    }

    @Test
    void shouldListAvailableOptions() {
        DropdownPage page = new DropdownPage(driver).open();

        assertEquals(List.of("Please select an option", "Option 1", "Option 2"), page.options());
    }
}
