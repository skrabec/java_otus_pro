package main;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import pages.CoursesPage;
import pages.MainPage;


public class MainPageTests {
    @Inject
    private MainPage mainPage;

    @Inject
    private CoursesPage coursesPage;

    @Test
    public void openRandomCategoryTest() {
        mainPage
            .open();

    }
}
