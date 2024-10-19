package factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class FirefoxSettings implements IBrowserSettings {
    @Override
    public AbstractDriverOptions settings() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--empty-blank");
        firefoxOptions.addArguments("--disable-search-engine-choice-screen");

        return firefoxOptions;
    }
}
