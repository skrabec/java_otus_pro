package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.GuicePageModules;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;

public class UIExtensions implements BeforeEachCallback, AfterEachCallback {

    private Injector injector;

    @Override
    public void afterEach(ExtensionContext context) {
        context.getTestInstance()
                .ifPresent(instance -> {
                    WebDriver driver = injector.getProvider(WebDriver.class).get();
                    if (driver != null) {
                        driver.quit();
                    }
                });
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        context.getTestInstance()
                .ifPresent(instance -> {
                    try {
                        injector = Guice.createInjector(new GuicePageModules());
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    injector.injectMembers(instance);
                });
    }
}
