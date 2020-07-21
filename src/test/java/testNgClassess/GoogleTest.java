package testNgClassess;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class GoogleTest {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().deleteAllCookies();
         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
         driver.get("https://www.google.com/");
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    @Test(/*priority = 1*/enabled = false)
    public void AgoogleTitleTest(){
        String actualTitle = driver.getTitle();
        String expectedTitle = "Google";
        Assert.assertEquals(actualTitle,expectedTitle,"failure");
    }
    @Test(/*priority = 2*/)
    public void CgoogleLogoDisplay(){
        WebElement logo = driver.findElement(By.id("hplogo"));
        System.out.println("logo display situation====>"+logo.isDisplayed());
    }
    @Test(/*priority = 3*/dependsOnMethods = "CgoogleLogoDisplay")
    public void BgmailLinkDisplay(){
        WebElement gmail = driver.findElement(By.xpath("//a[contains(text(),'Gmail')]"));
        gmail.click();
        //System.out.println("gmail button enabled====>"+gmail.isEnabled());
    }
}
