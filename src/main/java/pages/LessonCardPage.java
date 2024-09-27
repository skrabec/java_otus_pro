package pages;

import annotations.PathTemplate;
import org.openqa.selenium.WebDriver;

@PathTemplate("/lessons/$1")
public class LessonCardPage extends AbstractBasePage<LessonCardPage> {

    public LessonCardPage(WebDriver driver) {
        super(driver);
    }


}
