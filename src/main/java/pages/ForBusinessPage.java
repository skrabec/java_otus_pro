package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import annotations.Path;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

@Path("/uslugi-kompaniyam")
public class ForBusinessPage extends AbsBasePage {
    private Page customCoursesPage;

    public ForBusinessPage(Page page) {
        super(page);
    }

    public void validateCustomCoursesPage(String name, String header) {
        customCoursesPage = page.waitForPopup(() -> {
            page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(name)).click();
        });
        customCoursesPage.waitForLoadState();
        assertThat(customCoursesPage).hasTitle(header);
        assertThat(customCoursesPage.locator("//a[contains(@class, 'tn-atom') and contains(@href, 'categories')]")).hasCount(6);
    }

    public void clickCategory(String categoryName) {
        customCoursesPage.locator(String.format("//a[contains(@href, 'categories/%s')]", categoryName)).click();
    }

    public void checkCategoryChosen(String name) {
        assertThat(customCoursesPage.getByRole(AriaRole.CHECKBOX,
            new Page.GetByRoleOptions().setName(name)))
            .isChecked();
    }
}
