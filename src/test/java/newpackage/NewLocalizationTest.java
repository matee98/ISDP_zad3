package newpackage;
 
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class NewLocalizationTest {
    private WebDriver driver;
    private String url;
    private String login;
    private String password;
    private String newLocation;
    private WebDriverWait wait;
     
    @BeforeTest
    public void setupClass() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        url = "https://localhost:8181/WM/";
        login = "JDoe";
        password = "P@ssw0rd";
        newLocation = "AA-01-05-05";
        wait = new WebDriverWait(driver, 15);
 
    }
    
    @Test
    public void newLocalizationTest() {
        driver.get(url);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../common/signIn.xhtml']")));
        driver.findElement(By.xpath("//a[@href='../common/signIn.xhtml']")).click();

        WebElement loginField = driver.findElement(By.name("j_username"));
        loginField.clear();
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        driver.findElement(By.xpath("//input[@value='Zaloguj']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Lokalizacja")));
        driver.findElement(By.linkText("Lokalizacja")).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../location/createNewLocation.xhtml']")));
        driver.findElement(By.xpath("//a[@href='../location/createNewLocation.xhtml']")).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../main/index.xhtml']")));
        WebElement locationTextField = driver.findElement(By.name("CreateLocationForm:locationSymbol"));
        locationTextField.clear();
        locationTextField.sendKeys(newLocation);
        driver.findElement(By.name("CreateLocationForm:locationType")).click();
        driver.findElement(By.xpath("//option[@value='SHELF2']")).click();
        driver.findElement(By.xpath("//input[@class='button']")).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Lokalizacja")));
        driver.findElement(By.linkText("Lokalizacja")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../location/listLocations.xhtml']")));
        driver.findElement(By.xpath("//a[@href='../location/listLocations.xhtml']")).click();
        
        WebElement table = driver.findElement(By.xpath("//table[@class='table']"));

        List<WebElement> values = table.findElements(By.xpath("//tr[td='AA-01-05-05']/td"));
        Assert.assertEquals(values.get(0).getText(), newLocation);
        Assert.assertEquals(values.get(1).getText(), "POŁOWA");
        Assert.assertEquals(values.get(2).getText(), "30.0");

        table.findElement(By.xpath("//tr[td='AA-01-05-05']/td/input[@value='Usuwanie lokalizacji']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button']")));
        driver.findElement(By.xpath("//input[@class='button']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../main/index.xhtml']")));
        try {
            List<WebElement> values2 = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[td='AA-01-05-05']/td"));
            Assert.assertEquals(values2.size(), 0);
        } catch (NoSuchElementException ex) {

        }

        driver.findElement(By.xpath("//a[@href='../common/logout.xhtml']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Wyloguj']")));
        driver.findElement(By.xpath("//input[@value='Wyloguj']")).click();
 }
 
    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
