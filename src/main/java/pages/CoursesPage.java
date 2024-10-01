package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Path("/catalog/courses")
public class CoursesPage extends AbstractBasePage<CoursesPage> {
    @FindBy(xpath = "//section[2]//a[contains(@href, '/lessons')]")
    private List<WebElement> lessonItems;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public String getLessonTitleByIndex(int index) {
        return lessonItems.get(--index).findElement(By.xpath(".//h6")).getText();
    }

    public void clickLessonTitleByTitle(String title) {
        lessonItems.stream()
                .filter(lessonItem -> lessonItem.getText().contains(title))
                .findFirst()
                .ifPresent(lessonItem -> {
                    actions.doubleClick(lessonItem).build().perform();
                });
    }


}
