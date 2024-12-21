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

    @Test
    public void clickHouseTest() {
        clickHousePage.open();
        //clickHousePage.scrollInstructorsListByDragAndDrop();
        clickHousePage.clickOnTheSlideByName("Алексей Железной");
        clickHousePage.checkTeacherNamePresent("Алексей Железной");
        clickHousePage.clickOnArrow();
    }
}
