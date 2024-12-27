package playwright;

import com.google.inject.Inject;
import extensions.junit.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ClickHousePage;

@ExtendWith(UIExtensions.class)
public class ClickHouseTest {

    @Inject
    private ClickHousePage clickHousePage;

    String teacherName = "Алексей Железной";

    @Test
    public void clickHouseTest() {
        clickHousePage.open();
        clickHousePage.scrollInstructorsListByDragAndDrop(400);
        clickHousePage.checkIfInstructorSlidesWereRotated();
        clickHousePage.scrollInstructorsListByDragAndDrop(-400);
        clickHousePage.clickOnTheSlideByName(teacherName);
        clickHousePage.checkTeacherNamePresent(teacherName);
        clickHousePage.clickOnArrowRight();
        clickHousePage.validateDifferentTeacherName(teacherName);
        clickHousePage.clickOnArrowLeft();
        clickHousePage.checkTeacherNamePresent(teacherName);
    }
}
