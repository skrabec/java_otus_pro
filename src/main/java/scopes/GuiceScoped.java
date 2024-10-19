package scopes;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.util.Locale;

@ScenarioScoped
public class GuiceScoped {
    public EventFiringWebDriver driver = null;

    public void newBrowser(String browserName) {
        System.setProperty("browser", browserName.toLowerCase(Locale.ROOT));
        this.driver = new WebDriverFactory().create();
    }


}
