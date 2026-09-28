package selenium.pure.automation.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pure.automation.cucumber.DriverContext;
import selenium.pure.automation.pages.UploadPage;
import selenium.pure.automation.pages.UploadedFilePage;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UploadSteps {

    private final DriverContext context;

    private UploadPage uploadPage;
    private UploadedFilePage uploadedFilePage;

    public UploadSteps(DriverContext context) {
        this.context = context;
    }

    @Given("I am on the upload page")
    public void iAmOnTheUploadPage() {
        uploadPage = new UploadPage(context.driver()).open();
    }

    @When("I upload the file {string}")
    public void iUploadTheFile(String fileName) throws URISyntaxException {
        uploadedFilePage = uploadPage.upload(resource(fileName));
    }

    @Then("I should see {string} as uploaded")
    public void iShouldSeeAsUploaded(String fileName) {
        assertEquals("File Uploaded!", uploadedFilePage.heading());
        assertEquals(fileName, uploadedFilePage.uploadedFile());
    }

    private Path resource(String fileName) throws URISyntaxException {
        URL url = Objects.requireNonNull(getClass().getResource("/files/" + fileName), fileName);
        return Path.of(url.toURI());
    }
}
