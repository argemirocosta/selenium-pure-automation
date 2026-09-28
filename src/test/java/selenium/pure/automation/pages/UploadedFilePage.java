package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadedFilePage extends BasePage {

    private static final By HEADING = By.tagName("h3");
    private static final By UPLOADED_FILES = By.id("uploaded-files");

    public UploadedFilePage(WebDriver driver) {
        super(driver);
    }

    public String heading() {
        return text(HEADING);
    }

    public String uploadedFile() {
        return text(UPLOADED_FILES).trim();
    }
}
