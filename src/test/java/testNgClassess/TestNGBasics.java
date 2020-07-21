package testNgClassess;

import org.testng.annotations.*;

public class TestNGBasics {
    @BeforeSuite
    public void setUp(){
        System.out.println("driver manager is installed");
        //WebdriverManager.chrome().setup();
    }
    @BeforeTest
    public void launchBrowser(){
        System.out.println("launch chrome browser");
        //WebDriver driver = new ChromeDriver();
        }
    @BeforeClass
    public void login(){
        System.out.println("enter to browser");
    }
    @BeforeMethod// @BeforeEach in junit
    public void enterUrl(){
        System.out.println("enter Url");
        //driver.get("https://google.com")
    }
    @Test
    public void googleTitleTest(){
        System.out.println("Google Title Test");
    }
    @Test
    public void googleLogo(){
        System.out.println("Google Logo Test");
    }
    @AfterMethod// @AfterEach in junit
    public void refresh(){
        System.out.println("Refreshing the google home page");
    }
    @AfterClass
    public void closeBrowser(){
        System.out.println("close the browser");
    }
    @AfterTest
    public void deleteAllCookies(){
        System.out.println("deleteAllCookies");
    }
    @AfterSuite
    public void generateTestReport(){
        System.out.println("generating the test report");
    }
}
