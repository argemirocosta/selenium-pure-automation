package selenium.pure.automation.core;

import java.util.Arrays;

public enum Browser {
    CHROME,
    FIREFOX;

    public static Browser from(String name) {
        return Arrays.stream(values())
                .filter(browser -> browser.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported browser: '" + name + "'. Use one of " + Arrays.toString(values())));
    }
}
