package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import listeners.CommonListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebDriverFactory {

    private final String browserName = System.getProperty("browser");

    public WebDriver create() {
        WebDriver driver;

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
                break;
            default:
                throw new BrowserNotFoundException(browserName);
        }
        WebDriverListener listener = new CommonListener();

        return new EventFiringDecorator(listener).decorate(driver);
    }

}
