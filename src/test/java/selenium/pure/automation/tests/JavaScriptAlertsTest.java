package selenium.pure.automation.tests;

import org.junit.jupiter.api.Test;
import selenium.pure.automation.pages.JavaScriptAlertsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaScriptAlertsTest extends BaseTest {

    @Test
    void shouldReadAndAcceptAlert() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage(driver).open().openAlert();

        assertEquals("I am a JS Alert", page.popupText());

        page.accept();

        assertEquals("You successfully clicked an alert", page.result());
    }

    @Test
    void shouldDismissConfirm() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage(driver).open()
                .openConfirm()
                .dismiss();

        assertEquals("You clicked: Cancel", page.result());
    }

    @Test
    void shouldTypeInPromptAndAccept() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage(driver).open()
                .openPrompt()
                .typeInPrompt("Selenium")
                .accept();

        assertEquals("You entered: Selenium", page.result());
    }
}
