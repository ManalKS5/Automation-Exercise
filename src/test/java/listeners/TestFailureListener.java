package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.JiraBugReporter;

public class TestFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String testClass = result.getTestClass().getName();
        String exceptionMessage = result.getThrowable() != null ? result.getThrowable().toString() : "Unknown failure";

        String summary = "Automated Test Failure: " + testName;
        String description = "Test class: " + testClass + "\nFailure reason:\n" + exceptionMessage;

        JiraBugReporter.createBug(summary, description);
    }
}
