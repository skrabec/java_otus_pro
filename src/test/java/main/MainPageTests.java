package main;

import com.google.inject.Inject;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursesPage;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
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
