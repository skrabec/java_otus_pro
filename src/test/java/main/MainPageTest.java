package main;

import com.google.inject.Inject;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LessonCardPage;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
public class MainPageTest {
    @Inject
    private MainPage mainPage;

    @Inject
    private LessonCardPage lessonCardPage;

    @Test
    public void mainPageTest() throws InterruptedException {
        String lessonTitle = mainPage
                .open()
                .getLessonTitleByIndex(2);

        mainPage.clickLessonTitleByTitle(lessonTitle);
        lessonCardPage.pageHeaderShouldbeSameAs(lessonTitle);
    }

}
