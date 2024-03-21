package rahulshettyacademy.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    @Test(groups = {"submit order"})
    public  void standAloneTest(){
        String productName="ZARA COAT 3";
        System.setProperty("webdriver.gecko.driver","C://Users//Prashanth Reddy//Downloads//geckodriver.exe");
        WebDriver driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://www.rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("prashanthreddypadamati20@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Chintu@1260741");
        driver.findElement(By.id("login")).click();
        List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
        WebElement requiredProduct=products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        requiredProduct.findElement(By.cssSelector("div div button:nth-of-type(2)")).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ng-star-inserted")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
        List<WebElement> productsInCart=driver.findElements(By.cssSelector(".cartSection:first-child"));
        Boolean match=productsInCart.stream().anyMatch(prod->prod.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector("li .btn.btn-primary:last-of-type")).click();
        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
        List<WebElement> options=driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
        WebElement option=options.stream().filter(opt->opt.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
        option.click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String expected_text="THANKYOU FOR THE ORDER.";
        /*String orderConfirmationText*/Boolean orderConfirmed=driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase(expected_text);
        Assert.assertTrue(orderConfirmed);
        driver.close();
    }
}
