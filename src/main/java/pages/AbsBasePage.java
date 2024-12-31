package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import annotations.Path;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public abstract class AbsBasePage {
    protected Page page;
    private String baseUrl = System.getProperty("base.url");
    private String email = System.getProperty("email");
    private String password = System.getProperty("password");

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


    public void inputEmail() {
        page.locator("//input[@name='email']").fill(email);
    }

    public void inputPassword() {
        page.locator("//input[@type='password']").fill(password);

    }

    public void login() {
        inputEmail();
        inputPassword();
        page.locator("#__PORTAL__ button[type='button']").getByText("Войти").click();
    }
}
