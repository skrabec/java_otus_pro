package pages;

import annotations.Path;
import annotations.PathTemplate;
import common.AbstractCommon;
import exceptions.InvalidPathException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractBasePage<T> extends AbstractCommon<T> {

    private final String baseUrl =
            !System.getProperty("base.url").endsWith("/")
                    ? System.getProperty("base.url")
                    : System.getProperty("base.url").substring(0, System.getProperty("base.url").length() - 1);

    public AbstractBasePage(WebDriver driver){
        super(driver);
    }

    //  @FindBy(tagName = "h1")
    //  private WebElement headerPage;

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

    public T open(String... data) {
        PathTemplate pathTemplate = getClass().getAnnotation(PathTemplate.class);

        if (pathTemplate == null) {
            throw new InvalidPathException();
        }

        String path = pathTemplate.value();
        for (int i = 0; i < data.length; i++) {
            path = path.replace("$" + (i + 1), data[i]);
        }

        driver.get(baseUrl + path);

        return (T) this;
    }

    public T pageHeaderShouldbeSameAs(String title) {
        assertThat(findBy(By.tagName("h1")).getText())
                .as("Header of page should be {}", title)
                .isEqualTo(title);

        return (T) this;
    }
}
