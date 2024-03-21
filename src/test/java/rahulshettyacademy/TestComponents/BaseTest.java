package rahulshettyacademy.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import rahulshettyacademy.pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws Exception {

        Properties properties = new Properties();
       FileInputStream fis=new FileInputStream("C://Users//prashanth reddy//Documents//SeleniumFrameworkDesign//src//main//java//rahulshettyacademy//Resouces//GlobalData.properties");
       properties.load(fis);
        String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):properties.getProperty("browser");
        if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "C://Users//Prashanth Reddy//Downloads//chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return driver;
    }
    @BeforeMethod(alwaysRun = true)
        public void launchApplication () throws Exception {
            driver = initializeDriver();
             landingPage = new LandingPage(driver);
            landingPage.goTo();
//            return landingPage;
        }
        public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
            TakesScreenshot screenShot = (TakesScreenshot) driver;
            File source=screenShot.getScreenshotAs(OutputType.FILE);
            File file=new File(System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png");
            FileUtils.copyFile(source,file);
            return file.getAbsolutePath();
        }
        public List<HashMap<String, String>> getDataFromJson(String fileName) throws IOException {
            String jSonContent=FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8);
            ObjectMapper mapper=new ObjectMapper();
            List<HashMap<String,String>> data=mapper.readValue(jSonContent, new TypeReference<List<HashMap<String, String>>>(){
            });
            return data;
        }

        }



