package pages;

import annotations.Path;
import common.AbstractCommon;
import exceptions.InvalidPathException;
import org.openqa.selenium.WebDriver;

public abstract class AbstractBasePage<T> extends AbstractCommon<T> {

    private final String BASE_URL =
            !System.getProperty("base.url").endsWith("/") ?
                    System.getProperty("base.url"):
                    System.getProperty("base.url").substring(0, System.getProperty("base.url").length() - 1);

    public AbstractBasePage(WebDriver driver){
        super(driver);
    }

    private String getPath(){
        Path path = getClass().getAnnotation(Path.class);
        if (path == null) {
            throw new InvalidPathException();
        }
    return path.value().startsWith("/") ? path.value(): "/" + path.value();
    }

    public T open() {
        driver.get(BASE_URL + getPath());
        return (T) this;
    }

}
