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


public class TestTwo {
    private WebDriver driver;
    private String adress;
    private String login;
    private String password;
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
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void UseCaseTest() throws InterruptedException {

        //logowanie
        driver.get(adress);
        driver.findElement(By.linkText("Logowanie")).click();
        WebElement loginField = driver.findElement(By.name("j_username"));
        loginField.clear();
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Zaloguj']")).click();

        //wyswietlenie towarow
        //driver.findElement(By.xpath ("//a[@href='../stock/listStock.xhtml']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Zapas magazynowy")));
        driver.findElement(By.linkText("Zapas magazynowy")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../stock/listStock.xhtml']")));
        driver.findElement(By.linkText("Wyświetlanie stanu")).click();
        driver.findElement(By.name("j_idt26:productSymbol")).click();
        driver.findElement(By.xpath("//option[@value='9788374802789']")).click();
        driver.findElement(By.name("j_idt26:j_idt31")).click();

        //test warunkow poczatkowych
        List<WebElement> valuesBefore = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[td='Gandalf Sp. z o.o.']/td"));

        Assert.assertEquals(valuesBefore.get(0).getText(), "Gandalf Sp. z o.o.");
        Assert.assertEquals(valuesBefore.get(1).getText(), "15");
        Assert.assertEquals(valuesBefore.get(2).getText(), "6915");
        Assert.assertEquals(valuesBefore.get(3).getText(), "AA-01-04-01");
        Assert.assertEquals(valuesBefore.get(4).getText(), "15000");

        //przeniesienie towaru
        driver.findElement(By.name("j_idt26:j_idt33:0:onlyWarehouse:j_idt46")).click();
        driver.findElement(By.name("MoveStockForm:quantityToIssue")).sendKeys("15");
        driver.findElement(By.name("MoveStockForm:locationTo")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='AA-01-02-02']")));
        driver.findElement(By.xpath("//option[@value='AA-01-02-02']")).click();
        driver.findElement(By.name("MoveStockForm:j_idt35")).click();


        //test czy towar został przeniesiony
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Zapas magazynowy")));
        driver.findElement(By.linkText("Zapas magazynowy")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../stock/listStock.xhtml']")));
        driver.findElement(By.linkText("Wyświetlanie stanu")).click();
        driver.findElement(By.name("j_idt26:productSymbol")).click();
        driver.findElement(By.xpath("//option[@value='9788374802789']")).click();
        driver.findElement(By.name("j_idt26:j_idt31")).click();

        //WebElement tabela = driver.findElement(By.xpath("//table[@class='table']"));
        List<WebElement> valuesAfter = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[td='Gandalf Sp. z o.o.']/td"));

        Assert.assertEquals(valuesAfter.get(0).getText(), "Gandalf Sp. z o.o.");
        Assert.assertEquals(valuesAfter.get(1).getText(), "15");
        Assert.assertEquals(valuesAfter.get(2).getText(), "6915");
        Assert.assertEquals(valuesAfter.get(3).getText(), "AA-01-02-02");
        Assert.assertEquals(valuesAfter.get(4).getText(), "30000");

        //ponowne przeniesienie zgodne ze stanem początkowym
        driver.findElement(By.name("j_idt26:j_idt33:0:onlyWarehouse:j_idt46")).click();
        driver.findElement(By.name("MoveStockForm:quantityToIssue")).sendKeys("15");
        driver.findElement(By.name("MoveStockForm:locationTo")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='AA-01-04-01']")));
        driver.findElement(By.xpath("//option[@value='AA-01-04-01']")).click();
        driver.findElement(By.name("MoveStockForm:j_idt35")).click();
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
