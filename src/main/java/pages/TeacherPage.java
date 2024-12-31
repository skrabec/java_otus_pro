package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Page;
import java.util.regex.Pattern;

public class TeacherPage extends AbsBasePage {
    public TeacherPage(Page page) {
        super(page);
    }

    public void checkHeaderPage(String expectedHeader) {
        assertThat(page.getByText(Pattern.compile(String.format("^%s$", expectedHeader)))).isVisible();
    }

    public void checkTeacherNamePresent(String teacherName) {
        assertThat(page.getByText(Pattern.compile(String.format("^%s$", teacherName)))).isVisible();
    }
}
