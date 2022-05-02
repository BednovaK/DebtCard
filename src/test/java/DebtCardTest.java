import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebtCardTest {

        WebDriver driver;

        @BeforeAll
        public static void setUp() {
            WebDriverManager.chromedriver().setup();
        }

        @BeforeEach
        public void setUp1() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.get("http://localhost:9999");
        }

        @AfterEach
        public void close() {
            driver.quit();
            driver = null;
        }

        @Test
        public void shouldOpenBrowser() {
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванова Екатерина");
            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79152225458");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click(); // чекбокс
            driver.findElement(By.tagName("button")).click(); // Отправить

            String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim()); // trim-удаляет лишние пробелы и с лева и с права
        }
}
