package selenium.pure.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Works with "Example 1", a table whose cells have no ids or classes.
 * Columns are located by their header text, so the code does not depend on column positions.
 */
public class TablesPage extends BasePage {

    private static final String PATH = "/tables";

    private static final By TABLE = By.id("table1");
    private static final By HEADERS = By.cssSelector("#table1 thead th");
    private static final By ROWS = By.cssSelector("#table1 tbody tr");
    private static final By CELLS = By.tagName("td");

    public TablesPage(WebDriver driver) {
        super(driver);
    }

    public TablesPage open() {
        open(PATH);
        return this;
    }

    public TablesPage sortBy(String column) {
        header(column).click();
        return this;
    }

    public List<String> columnValues(String column) {
        // nth-child is 1-based
        By cells = By.cssSelector("#table1 tbody tr td:nth-child(" + (columnIndex(column) + 1) + ")");
        return driver.findElements(cells).stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * Returns the row whose "Last Name" matches, as a map of header -> cell text.
     */
    public Map<String, String> row(String lastName) {
        List<String> headers = headers();
        int lastNameIndex = headers.indexOf("Last Name");

        for (WebElement row : driver.findElements(ROWS)) {
            // findElements on an element searches only inside that element
            List<WebElement> cells = row.findElements(CELLS);
            if (cells.get(lastNameIndex).getText().equals(lastName)) {
                Map<String, String> values = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    values.put(headers.get(i), cells.get(i).getText());
                }
                return values;
            }
        }
        throw new IllegalArgumentException("No row with last name '" + lastName + "'");
    }

    private List<String> headers() {
        waitVisible(TABLE);
        return driver.findElements(HEADERS).stream()
                .map(WebElement::getText)
                .toList();
    }

    private int columnIndex(String column) {
        int index = headers().indexOf(column);
        if (index < 0) {
            throw new IllegalArgumentException("No column '" + column + "'. Available: " + headers());
        }
        return index;
    }

    private WebElement header(String column) {
        return driver.findElements(HEADERS).get(columnIndex(column));
    }
}
