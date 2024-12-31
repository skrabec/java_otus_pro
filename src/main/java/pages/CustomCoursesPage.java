package pages;

import annotations.Path;
import com.microsoft.playwright.Page;

@Path("/custom_courses")
public class CustomCoursesPage extends AbsBasePage {
    public CustomCoursesPage(Page page) {
        super(page);
    }


    public void clickOnDirectionProgramming() {
        page.locator("//a[@href='https://otus.ru/categories/programming']").click();
    }
}
