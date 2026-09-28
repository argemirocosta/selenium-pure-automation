package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Path;

public class UploadPage extends BasePage {

    private static final String PATH = "/upload";

    private static final By FILE_INPUT = By.id("file-upload");
    private static final By UPLOAD_BUTTON = By.id("file-submit");

    public UploadPage(WebDriver driver) {
        super(driver);
    }

    public UploadPage open() {
        open(PATH);
        return this;
    }

    public UploadedFilePage upload(Path file) {
        waitVisible(FILE_INPUT).sendKeys(file.toAbsolutePath().toString());
        clickAndWaitForNavigation(UPLOAD_BUTTON);
        return new UploadedFilePage(driver);
    }
}
