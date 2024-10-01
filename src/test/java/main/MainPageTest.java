package main;

import com.google.inject.Inject;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursesPage;
import pages.LessonCardPage;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
public class MainPageTest {
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
        lessonCardPage.pageHeaderShouldbeSameAs(lessonTitle);
    }

}
