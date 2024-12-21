package pages;

import annotations.Path;
import com.microsoft.playwright.Page;

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
}
