package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
//    driver.findElement(By.id("userEmail")).sendKeys("prashanthreddypadamati20@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Chintu@1260741");
//        driver.findElement(By.id("login")).click();
    @FindBy(css="button[routerlink='/dashboard/myorders']")
    WebElement orderPage;
    @FindBy(css="tbody tr td:nth-child(3)")
    List<WebElement> orders;
    @FindBy(css="tbody tr td:nth-child(3)")
    WebElement order;

    public boolean orders(String s) {
        waitTillErrorMessageAppear(order);
        boolean match = orders.stream().anyMatch(o -> o.getText().equalsIgnoreCase(s));
        return match;
    }


}
