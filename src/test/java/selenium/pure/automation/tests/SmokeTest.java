package selenium.pure.automation.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import selenium.pure.automation.core.Config;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("smoke")
@DisplayName("Smoke")
class SmokeTest extends BaseTest {

    @Test
    @DisplayName("Home page abre com o título esperado")
    void shouldOpenHomePage() {
        driver.get(Config.baseUrl());

        assertEquals("The Internet", driver.getTitle());
    }
}
