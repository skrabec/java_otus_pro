package main;

import com.google.inject.Inject;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
public class MainPageTest {
    @Inject
    private MainPage mainPage;

    @Test
    public void mainPageTest() {
        String lessonTitle = mainPage
                .open()
                .getLessonTitleByIndex(1);

        mainPage.clickLessonTitleByTitle(lessonTitle)
                .pageHeaderShouldbeSameAs(lessonTitle);
    }

}
