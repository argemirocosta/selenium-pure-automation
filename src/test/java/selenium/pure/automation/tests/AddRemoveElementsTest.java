package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.AddRemoveElementsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRemoveElementsTest extends BaseTest {

    @Test
    void shouldStartWithNoElements() {
        AddRemoveElementsPage page = new AddRemoveElementsPage(driver).open();

        assertEquals(0, page.elementCount());
    }

    @Test
    void shouldAddElements() {
        AddRemoveElementsPage page = new AddRemoveElementsPage(driver).open().addElements(3);

        assertEquals(3, page.elementCount());
    }

    @Test
    void shouldRemoveElement() {
        AddRemoveElementsPage page = new AddRemoveElementsPage(driver).open()
                .addElements(3)
                .removeElement(1);

        assertEquals(2, page.elementCount());
    }
}
