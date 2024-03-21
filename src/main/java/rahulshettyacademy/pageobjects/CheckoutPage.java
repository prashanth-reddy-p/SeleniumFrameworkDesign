package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //List<WebElement> options=driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
    @FindBy(css="input[placeholder='Select Country']")
        WebElement insertCountry;
    @FindBy(css=".ta-item.list-group-item.ng-star-inserted")
    List<WebElement> options;
    @FindBy(css=".action__submit")
    WebElement submit;
    //driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
    public void selectCountry(String country){
        insertCountry.sendKeys(country);
        options.stream().filter(opt->opt.getText().equalsIgnoreCase("india")).findFirst().orElse(null).click();
    }
    public ConfirmationPage goToSubmitOrder(){
        //driver.findElement(By.cssSelector(".action__submit")).click();
        submit.click();
        ConfirmationPage confirmationPage=new ConfirmationPage(driver);
        return confirmationPage;

    }

}
