package main;

import com.google.inject.Inject;
import data.LessonCard;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursesPage;
import pages.LessonCardPage;
import pages.MainPage;
import java.util.List;
import java.util.Map;

@ExtendWith(UIExtensions.class)
public class Assignment1Test {
    @Inject
    private MainPage mainPage;

    @Inject
    private LessonCardPage lessonCardPage;

    @Inject
    private CoursesPage coursesPage;

    @Test
    public void coursesPageTest() throws InterruptedException {
        String lessonTitle = coursesPage
                .open()
                .getLessonTitleByIndex(2);

        coursesPage.clickLessonTitleByTitle(lessonTitle);
        lessonCardPage.pageHeaderShouldBeSameAs(lessonTitle);
    }

    @Test
    public void searchEarliestAndLatestCoursesTest() {
        Map<String, List<LessonCard>> coursesByDate = coursesPage
            .open()
            .findEarliestAndLatestCourses();

        coursesPage.validateCourseData(coursesByDate);
    }

    @Test
    public void openRandomCategoryTest() {
        String categoryName = mainPage
            .open()
            .clickRandomCategory();

        coursesPage.validateCategory(categoryName);
    }
}
