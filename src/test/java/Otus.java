import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        driver = new ChromeDriver();
        logger.info("Driver up");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

//    @AfterClass
//    public void End(){
//        if (driver!=null)
//            driver.quit();
//    }

    private void auth() throws InterruptedException  {
        driver.get("https://otus.ru");
        logger.info("Site open");
            driver.findElement(By.xpath("//button[@class=\"header2__auth js-open-modal\"]")).click();
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[2]/input")).sendKeys("pantik96@mail.ru");
            driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("qwerty123");
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[4]/button")).click();
            logger.info("Authorization path");

    }

    private void my() throws InterruptedException  {
        Thread.sleep(2000);
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Paragraph My open");
        Thread.sleep(1000);

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
        //Страна
        if(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText().contains("Россия")) {
            driver.findElement(By.xpath("//div[@class=\"container__col container__col_9 container__col_md-12\"]")).click();
            driver.findElement(By.xpath("(//div[@class=\"input input_full lk-cv-block__input lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation\"])[1]")).click();
            driver.findElement(By.xpath("//button[@title=\"Россия\"]")).click();
        }
        driver.findElement(By.xpath("//input[@data-title=\"Город\"]/..//div")).click();
        driver.findElement(By.xpath("//button[@title=\"Москва\"]")).click();
        driver.findElement(By.xpath("(//div[@class=\"input input_full lk-cv-block__input lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation\"])[3]")).click();
        driver.findElement(By.xpath("//button[@title=\"Элементарный уровень (Elementary)\"]")).click();
        driver.findElement(By.xpath("//input[@title=\"Полный день\"]//..")).click();
        driver.findElement(By.xpath("//span[text() = \"Способ связи\"]")).click();
        driver.findElement(By.xpath("//button[@data-value=\"whatsapp\"]")).click();
        driver.findElement(By.xpath("//input[@name=\"contact-0-value\"]")).sendKeys("+79999999999");
        driver.findElement(By.xpath("//button[text() = \"Добавить\"]")).click();
        driver.findElement(By.xpath("(//span[@class=\"placeholder\"])[2]")).click();
        driver.findElement(By.xpath("(//button[@data-value=\"telegram\"])[2]")).click();
        driver.findElement(By.xpath("//input[@name=\"contact-1-value\"]")).sendKeys("+79999999999");
        driver.findElement(By.xpath("//select[@name=\"gender\"]")).click();
        driver.findElement(By.xpath("//option[@value=\"m\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"id_company\"]")).sendKeys("ПАО Абсолют банк");
        driver.findElement(By.xpath("//input[@id=\"id_work\"]")).sendKeys("QA Engineer");
        driver.findElement(By.xpath("//button[@title=\"Сохранить и продолжить\"]")).click();

    }

    @Test
    public void PersonalArea() throws InterruptedException  {
        //Авторизация
        auth();
        //Переход в раздел о себе
        my();
        //Заполнение данных
        addMy();

    }

    @Test
    public void window() throws InterruptedException {
        driver.manage().window().maximize();
        logger.info(driver.manage().window().getSize());
        Thread.sleep(1000);

        driver.manage().window().setSize(new Dimension(700,700));
        logger.info(driver.manage().window().getPosition());
        Thread.sleep(1000);

        driver.manage().window().setSize(new Dimension(1000,700));
        Point point = driver.manage().window().getPosition();

        point.x = point.x + 500;
        driver.manage().window().setPosition(point);
        Thread.sleep(1000);

        point.y = point.y +500;
        driver.manage().window().setPosition(point);
        Thread.sleep(1000);

        point.x = point.x -500;
        driver.manage().window().setPosition(point);
        Thread.sleep(1000);
    }

    @Test
    public void headless() throws InterruptedException{
        auth();
    }

}
