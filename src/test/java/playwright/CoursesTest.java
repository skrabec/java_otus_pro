package playwright;

import com.google.inject.Inject;
import extensions.junit.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursesPage;

@ExtendWith(UIExtensions.class)
public class CoursesTest {
    @Inject
    private CoursesPage coursesPage;

    @Test
    public void coursesPageTest() {
        coursesPage.open();
        coursesPage.checkBoxChecked("Все направления");
        coursesPage.checkBoxChecked("Любой уровень");
        coursesPage.moveLeftSliderToValue(3);
        coursesPage.moveRightSliderToValue(10);
        coursesPage.validateSliderFilter();
        coursesPage.markCheckBox("Архитектура");
        coursesPage.validateCheckBoxFilter();
        coursesPage.resetFilter();
        coursesPage.validateFilterReset();
    }
}
