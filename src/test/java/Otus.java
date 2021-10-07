import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Otus {

    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);
    protected static WebDriver driver;

    @BeforeClass
    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @AfterClass
    public void End(){
        if (driver!=null)
            driver.quit();
    }


    private void auth() throws InterruptedException  {
        driver.get("https://otus.ru");
        logger.info("Сайт открыт");
        try {
            Thread.sleep(20);
            driver.findElement(By.xpath("//button[@class=\"header2__auth js-open-modal\"]")).click();
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[2]/input")).sendKeys("pantik96@mail.ru");
            driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("qwerty123");
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[4]/button")).click();
            logger.info("Авторизация прошла успешно");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void my() throws InterruptedException  {
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Раздел о себе открыт");

    }

    @Test
    public void PersonalArea() throws InterruptedException  {
        auth();
        my();
    }

    private class Authorization extends Exception {
    }
}
