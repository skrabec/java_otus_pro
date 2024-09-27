package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings implements IBrowserSettings {
    @Override
    public AbstractDriverOptions settings() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--empty-blank");
        chromeOptions.addArguments("--disable-search-engine-choice-screen");

        return chromeOptions;
    }
}
