package testNgClassess;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author: Orhan Demirci
 */
public class ParametersExample {
     WebDriver driver;
    @Parameters({"browser","url"})
    @BeforeClass
    public  void setUp(String browser, String url){
        switch(browser){
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.get(url);
                break;
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.get(url);
                break;
            case "edge" :
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.get(url);
                break;
        }


    }
    @AfterClass
    public  void tearDown(){
        driver.quit();
    }

    @Parameters({"username","password"})
    @Test
    public void login(String username, String password) {
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
    }

    @Parameters({"firstname","lastname"})
    @Test(dependsOnMethods = "login")
    public void addEmployee(String firstname, String lastname) throws InterruptedException {
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

}
