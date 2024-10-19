package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import factory.settings.IBrowserSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory {

    private final String browserName = System.getProperty("browser", "chrome");

    public EventFiringWebDriver create() {
        EventFiringWebDriver driver;

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                IBrowserSettings chromeSettings = new ChromeSettings();
                WebDriverManager.chromedriver().setup();
                driver = new EventFiringWebDriver(new ChromeDriver((ChromeOptions) chromeSettings.settings()));
                return driver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new EventFiringWebDriver(new FirefoxDriver());
                return driver;
            default:
                throw new BrowserNotFoundException(browserName);
        }
    }

}
