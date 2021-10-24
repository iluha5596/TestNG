import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class Otus {

    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);
    protected static WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);

    }

    @AfterClass
    public void End(){
        if (driver!=null)
            driver.quit();
    }

    private void auth() throws InterruptedException  {
        driver.get("https://otus.ru");
        logger.info("Сайт открыт");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"header2__auth js-open-modal\"]")));
        driver.findElement(By.xpath("//button[@class=\"header2__auth js-open-modal\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@placeholder=\"Электронная почта\"])[3]")));
        driver.findElement(By.xpath("(//input[@placeholder=\"Электронная почта\"])[3]")).sendKeys(LoginPassword.LOGIN);
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(LoginPassword.PASSWORD);
        driver.findElement(By.xpath("(//button[@type=\"submit\"])[3]")).click();
        logger.info("Авторизация прошла");
    }

    private void my() throws InterruptedException  {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href=\"/learning/\"])[1]")));
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Раздел о себе открыт");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title=\"Сохранить и продолжить\"]")));
    }

    private void addMy() throws InterruptedException {
        //Очистка данных
        driver.findElement(By.xpath("//input[@data-title=\"Имя\"]")).clear();
        driver.findElement(By.xpath("//input[@data-title=\"Фамилия\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"blog_name\"]")).clear();
        driver.findElement(By.xpath("//input[@title=\"День рождения\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"company\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"work\"]")).clear();
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
        //Город
        if(!driver.findElement(By.xpath("//button[@title=\"Москва\"]")).getText().contains("Москва")){
            driver.findElement(By.xpath("//div[@class=\"container__col container__col_9 container__col_md-12\"]")).click();
            driver.findElement(By.xpath("//input[@data-title=\"Город\"]/..//div")).click();
            driver.findElement(By.xpath("//button[@title=\"Москва\"]")).click();
        }
        //Уровень английского
        driver.findElement(By.xpath("(//div[@class=\"input input_full lk-cv-block__input lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation\"])[3]")).click();
        driver.findElement(By.xpath("//button[@title=\"Элементарный уровень (Elementary)\"]")).click();
        //Форма работы
        driver.findElement(By.xpath("//input[@title=\"Полный день\"]//..")).click();
        //Контактная информация
        driver.findElement(By.xpath("//span[text() = \"Способ связи\"]")).click();
        driver.findElement(By.xpath("//button[@data-value=\"whatsapp\"]")).click();
        driver.findElement(By.xpath("//input[@name=\"contact-0-value\"]")).sendKeys("+79999999999");
        driver.findElement(By.xpath("//button[text() = \"Добавить\"]")).click();
        driver.findElement(By.xpath("//span[text() = \"Способ связи\"]")).click();
        driver.findElement(By.xpath("(//button[@data-value=\"telegram\"])[2]")).click();
        driver.findElement(By.xpath("//input[@name=\"contact-1-value\"]")).sendKeys("+79999999999");
        //Пол
        driver.findElement(By.xpath("//select[@name=\"gender\"]")).click();
        driver.findElement(By.xpath("//option[@value=\"m\"]")).click();
        //Место работы
        driver.findElement(By.xpath("//input[@id=\"id_company\"]")).sendKeys("ПАО Абсолют банк");
        //Должность
        driver.findElement(By.xpath("//input[@id=\"id_work\"]")).sendKeys("QA Engineer");
        //Сохранение данных
        driver.findElement(By.xpath("//button[@title=\"Сохранить и продолжить\"]")).click();
        logger.info("Данные заполнены");
    }

    private void clear() throws InterruptedException {
        driver.findElement(By.xpath("//input[@title=\"Полный день\"]//..")).click();
        driver.findElement(By.xpath("(//button[text() = \"Удалить\"])[2]")).click();
        driver.findElement(By.xpath("(//button[text() = \"Удалить\"])[4]")).click();
        driver.findElement(By.xpath("//select[@name=\"gender\"]")).click();
        driver.findElement(By.xpath("//option[@value]")).click();
        driver.findElement(By.xpath("//button[@title=\"Сохранить и продолжить\"]")).click();
        logger.info("Данные очищены");
    }

    private void check() throws InterruptedException {
        //Проверка ранее введённых данных в ЛК
        Assert.assertEquals("Илья", driver.findElement(By.xpath("//input[@data-title=\"Имя\"]")).getAttribute("value"));
        logger.info("Имя - ОК");
        Assert.assertEquals("Пантиков", driver.findElement(By.xpath("//input[@data-title=\"Фамилия\"]")).getAttribute("value"));
        logger.info("Фамилия - ОК");
        Assert.assertEquals("05.07.1996", driver.findElement(By.xpath("//input[@title=\"День рождения\"]")).getAttribute("value"));
        logger.info("День рождения - ОК");
        //Страна
        Assert.assertEquals("Россия", driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText());
        logger.info("Страна - ОК");
        //Город
        Assert.assertEquals("Москва", driver.findElement(By.xpath("//input[@data-title=\"Город\"]/..//div")).getText());
        logger.info("Город - ОК");
        //Уровень английского
        Assert.assertEquals("Элементарный уровень (Elementary)", driver.findElement(By.xpath("//input[@data-title=\"Уровень знания английского языка\"]/..//div")).getText());
        logger.info("Уровень английского - ОК");
        //Почта
        Assert.assertEquals("pantik96@mail.ru", driver.findElement(By.xpath("(//input[@name=\"email\"])[1]")).getAttribute("value"));
        logger.info("Почта - ОК");
        //Способ связи
        Assert.assertEquals("Тelegram", driver.findElement(By.xpath("(//div[@class=\"input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation\"])[1]")).getText());
        logger.info("Телеграмм - ОК");
        Assert.assertEquals("WhatsApp", driver.findElement(By.xpath("(//div[@class=\"input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation\"])[2]")).getText());
        logger.info("WhatsApp - ОК");
        Assert.assertEquals("+79999999999", driver.findElement(By.xpath("//input[@name=\"contact-0-value\"]")).getAttribute("value"));
        logger.info("Номер Tg - ОК");
        Assert.assertEquals("+79999999999", driver.findElement(By.xpath("//input[@name=\"contact-1-value\"]")).getAttribute("value"));
        logger.info("Номер WhatsApp - ОК");
        //Пол
        Assert.assertEquals("m", driver.findElement(By.xpath("//select[@name=\"gender\"]")).getAttribute("value"));
        logger.info("Пол - ОК");
        //Компания
        Assert.assertEquals("ПАО Абсолют банк", driver.findElement(By.xpath("//input[@id=\"id_company\"]")).getAttribute("value"));
        logger.info("Компания - ОК");
        //Должность
        Assert.assertEquals("QA Engineer", driver.findElement(By.xpath("//input[@id=\"id_work\"]")).getAttribute("value"));
        logger.info("Должность - ОК");
        //Проверка чекбокса
        if (driver.findElement(By.xpath("//input[@title=\"Полный день\"]//..")).isSelected())
        {
            logger.info("Неверный данные в Готовность к переезду");
        }
        if (driver.findElement(By.xpath("//input[@title=\"Полный день\"]//..")).isSelected()) {
            logger.info("Неверный данные в Форма работы");
        }
        logger.info("Данные заполнены верно");

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
    public void CheckPersonalArea() throws InterruptedException{
        //Авторизация
        auth();
        //Переход в раздел о себе
        my();
        //Проверка данных
        check();
        //Очистка данных
        clear();

    }





}
