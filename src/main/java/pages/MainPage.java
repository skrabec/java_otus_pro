package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Path("/catalog/courses")
public class MainPage extends AbstractBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//section[2]//a[contains(@href, '/lessons')]")
    private List<WebElement> lessonItems;


    public String getLessonTitleByIndex(int index) {
        return lessonItems.get(--index).findElement(By.xpath(".//h6")).getText();
    }

    public void clickLessonTitleByTitle(String title) {
        lessonItems.stream()
                .filter(lessonItem -> lessonItem.getText().contains(title))  // Filter based on title
                .findFirst()  // Find the first matching lesson item
                .ifPresent(lessonItem -> {
                    actions.doubleClick(lessonItem).build().perform();  // Perform double click on the found element
                });
    }
}
