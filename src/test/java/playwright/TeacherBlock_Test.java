package playwright;

import com.google.inject.Inject;
import extensions.junit.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;
import pages.TeacherPage;

@ExtendWith(UIExtensions.class)
public class TeacherBlock_Test {

    @Inject
    private MainPage mainPage;

    @Inject
    private TeacherPage teacherPage;

    @Test
    public void clickTeacherItem() {
        mainPage.open();
        String teacherName = mainPage.getTeacherName(1);
        mainPage.clickTeacherByName(teacherName);

        teacherPage.checkHeaderPage("Преподаватели");
        teacherPage.checkTeacherNamePresent(teacherName);
    }

}
