# selenium-pure-automation

[![Tests](https://github.com/argemirocosta/selenium-pure-automation/actions/workflows/tests.yml/badge.svg)](https://github.com/argemirocosta/selenium-pure-automation/actions/workflows/tests.yml)

A practice project for pure Selenium WebDriver fundamentals, tested against
[The Internet](https://the-internet.herokuapp.com).

## Stack

- Java 21
- Maven
- Selenium 4.49 (drivers and browsers resolved by Selenium Manager)
- JUnit 6
- Cucumber 8 (Gherkin in English)

## Prerequisites

- JDK 21+
- Maven 3.9+
- Chrome or Firefox

Both versions are checked by the Maven Enforcer Plugin, so the build stops early if either is too old.
No driver setup is needed.

## Running

```bash
mvn test
mvn test -Dbrowser=firefox
mvn test -Dheadless=true
mvn test -Dgroups=smoke
mvn test -Dtest=CheckboxesTest
mvn test -Dtest=RunCucumberTest
```

`-Dgroups=smoke` runs both JUnit tests tagged `@Tag("smoke")` and Cucumber scenarios tagged `@smoke`.

## Configuration

Defaults live in `src/test/resources/config.properties`. Any key can be overridden with `-D`.

Precedence: `-D` > `config.properties` > default in code.

| Key               | Default                              | Description                          |
|-------------------|--------------------------------------|--------------------------------------|
| `baseUrl`         | `https://the-internet.herokuapp.com` | Application under test               |
| `browser`         | `chrome`                             | `chrome` or `firefox`                |
| `headless`        | `false`                              | Run without a visible browser window |
| `timeout`         | `10`                                 | Explicit wait timeout, in seconds    |
| `pageLoadTimeout` | `30`                                 | Page load timeout, in seconds        |

## Parallel execution

Tests run in parallel with 4 threads, configured in `src/test/resources/junit-platform.properties`.
JUnit tests run first, then Cucumber scenarios, so at most 4 browsers are open at the same time.
`max-pool-size` caps the thread pool, since `parallelism` alone does not stop JUnit from creating extra threads.

To run sequentially:

```bash
mvn test -Djunit.jupiter.execution.parallel.enabled=false -Dcucumber.execution.parallel.enabled=false
```

## Project structure

```
src/test/java/selenium/pure/automation
├── core         Config, Browser, DriverFactory, Screenshots
├── pages        Page Objects (BasePage and one class per page)
├── components   Reusable page fragments (FlashMessage)
├── data         Test data constants
├── tests        JUnit tests, BaseTest and the ScreenshotOnFailure extension
└── cucumber     Runner, hooks, DriverContext and step definitions

src/test/resources
├── config.properties
├── junit-platform.properties
├── features     Gherkin feature files
└── files        Files used by the upload scenario
```

## Design decisions

- **Page Objects**: locators are `By` fields, pages are fluent and return the next page,
  and assertions live only in tests and steps.
- **Explicit waits only**: no implicit waits and no `Thread.sleep`.
- **One driver per test**: JUnit tests extend `BaseTest`, and Cucumber scenarios get a `DriverContext`
  injected by PicoContainer. No test state is static, which keeps parallel execution safe.
- **Hybrid approach**: Cucumber covers business-like flows, and plain JUnit covers technical fundamentals.
  Both share the same Page Objects.

## Coverage

| Page                 | Runner   | Fundamentals                                                          |
|----------------------|----------|-----------------------------------------------------------------------|
| Login                | Cucumber | Forms, flash messages, Scenario Outline, waiting for navigation      |
| Sortable Tables      | Cucumber | Reading tables, DataTable, sorting checks                             |
| File Upload          | Cucumber | `sendKeys` on a file input with a path resolved from the classpath    |
| Checkboxes           | JUnit    | `isSelected`, toggling                                                |
| Dropdown             | JUnit    | `Select`                                                              |
| Add/Remove Elements  | JUnit    | `findElements` for counting and absence                               |
| Dynamic Loading      | JUnit    | Hidden vs. rendered elements, visibility waits                        |
| Dynamic Controls     | JUnit    | Invisibility and clickable waits, enabled state                       |
| JavaScript Alerts    | JUnit    | `Alert`: accept, dismiss, prompt input                                |
| Nested Frames        | JUnit    | `switchTo().frame`, `parentFrame`, `defaultContent`                   |
| Multiple Windows     | JUnit    | Window handles, `newWindow(WindowType.TAB)`                           |
| Infinite Scroll      | JUnit    | `JavascriptExecutor`, custom lambda wait                              |

## Reports

- Surefire reports: `target/surefire-reports`
- Screenshots of failed tests and scenarios: `target/screenshots`

## CI

The [Tests workflow](.github/workflows/tests.yml) runs on GitHub Actions with Chrome headless:

- on every pull request
- on every push to `main`
- on demand, from the Actions tab (**Run workflow**), choosing the branch and the browser

Each run writes a Markdown report to the job summary, generated by
[`test_report.py`](.github/scripts/test_report.py) from the Surefire XML files:
failed tests first with their error, then passed tests sorted by duration.
When tests fail, Surefire reports and screenshots are uploaded as the `test-results` artifact.
