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
        logger.info("Driver up");
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
        logger.info("Site open");
        try {
            Thread.sleep(20);
            driver.findElement(By.xpath("//button[@class=\"header2__auth js-open-modal\"]")).click();
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[2]/input")).sendKeys("pantik96@mail.ru");
            driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("qwerty123");
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[4]/button")).click();
            logger.info("Authorization path");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void my() throws InterruptedException  {
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Paragraph My open");

    }

    private void addMy() throws InterruptedException {
        //Очистка данных
        driver.findElement(By.xpath("//input[@data-title=\"Имя\"]")).clear();
        driver.findElement(By.xpath("//input[@data-title=\"Фамилия\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"blog_name\"]")).clear();
        driver.findElement(By.xpath("//input[@title=\"День рождения\"]")).clear();
        //Заполнение данных
        driver.findElement(By.xpath("//input[@data-title=\"Имя\"]")).sendKeys("Илья");
        driver.findElement(By.xpath("//input[@data-title=\"Фамилия\"]")).sendKeys("Пантиков");
        driver.findElement(By.xpath("//input[@name=\"blog_name\"]")).sendKeys("Ilya");
        driver.findElement(By.xpath("//input[@title=\"День рождения\"]")).sendKeys("05.07.1996");
        driver.findElement(By.xpath("//button[@title=\"Россия\"]")).click();
        driver.findElement(By.xpath("//button[@title=\"Москва\"]")).click();
        driver.findElement(By.xpath("//button[@title=\"Элементарный уровень (Elementary)\"]")).click();
        driver.findElement(By.xpath("//input[@title=\"Полный день\"]//..")).click();
        driver.findElement(By.xpath("//button[@data-value=\"whatsapp\"]")).click();
        driver.findElement(By.xpath("//input[@name=\"contact-0-value\"]")).sendKeys("+79999999999");
        driver.findElement(By.xpath("//button[text() = \"Добавить\"]")).click();
        driver.findElement(By.xpath("(//button[@data-value=\"telegram\"])[2]")).click();
        driver.findElement(By.xpath("(//button[@data-value=\"telegram\"])[2]")).sendKeys("+79999999999");

    }

    @Test
    public void PersonalArea() throws InterruptedException  {
        //Авторизация
        auth();
        //Переход в раздел о себе
        Thread.sleep(20);
        my();
        //Заполнение данных
        Thread.sleep(10);
        addMy();

    }

    private class Authorization extends Exception {
    }
}
