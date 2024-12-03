package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import listeners.CommonListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import java.net.MalformedURLException;

public class WebDriverFactory {

    private final String browserName = System.getProperty("browser");

    public WebDriver create() throws MalformedURLException {
        WebDriver driver;

        //if (!System.getProperty("remote.url").isEmpty()) {
        //    ChromeOptions options = new ChromeOptions();
        //    options.setCapability("browserVersion", "128.0");
        //    options.setCapability("selenoid:options", new HashMap<String, Object>() {{
        //        put("name", "Test badge");
        //   }});
        //    return new RemoteWebDriver(new URL(System.getProperty("remote.url")), options);
        //}

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
