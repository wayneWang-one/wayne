package Listener;

import com.aventstack.extentreports.ExtentTest;
/***
 * 
 * @author wayne
 *
 */
public class MyReporter {
    public static ExtentTest report;
    private static String testName;

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String testName) {
        MyReporter.testName = testName;
    }
}
