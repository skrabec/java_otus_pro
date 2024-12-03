package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.CoursesPage;
import pages.LessonCardPage;
import pages.MainPage;
import java.net.MalformedURLException;

public class GuicePageModules extends AbstractModule {

    private final WebDriver driver = new WebDriverFactory().create();

    public GuicePageModules() throws MalformedURLException {
    }

    @Provides
    private WebDriver getDriver() {
        return driver;
    }

    @Singleton
    @Provides
    public LessonCardPage getLessonCardPage() {
        return new LessonCardPage(driver);
    }

    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Singleton
    @Provides
    public CoursesPage getCoursesPage() {
        return new CoursesPage(driver);
    }

}
