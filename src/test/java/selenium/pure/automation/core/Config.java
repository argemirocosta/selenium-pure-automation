package selenium.pure.automation.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.Properties;

/**
 * Reads settings with the precedence: system property (-D) > config.properties > default in code.
 */
public final class Config {

    private static final String FILE = "config.properties";
    private static final Properties FILE_PROPERTIES = load();

    private Config() {
    }

    public static String baseUrl() {
        return get("baseUrl", "https://the-internet.herokuapp.com");
    }

    public static Browser browser() {
        return Browser.from(get("browser", "chrome"));
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless", "false"));
    }

    public static Duration timeout() {
        return Duration.ofSeconds(Long.parseLong(get("timeout", "10")));
    }

    private static String get(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value == null || value.isBlank()) {
            value = FILE_PROPERTIES.getProperty(key, defaultValue);
        }
        return value.trim();
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(FILE)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read " + FILE, e);
        }
        return properties;
    }
}
