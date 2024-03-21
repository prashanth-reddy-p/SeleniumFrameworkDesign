package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
//    driver.findElement(By.id("userEmail")).sendKeys("prashanthreddypadamati20@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Chintu@1260741");
//        driver.findElement(By.id("login")).click();
    @FindBy(css=".cartSection:first-child")
    List<WebElement> productsInCart;
    @FindBy(css="li .btn.btn-primary:last-of-type")
    WebElement checkout;
    public boolean verifyProductDisplay(String productName){
        Boolean match=productsInCart.stream().anyMatch(prod->prod.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
        return match;
    }
    public CheckoutPage goToCheckout(){
        //driver.findElement(By.cssSelector("li .btn.btn-primary:last-of-type")).click();
        checkout.click();
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        return checkoutPage;
    }


}
