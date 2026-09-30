package selenium.pure.automation.core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Screenshots {

    private static final Path DIRECTORY = Path.of("target", "screenshots");
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    private Screenshots() {
    }

    public static Path save(WebDriver driver, String name) {
        byte[] image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Path file = DIRECTORY.resolve(sanitize(name) + "_" + LocalDateTime.now().format(TIMESTAMP) + ".png");
        try {
            Files.createDirectories(DIRECTORY);
            Files.write(file, image);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not save screenshot " + file, e);
        }
        System.out.println("Screenshot saved: " + file.toAbsolutePath());
        return file;
    }

    private static String sanitize(String name) {
        return name.replaceAll("[^A-Za-z0-9._-]+", "_");
    }
}
