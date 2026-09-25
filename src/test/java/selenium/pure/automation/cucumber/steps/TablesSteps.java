package selenium.pure.automation.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pure.automation.cucumber.DriverContext;
import selenium.pure.automation.pages.TablesPage;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TablesSteps {

    private final DriverContext context;

    private TablesPage tablesPage;

    public TablesSteps(DriverContext context) {
        this.context = context;
    }

    @Given("I am on the tables page")
    public void iAmOnTheTablesPage() {
        tablesPage = new TablesPage(context.driver()).open();
    }

    @When("I sort the table by {string}")
    public void iSortTheTableBy(String column) {
        tablesPage.sortBy(column);
    }

    // Cucumber converts a two-column DataTable into a Map automatically
    @Then("the row for {string} should contain:")
    public void theRowShouldContain(String lastName, Map<String, String> expected) {
        Map<String, String> row = tablesPage.row(lastName);

        expected.forEach((column, value) -> assertEquals(value, row.get(column), column));
    }

    @Then("the {string} column should be sorted alphabetically")
    public void theColumnShouldBeSortedAlphabetically(String column) {
        List<String> values = tablesPage.columnValues(column);

        assertEquals(values.stream().sorted().toList(), values);
    }

    @Then("the {string} column should be sorted by amount")
    public void theColumnShouldBeSortedByAmount(String column) {
        // Sorting "$100.00" and "$50.00" as text would put $100.00 first,
        // so the values are compared as numbers
        List<BigDecimal> amounts = tablesPage.columnValues(column).stream()
                .map(value -> new BigDecimal(value.replace("$", "")))
                .toList();

        assertEquals(amounts.stream().sorted(Comparator.naturalOrder()).toList(), amounts);
    }
}
