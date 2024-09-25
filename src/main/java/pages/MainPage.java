package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Path("/")
public class MainPage extends AbstractBasePage<MainPage>{

    public MainPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//section[./h2]//a[contains(@href, '/lessons')]")
    private List<WebElement> lessonItems;

    public String getLessonTitleByIndex(int index) {
        return lessonItems.get(--index).findElement(By.xpath(".//h5")).getText();
    }

//    public LessonCardPage clickLessonTitleByTitle (String title) {
//
//    }

}
