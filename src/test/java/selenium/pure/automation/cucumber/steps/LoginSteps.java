package selenium.pure.automation.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pure.automation.components.FlashMessage;
import selenium.pure.automation.cucumber.DriverContext;
import selenium.pure.automation.data.TestData;
import selenium.pure.automation.pages.LoginPage;
import selenium.pure.automation.pages.SecureAreaPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final DriverContext context;

    private LoginPage loginPage;
    private SecureAreaPage secureAreaPage;
    // Flash message of the page the scenario is currently on
    private FlashMessage flashMessage;

    public LoginSteps(DriverContext context) {
        this.context = context;
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage = new LoginPage(context.driver()).open();
    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
        iAmOnTheLoginPage();
        iLogInWithValidCredentials();
    }

    @When("I log in with valid credentials")
    public void iLogInWithValidCredentials() {
        secureAreaPage = loginPage.loginAs(TestData.USERNAME, TestData.PASSWORD);
        flashMessage = secureAreaPage.flashMessage();
    }

    @When("I log in with username {string} and password {string}")
    public void iLogInWith(String username, String password) {
        loginPage = loginPage.loginWithInvalidCredentials(username, password);
        flashMessage = loginPage.flashMessage();
    }

    @When("I log out")
    public void iLogOut() {
        loginPage = secureAreaPage.logout();
        flashMessage = loginPage.flashMessage();
    }

    @Then("I should be on the secure area")
    public void iShouldBeOnTheSecureArea() {
        assertEquals("Secure Area", secureAreaPage.heading());
    }

    @Then("I should be on the login page")
    public void iShouldBeOnTheLoginPage() {
        assertEquals("Login Page", loginPage.heading());
    }

    @Then("I should see a success message {string}")
    public void iShouldSeeASuccessMessage(String message) {
        assertTrue(flashMessage.isSuccess(), "Expected a success message");
        assertEquals(message, flashMessage.text());
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String message) {
        assertTrue(flashMessage.isError(), "Expected an error message");
        assertEquals(message, flashMessage.text());
    }
}
