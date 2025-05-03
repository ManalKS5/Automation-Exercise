package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FailTest extends BaseTest {

    @Test
    public void triggerFailureOnPurpose() {
        Assert.assertEquals(1, 2, "This is an intentional failure for testing Jira bug creation.");
    }
}
