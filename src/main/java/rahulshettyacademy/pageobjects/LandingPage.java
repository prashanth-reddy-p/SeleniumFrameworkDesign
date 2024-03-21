package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
//    driver.findElement(By.id("userEmail")).sendKeys("prashanthreddypadamati20@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Chintu@1260741");
//        driver.findElement(By.id("login")).click();
    @FindBy(id="userEmail")
    WebElement userName;
    @FindBy(id="userPassword")
    WebElement userpassword;
    @FindBy(id="login")
    WebElement login;
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue login(String email, String password){
        userName.sendKeys(email);
        userpassword.sendKeys(password);
        login.click();
        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        return productCatalogue;

    }
    public void goTo(){
        driver.get("https://www.rahulshettyacademy.com/client");
    }
    public String errorMessage(){
        waitTillErrorMessageAppear(errorMessage);
        return errorMessage.getText();
    }

}
