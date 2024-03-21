package rahulshettyacademy.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
    @Test
    public void loginErrorValidation() throws Exception {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.login("prahanthreddypadamati20@gmail.com", "Chintu@1260741");
        String errorMessage=landingPage.errorMessage();
        Assert.assertEquals("Incorrect email or password.",errorMessage);
    }
    @AfterMethod
    public void closeWindow(){
        driver.close();
    }
}


