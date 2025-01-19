package main;

import com.google.inject.Inject;
import extensions.UIExtensions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursesPage;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
@Feature("Main Page Tests")
public class MainPageTests {
    @Inject
    private MainPage mainPage;

    @Inject
    private CoursesPage coursesPage;

    @Test
    @Description("Click random category")
    public void openRandomCategoryTest() {
        String categoryName = mainPage
            .open()
            .clickRandomCategory();

        coursesPage.validateCategory(categoryName);
    }
}
