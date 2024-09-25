package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

public class GuicePageModules extends AbstractModule {

    private WebDriver driver = new ChromeDriver();

    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

}
