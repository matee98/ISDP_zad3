package newpackage;
 
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
public class NewTest {
    private WebDriver driver;
    @Test
    public void testEasy() {
        driver.get("http://demo.guru99.com/test/guru99home/");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Demo Guru99 Page"));
    }
 
 
    @BeforeTest
    public void setupClass() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(); // dobra zmiana
 
    }
 
 
    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
 
}