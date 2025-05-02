//package listeners;
//
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import utils.JiraBugReporter;
//
//public class TestFailureListener implements ITestListener {
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        String testName = result.getName();
//        String testClass = result.getTestClass().getName();
//        String exceptionMessage = result.getThrowable() != null ? result.getThrowable().toString() : "Unknown failure";
//
//        String summary = "Automated Test Failure: " + testName;
//        String description = "Test class: " + testClass + "\nFailure reason:\n" + exceptionMessage;
//
//        JiraBugReporter.createBug(summary, description);
//    }
//}

package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.JiraBugReporter;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String testClass = result.getTestClass().getName();

        Throwable throwable = result.getThrowable();
        String exceptionMessage = (throwable != null) ? throwable.toString() : "Unknown failure";

        // Convert stack trace to string (optional)
        StringWriter sw = new StringWriter();
        if (throwable != null) {
            throwable.printStackTrace(new PrintWriter(sw));
        }
        String stackTrace = sw.toString();

        String summary = "❌ Automated Test Failed: " + testName;
        String description =
                "🔹 Test Class: " + testClass + "\n" +
                        "🔸 Failure Reason: " + exceptionMessage + "\n\n" +
                        "🧵 Stack Trace:\n" + stackTrace;

        System.out.println("📍 [TestFailureListener] Detected failure in: " + testClass + "." + testName);
        JiraBugReporter.createBug(summary, description);
    }
}
