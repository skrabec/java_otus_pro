package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

@Path("/")
public class MainPage extends AbsBasePage {
    private Locator teacherList = page.locator("section div > a[href*='instructors']:not(a[class])");

    public MainPage(Page page) {
        super(page);
    }

    public String getTeacherName(int index) {
        return teacherList.all().get(--index).allTextContents().get(0);
    }

    public void clickTeacherByName(String name) {
        teacherList.filter(new Locator.FilterOptions().setHasText(name)).click();
    }

}
