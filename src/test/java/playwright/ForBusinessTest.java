package playwright;

import com.google.inject.Inject;
import extensions.junit.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursesPage;
import pages.ForBusinessPage;

@ExtendWith(UIExtensions.class)
public class ForBusinessTest {
    @Inject
    private ForBusinessPage forBusinessPage;

    @Inject
    private CoursesPage coursesPage;

    @Test
    public void forBusinessPageTest() {
        forBusinessPage.open();
        forBusinessPage.validateCustomCoursesPage("Подробнее",
            "Разработка индивидуальных программ обучения для бизнеса");

        forBusinessPage.clickCategory("programming");
        forBusinessPage.checkCategoryChosen("Программирование");
        System.out.println("Done");
    }

}
