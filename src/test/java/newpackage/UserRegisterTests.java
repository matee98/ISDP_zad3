package newpackage;
 
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
 
public class UserRegisterTests {
    private WebDriver driver;
    private String fname = "Mateusz";
    private String lname = "Szewc";
    private String email = "mszewc@lp.pl";
    private String login = "MSzewc";
    private String pass = "qweRTY#3";
    private String question = "Ile owca ma nóg";
    private String answer = "4";
    
    
    @Test
    public void RegisterNewUserAndDeleteTest() {
        driver.get("https://localhost:8181/WM/faces/common/signIn.xhtml");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Logowanie"));
        
        WebElement loginInput = driver.findElement(By.name("j_username"));
        loginInput.clear();
        loginInput.sendKeys("DMitchell");
        WebElement passInput = driver.findElement(By.name("j_password"));
        passInput.clear();
        passInput.sendKeys("P@ssw0rd");
        
        WebElement loginBtn = driver.findElement(By.xpath("//input[@value='Zaloguj']"));
        loginBtn.click();
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://localhost:8181/WM/faces/main/index.xhtml"));
        
        String src = driver.getCurrentUrl();
        
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/main/index.xhtml"));
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../common/registerAccount.xhtml']")));
        
        WebElement registerFormBtn = driver.findElement(By.xpath("//a[@href='../common/registerAccount.xhtml']"));
        registerFormBtn.click();
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://localhost:8181/WM/faces/common/registerAccount.xhtml"));
        
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/common/registerAccount.xhtml"));
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Rejestruj konto']")));

        driver.findElement(By.name("RegisterForm:name")).sendKeys(fname);
        driver.findElement(By.name("RegisterForm:surname")).sendKeys(lname);
        driver.findElement(By.name("RegisterForm:email")).sendKeys(email);
        driver.findElement(By.name("RegisterForm:login")).sendKeys(login);
        driver.findElement(By.name("RegisterForm:password")).sendKeys(pass);
        driver.findElement(By.name("RegisterForm:passwordRepeat")).sendKeys(pass);
        driver.findElement(By.name("RegisterForm:question")).sendKeys(question);
        driver.findElement(By.name("RegisterForm:answer")).sendKeys(answer);
        
        WebElement registerUserBtn = driver.findElement(By.xpath("//input[@value='Rejestruj konto']"));
        registerUserBtn.click();
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://localhost:8181/WM/faces/main/index.xhtml"));
        
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/main/index.xhtml"));
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='dropdown']/a[contains(text(),'Konto użytkownika')]")));
        
        WebElement dropdown = driver.findElement(By.xpath("//li[@class='dropdown']/a[contains(text(),'Konto użytkownika')]"));
        dropdown.click();
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='../account/listNewAccounts.xhtml']")));
        
        WebElement newUserPageBtn = driver.findElement(By.xpath("//a[@href='../account/listNewAccounts.xhtml']"));
        newUserPageBtn.click();
        
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/account/listNewAccounts.xhtml"));
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://localhost:8181/WM/faces/account/listNewAccounts.xhtml"));

        new WebDriverWait(driver, 20).until(d -> ((JavascriptExecutor) d).executeScript("return (document.readyState === 'complete' || document.readyState === 'interactive')"));
        
        List<WebElement> columns = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[td='MSzewc']/td"));
        
        Assert.assertEquals(login, columns.get(0).getText());
        Assert.assertEquals(fname, columns.get(1).getText());
        Assert.assertEquals(lname, columns.get(2).getText());
        Assert.assertEquals(email, columns.get(3).getText());
        WebElement deleteBtn = columns.get(4).findElement(By.xpath("//table[@class='table']/tbody/tr[td='MSzewc']/td/input[@value='Usuń konto']"));
        deleteBtn.click();
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://localhost:8181/WM/faces/account/deleteNewRegisteredAccount.xhtml"));
        
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/account/deleteNewRegisteredAccount.xhtml"));
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Usuń konto']")));
        
        WebElement deleteBtnConfirm = driver.findElement(By.xpath("//input[@value='Usuń konto']"));
        deleteBtnConfirm.click();
        
        new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://localhost:8181/WM/faces/account/listNewAccounts.xhtml"));
        
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/account/listNewAccounts.xhtml"));
        
        try {
            List<WebElement> columns2 = driver.findElements(By.cssSelector("//table[@class='table']/tbody/tr[td='MSzewc']/td"));
            Assert.assertNotEquals(login, columns.get(0).getText());
            Assert.assertNotEquals(fname, columns.get(1).getText());
            Assert.assertNotEquals(lname, columns.get(2).getText());
            Assert.assertNotEquals(email, columns.get(3).getText());
            Assert.fail();
        } catch (NoSuchElementException ex) { 
             
        }
    }
 
 
    @BeforeTest
    public void setupClass() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
 
 
    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
 
}