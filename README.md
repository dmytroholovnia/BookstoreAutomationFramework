# Bookstore Automation Framework

POC of automation framework Java + RestAssured

## Overview

This project is a proof-of-concept automation framework built with Java and RestAssured. It is designed for automated testing of a bookstore API or web service.

## Features

- Automated API tests using Java and RestAssured
- Maven build system for easy dependency management
- CI/CD integration with GitHub Actions
- Allure reporting for test results

## Prerequisites

- Java 17 or newer
- Maven 3.6+
- Git (for cloning the repository)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/dmytroholovnia/BookstoreAutomationFramework.git
cd BookstoreAutomationFramework
```

### Running Tests via GitHub Actions

Automated tests are configured to run in a GitHub Actions workflow. You can trigger the workflow manually from the "Actions" tab in your repository.

- You can trigger the workflow manually or view previous runs from the ["Run tests" workflow](https://github.com/dmytroholovnia/BookstoreAutomationFramework/actions/workflows/allure-report.yml) in the Actions tab.
- [Link for latest report](https://dmytroholovnia.github.io/BookstoreAutomationFramework/)
- The workflow file: [.github/workflows/allure-report.yml](.github/workflows/allure-report.yml)
- The workflow installs Java, builds the project, runs `mvn clean test`, and publishes an Allure report.

#### Steps Performed by GitHub Actions

- Checks out the code
- Installs Java 17 (Corretto)
- Runs the tests with Maven
- Generates and publishes an Allure report to GitHub Pages (branch: `master`)
- Prints a link to the published report in the workflow logs

### Running Tests Locally

1. **Install dependencies and run tests:**

   ```bash
   mvn clean test
   ```

   This command will:
    - Download all necessary dependencies
    - Build the project
    - Run all tests

2. **View Allure Reports (optional):**

   You can generate and open the test report:

   ```bash
   mvn allure:report
   ```
3. **Open report file**

    - Open `target/site/allure-maven-plugin/index.html` in browser
    
You can see the latest workflow runs and test results in the [Actions tab](https://github.com/dmytroholovnia/BookstoreAutomationFramework/actions).

## License

This project is open source. See the repository for more details.

---

_This README was created with reference to the project's code and CI configuration as of June 2025._