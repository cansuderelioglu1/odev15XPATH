package demoqa.com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class DdemoqaTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/cansu/Documents/WebDriver/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }


    @Test(priority = 0)
    public void testDoubleClickButton() {


        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://demoqa.com/elements");
        WebElement buttons = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div/div[1]/div/ul/li[5]"));
        js.executeScript("arguments[0].scrollIntoView(true);", buttons);
        buttons.click();


        WebElement btnDoubleClick = driver.findElement(By.xpath("//button[text()=\"Double Click Me\"]"));

        js.executeScript("arguments[0].scrollIntoView(true);", btnDoubleClick);

        Actions actions = new Actions(driver);
        actions.doubleClick(btnDoubleClick).perform();

        WebElement result = driver.findElement(By.xpath("//p[@id='doubleClickMessage']"));
        Assert.assertEquals(result.getText(), "You have done a double click");
    }

    @Test(priority = 1)
    public void testClickButton() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://demoqa.com/elements");
        //WebElement buttons=driver.findElement(By.xpath("//*[@id='item-4']"));
        WebElement buttons = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div/div[1]/div/ul/li[5]"));

        js.executeScript("arguments[0].scrollIntoView(true);", buttons);
        buttons.click();

        WebElement btnClick = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[3]/button"));

        js.executeScript("arguments[0].scrollIntoView(true);", btnClick);

        btnClick.click();
//*[@id="dynamicClickMessage"]
        WebElement result = driver.findElement(By.xpath("//p[@id='dynamicClickMessage']"));
        Assert.assertEquals(result.getText(), "You have done a dynamic click");

    }

    @Test(priority = 2)
    public void testAddRecord() {
        driver.get("https://demoqa.com/webtables");
        WebElement btnAdd = driver.findElement(By.xpath("//button[text()=\"Add\"]"));
        btnAdd.click();

        WebElement textFirstName = driver.findElement(By.xpath("//*[@id='firstName']"));
        WebElement textLastName = driver.findElement(By.xpath("//*[@id='lastName']"));
        WebElement textEmail = driver.findElement(By.xpath("//*[@id='userEmail']"));
        WebElement textAge = driver.findElement(By.xpath("//*[@id='age']"));
        WebElement textSalary = driver.findElement(By.xpath("//*[@id='salary']"));
        WebElement textDepartment = driver.findElement(By.xpath("//*[@id='department']"));

        textFirstName.sendKeys("Cansu");
        textLastName.sendKeys("Derelioglu");
        textEmail.sendKeys("cansu@gmail.com");
        textAge.sendKeys("30");
        textSalary.sendKeys("1000");
        textDepartment.sendKeys("Test");

        WebElement btnSubmit = driver.findElement(By.xpath("//*[@id='submit']"));
        btnSubmit.click();

        WebElement btnEdit = driver.findElement(By.xpath("//div[contains(text(), 'Cansu')]//..//div[@class='action-buttons']//span[contains(@title, 'Edit')]"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btnEdit);
        btnEdit.click();


        textSalary = driver.findElement(By.xpath("//*[@id='salary']"));
        textSalary.clear();
        textSalary.sendKeys("2000");

        btnSubmit = driver.findElement(By.xpath("//*[@id='submit']"));
        btnSubmit.click();


        WebElement salaryCell = driver.findElement(By.xpath("//div[contains(text(), 'Cansu')]//following-sibling::div[4]"));
        Assert.assertEquals(salaryCell.getText(), "2000");


    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }

    }

}
