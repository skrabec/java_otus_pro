package factory;

import listeners.CommonListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    private final String browserName = System.getProperty("browser");

    public WebDriver create() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");

        // Basic Chrome options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-background-timer-throttling");
        options.addArguments("--disable-backgrounding-occluded-windows");
        options.addArguments("--disable-renderer-backgrounding");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Remote execution
        if (System.getProperty("remote.url") != null && !System.getProperty("remote.url").isEmpty()) {
            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("name", "Test badge...");
            selenoidOptions.put("sessionTimeout", "15m");
            selenoidOptions.put("env", Arrays.asList("TZ=UTC"));
            selenoidOptions.put("enableVideo", false);

            options.setCapability("selenoid:options", selenoidOptions);

            WebDriver driver = new RemoteWebDriver(
                new URL(System.getProperty("remote.url")),
                options
            );

            WebDriverListener listener = new CommonListener();
            return new EventFiringDecorator(listener).decorate(driver);
    }
        // Local execution
        WebDriver driver = new ChromeDriver(options);
        WebDriverListener listener = new CommonListener();
        return new EventFiringDecorator(listener).decorate(driver);

    }
}
