package extensions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import pages.ClickHousePage;
import pages.CoursesPage;
import pages.MainPage;
import pages.TeacherPage;

public class GuicePageModule extends AbstractModule {
    private Page page;
    private BrowserContext context;

    public GuicePageModule() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext context = browser.newContext();

        context
            .tracing()
            .start(new Tracing.StartOptions()
                .setScreenshots(false)
                .setSnapshots(false)
                .setSources(false));

        this.page = context.newPage();
        this.context = context;
    }

    @Singleton
    @Provides
    public BrowserContext getContext() {
        return context;
    }


    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(page);
    }

    @Singleton
    @Provides
    public TeacherPage getTeacherPage() {
        return new TeacherPage(page);
    }

    @Singleton
    @Provides
    public ClickHousePage getClickHousePage() {
        return new ClickHousePage(page);
    }

    @Singleton
    @Provides
    public CoursesPage getCoursesPage() {
        return new CoursesPage(page);
    }

}
