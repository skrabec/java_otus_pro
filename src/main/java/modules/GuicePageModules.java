package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.LessonCardPage;
import pages.MainPage;

public class GuicePageModules extends AbstractModule {

    @Provides
    private WebDriver getDriver() {
        return new WebDriverFactory().create();
    }

    @Singleton
    @Provides
    public LessonCardPage getLessonCardPage() {
        return new LessonCardPage(getDriver());
    }

    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(getDriver());
    }

}
