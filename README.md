# 🚀 Automation Testing Framework – AutomationExercise.com

This project is a **robust test automation framework** built using **Java**, **Selenium WebDriver**, and **TestNG**, designed to validate core functionalities of [AutomationExercise.com](https://automationexercise.com/). It reflects real-world best practices, including CI/CD integration, Allure reporting, data-driven testing, structured logging, and automated defect creation in Jira.

---

## 🎯 Objectives

- Automate core test scenarios on a dynamic e-commerce website.
- Design a framework that is modular, maintainable, and easy to extend.
- Showcase capabilities in CI/CD, reporting, data-driven testing, and defect management.
- Align with industry best practices in QA automation.

---

## ✅ Features

- ✔️ Selenium WebDriver with Java and TestNG
- ✔️ Page Object Model (POM) design pattern
- ✔️ CI/CD pipeline with Jenkins
- ✔️ Allure report generation
- ✔️ Data-driven testing using Excel and CSV
- ✔️ Jira integration: automatic ticket creation on test failure
- ✔️ Logging and exception tracking using Log4j
- ✔️ TestNG Listeners to trigger custom behavior on test failures
- ✔️ Organized test suites and reusable utilities

---

## 🧪 Automated Test Scenarios

1. **User Registration**: Fill in user data, submit form, and verify account creation  
2. **Contact Us Form**: Submit a support request and verify success message  
3. **Search Functionality**: Search for a product and validate search results

---

## 🧱 Tech Stack

| Component       | Technology             |
|----------------|------------------------|
| Language        | Java                   |
| Automation      | Selenium WebDriver     |
| Test Framework  | TestNG                 |
| Reporting       | Allure                 |
| CI/CD           | Jenkins                |
| Logging         | Log4j                  |
| Data Providers  | Apache POI (Excel), OpenCSV (CSV) |
| Defect Tracking | Jira (REST API)        |

---

## 📊 Reporting & Logging

- **Allure Reports**: Automatically generated in Jenkins to provide clean, detailed test results.
- **Log4j Logs**: Each test logs detailed status, errors, and actions into the `/logs` directory.
- **Jira Tickets**: On test failure, tickets are automatically created via the Jira REST API, including exception messages and test context.

---

## ▶️ Running the Tests Locally

1. **Clone the project**:
   ```bash
   git clone https://github.com/ManalKS5/Automation-Exercise.git
   ```
2. **Install dependencies:**
   ```bash
   mvn clean install
   ```
3. **Execute tests:**
   ```bash
   mvn test
    ```

---

## 🛠️ Continuous Integration (CI/CD)
Jenkins is used for automated test execution after every commit.

The Jenkinsfile defines the pipeline steps, including:

- Code checkout
- Test execution
- Allure report generation

---

## 🧩 Jira Integration
- Jira API is used to create issues automatically on test failure.
- Implemented via a custom TestNG Listener.
- Tickets include failure logs and stack traces for fast debugging.

---

## 🙋 Author

Manal Sewaied

Quality Assurance & Automation Engineer

[LinkedIn](https://www.linkedin.com/in/manal-sewaied-76bb18216/)
