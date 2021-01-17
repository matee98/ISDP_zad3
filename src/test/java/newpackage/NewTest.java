package newpackage;
 
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class NewTest {
    private WebDriver driver;
    private String adress;
    private String login;
    private String password;

    @BeforeTest
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        adress = "https://localhost:8181/WM/faces/common/signIn.xhtml";
        login = "JDoe";
        password = "P@ssw0rd";
    }

    @Test
    public void UseCaseTest() {
        driver.get(adress);
        WebElement loginField = driver.findElement(By.name("j_username"));

    }

 
 
    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
 
}