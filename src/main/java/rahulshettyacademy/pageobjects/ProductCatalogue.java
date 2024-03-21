package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.time.Duration;
import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;
    @FindBy(className = "ng-animating")
    WebElement element;
    @FindBy(css = "button[routerlink='/dashboard/myorders']")
    WebElement orderPage;
    @FindBy(css = "tbody tr td:nth-child(3)")
    List<WebElement> orders;
    @FindBy(css = "tbody tr td:nth-child(3)")
    WebElement order;

    public List<WebElement> getProductList() {
        return products;
    }

    public WebElement getProductsByName(String productName) {
        WebElement requiredproduct = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return requiredproduct;
    }

    public CartPage addProductToCart(String productName) {
        getProductsByName(productName).findElement(By.cssSelector("div div button:nth-of-type(2)")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
        CartPage cartpage = goToCart();
        return cartpage;
    }

    public boolean goToOrderPage(String productName) {
        //driver.findElement(By.cssSelector("li .btn.btn-primary:last-of-type")).click();
        //driver.findElements(By.cssSelector("tbody tr td:nth-child(3)"));
        orderPage.click();
       OrderPage orderpage=new OrderPage(driver);
       return orderpage.orders(productName);

    }
}