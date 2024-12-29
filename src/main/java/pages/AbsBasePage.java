package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import annotations.Path;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public abstract class AbsBasePage {
    protected Page page;
    private String baseUrl = System.getProperty("base.url");

    public AbsBasePage(Page page) {
        this.page = page;
    }

    private String getPath() {
        Class clazz = this.getClass();
        Path path = (Path) clazz.getDeclaredAnnotation(Path.class);
        return path.value();
    }

    public void open() {
        page.navigate(baseUrl + getPath());
    }

    public void checkBoxChecked(String checkBoxName) {
        assertThat(page.getByRole(AriaRole.CHECKBOX,
            new Page.GetByRoleOptions().setName(checkBoxName)))
            .isChecked();
    }

    public void markCheckBox(String name) {
        page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName(name))
            .check();
    }
}
