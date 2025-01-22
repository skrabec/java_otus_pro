package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import listeners.CommonListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    private final String browserName = System.getProperty("browser");

    public WebDriver create() throws MalformedURLException {
        WebDriver driver;
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");


        if (!System.getProperty("remote.url").isEmpty()) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-background-timer-throttling");
            options.addArguments("--disable-backgrounding-occluded-windows");
            options.addArguments("--disable-renderer-backgrounding");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
            options.setCapability("browserVersion", "132");
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{

                put("enableVNC", true);
                /* How to add test badge */
                put("name", "Test badge...");

                /* How to set session timeout */
                put("sessionTimeout", "15m");

                /* How to set timezone */
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});

                /* How to enable video recording */
                put("enableVideo", false);
            }});
            return new RemoteWebDriver(new URL("http://45.132.17.22:4444/wd/hub"), options);
        }

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
