package rahulshettyacademy.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.CartPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;
    public AbstractComponent(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver,this);

    }
    @FindBy(css="button[routerlink='/dashboard/cart']")
    WebElement cartButton;

    public CartPage goToCart(){
        //driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
        cartButton.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;

    }
    public void waitUntilElementToVisible(By findBy){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitUntilElementToInvisible(WebElement element){
       WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitTillErrorMessageAppear(WebElement errorMessage){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }
}
