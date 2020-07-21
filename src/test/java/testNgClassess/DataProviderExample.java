package testNgClassess;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: Orhan Demirci
 */
public class DataProviderExample {
     WebDriver driver;
    @BeforeMethod
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://opensource-demo.orangehrmlive.com/");

    }
    @AfterMethod
    public  void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "getData")
    public void addEmployee(String firstname, String lastname) throws InterruptedException, IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\demir\\TestNg_Iq\\src\\test\\java\\suite\\loginCredentials.properties");
        prop.load(fis);
        String userName = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("txtUsername")).sendKeys(userName);
        driver.findElement(By.id("txtPassword")).sendKeys(password);


        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
       Actions action = new Actions(driver);
       WebElement pim = driver.findElement(By.xpath("//b[contains(text(),'PIM')]"));
       action.moveToElement(pim).build().perform();
       Thread.sleep(5000);
        WebElement add = driver.findElement(By.linkText("Add Employee"));
        add.click();
        driver.findElement(By.id("firstName")).sendKeys(firstname);
        driver.findElement(By.id("lastName")).sendKeys(lastname);
        String expectedId = driver.findElement(By.cssSelector("#employeeId")).getAttribute("value");
        driver.findElement(By.id("btnSave")).click();
        String actualId =driver.findElement(By.xpath("//input[@id='personal_txtEmployeeId']")).getAttribute("value");
        Assert.assertEquals(actualId,expectedId,"Fail");
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Logout")).click();

    }
   @DataProvider
    public Object[][] getData(){
       Object[][] data ={{"Ali","Donmez"},{"Veli","Doner"},{"Nuriye","Demirci"},{"Ali Ihsan","Demirci"}};
       return data;
   }
}
