package rahulshettyacademy.Tests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
public class SubmitOrderTest extends BaseTest {
    @Test(dataProvider = "getData", groups = {"purchase"})
    public void submitOrder(HashMap<String, String> data) throws Exception {
        ProductCatalogue productCatalogue = landingPage.login(data.get("email"), data.get("password"));
        productCatalogue.getProductList();
        CartPage cartPage = productCatalogue.addProductToCart(data.get("productName"));
        boolean match = cartPage.verifyProductDisplay(data.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("ind");
        ConfirmationPage confirmationPage = checkoutPage.goToSubmitOrder();
        String expected_text = "THANKYOU FOR THE ORDER.";
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase(expected_text));
    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void verifyOrder() {
        ProductCatalogue productCatalogue = landingPage.login("prashanthreddypadamati20@gmail.com", "Chintu@1260741");
        boolean match = productCatalogue.goToOrderPage("ZARA COAT 3");
        Assert.assertTrue(match);
    }
    @AfterMethod(alwaysRun = true)
    public void closeWindow() {
        driver.close();
    }
    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> map=getDataFromJson(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder//PurchaseOrder.json");
        return new Object[][] {{map.get(0)},{map.get(1)}};
    }
}


