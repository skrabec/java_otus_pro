package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import factory.settings.FirefoxSettings;
import factory.settings.IBrowserSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private final String browserName = System.getProperty("browser", "chrome");

    public WebDriver create() {
        WebDriver driver;

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                IBrowserSettings chromeSettings = new ChromeSettings();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
                return driver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver((FirefoxOptions) new FirefoxSettings().settings());
                return driver;
            default:
                throw new BrowserNotFoundException(browserName);
        }
    }

}
