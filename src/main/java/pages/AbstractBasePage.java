package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import com.google.inject.Inject;
import exceptions.InvalidPathException;
import org.openqa.selenium.By;
import pageobject.AbstractCommon;
import scopes.GuiceScoped;

public abstract class AbstractBasePage<T> extends AbstractCommon<T> {

    private final String baseUrl = System.getProperty("base.url", "https://otus.ru");

    @Inject
    public AbstractBasePage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    private String getPath(){
        Path path = getClass().getAnnotation(Path.class);
        if (path == null) {
            throw new InvalidPathException();
        }
    return path.value().startsWith("/") ? path.value(): "/" + path.value();
    }

    public T open() {
        driver.get(baseUrl + getPath());
        return (T) this;
    }

    public void pageHeaderShouldBeSameAs(String title) throws InterruptedException {
        Thread.sleep(1000);
        assertThat(findBy(By.tagName("h1")).getText())
                .as("Header of page should be {}", title)
                .isEqualTo(title);
    }
}
