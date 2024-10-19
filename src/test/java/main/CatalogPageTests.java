package main;

import com.google.inject.Inject;
import data.LessonCard;
import org.junit.jupiter.api.Test;
import pages.CoursesPage;
import pages.LessonCardPage;
import java.util.ArrayList;
import java.util.List;


public class CatalogPageTests {
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
        List<LessonCard> allCourses = new ArrayList<>();

        allCourses.addAll(coursesPage
            .open()
            .findCourses(true));

        allCourses.addAll(coursesPage
            .open()
            .findCourses(false));

        coursesPage.validateCourseData(allCourses);
    }
}
