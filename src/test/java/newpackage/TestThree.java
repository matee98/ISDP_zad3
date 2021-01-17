package newpackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;


public class TestThree {
    private WebDriver driver;
    private String adress;
    private String login;
    private String password;
    private String newPassword;
    private WebDriverWait wait;
    
    @BeforeTest
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        driver = new FirefoxDriver(firefoxOptions);
        adress = "https://localhost:8181/WM/faces/main/index.xhtml";
        login = "JDoe";
        password = "P@ssw0rd";
        newPassword = "newP@ssw0rd";
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void UseCaseTest() throws InterruptedException {

        //logowanie
        driver.get(adress);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logowanie")));
        driver.findElement(By.linkText("Logowanie")).click();
        WebElement loginField = driver.findElement(By.name("j_username"));
        loginField.clear();
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.clear();
        passwordField.sendKeys(password);
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/common/signIn.xhtml"));
        driver.findElement(By.xpath("//input[@value='Zaloguj']")).click();        
        //zmiana hasla
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Ustawienia")));
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/main/index.xhtml"));
        driver.findElement(By.linkText("Ustawienia")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../common/changeMyPassword.xhtml']")));
        driver.findElement(By.linkText("Zmiana hasła")).click();
        WebElement oldPasswordField = driver.findElement(By.name("ChangeMyPasswordForm:oldPassword"));
        oldPasswordField.clear();
        oldPasswordField.sendKeys(password);
        WebElement newPasswordField = driver.findElement(By.name("ChangeMyPasswordForm:newPassword"));
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
        WebElement newPasswordRepeat = driver.findElement(By.name("ChangeMyPasswordForm:newPasswordRepeat"));
        newPasswordRepeat.clear();
        newPasswordRepeat.sendKeys(newPassword);
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/common/changeMyPassword.xhtml"));
        driver.findElement(By.name("ChangeMyPasswordForm:j_idt32")).click();
        //wylogowanie
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Wylogowanie")));
        driver.findElement(By.linkText("Wylogowanie")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/common/logout.xhtml"));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("j_idt26:j_idt30")));
        driver.findElement(By.name("j_idt26:j_idt30")).click();
        //logowanie
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logowanie")));
        driver.findElement(By.linkText("Logowanie")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/common/signIn.xhtml"));
        loginField = driver.findElement(By.name("j_username"));
        loginField.clear();
        loginField.sendKeys(login);
        passwordField = driver.findElement(By.name("j_password"));
        passwordField.clear();
        passwordField.sendKeys(newPassword);
        driver.findElement(By.xpath("//input[@value='Zaloguj']")).click();
        //zmiana hasla na pierwotna
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Ustawienia")));
        driver.findElement(By.linkText("Ustawienia")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../common/changeMyPassword.xhtml']")));
        driver.findElement(By.linkText("Zmiana hasła")).click();
        oldPasswordField = driver.findElement(By.name("ChangeMyPasswordForm:oldPassword"));
        oldPasswordField.clear();
        oldPasswordField.sendKeys(newPassword);
        newPasswordField = driver.findElement(By.name("ChangeMyPasswordForm:newPassword"));
        newPasswordField.clear();
        newPasswordField.sendKeys(password);
        newPasswordRepeat = driver.findElement(By.name("ChangeMyPasswordForm:newPasswordRepeat"));
        newPasswordRepeat.clear();
        newPasswordRepeat.sendKeys(password);
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/common/changeMyPassword.xhtml"));
        driver.findElement(By.name("ChangeMyPasswordForm:j_idt32")).click();
        //wylogowanie
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Wylogowanie")));
        driver.findElement(By.linkText("Wylogowanie")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/WM/faces/common/logout.xhtml"));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("j_idt26:j_idt30")));
        driver.findElement(By.name("j_idt26:j_idt30")).click();
        
      
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
