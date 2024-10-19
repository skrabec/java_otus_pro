package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import scopes.GuiceScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("/online")
public class TrainingCoursesPage extends AbstractBasePage<TrainingCoursesPage> {

    @FindBy(css = ".lessons__new-item-container")
    private List<WebElement> coursesTile;
    private String courseNameSelector = ".lessons__new-item-title";
    private String coursePriceSelector = "div.lessons__new-item-price";
    @Inject
    public TrainingCoursesPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public List<WebElement> getCourseItems() {
        return List.copyOf(coursesTile);
    }

    public Map<String, Integer> getCourseTitlesAndPrices(List<WebElement> courseItems) {
        Map<String, Integer> result = new HashMap<>();
        for (WebElement webElement : courseItems) {
            String courseTitle = webElement.findElement(By.cssSelector(courseNameSelector)).getText();
            Integer price = Integer.valueOf(webElement.findElement(By.cssSelector(coursePriceSelector)).getText().replace(" ₽", ""));
            result.put(courseTitle, price);
        }
        return result;
    }

    public void printCheapiestAndTheMostExpensiveCourse() {
        List<WebElement> courseTile = getCourseItems();
        Map<String, Integer> courseNamesAndPrices = getCourseTitlesAndPrices(courseTile);

        Optional<Map.Entry<String, Integer>> cheapestCourse = courseNamesAndPrices.entrySet()
            .stream()
            .min(Map.Entry.comparingByValue());

        Optional<Map.Entry<String, Integer>> mostExpensiveCourse = courseNamesAndPrices.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue());

        cheapestCourse.ifPresent(entry ->
            System.out.println("Самый дешёвый курс: Название - " + entry.getKey() + " / Стоимость - " + entry.getValue() + " ₽"));

        mostExpensiveCourse.ifPresent(entry ->
            System.out.println("Самый дорогой курс: Название - " + entry.getKey() + " / Стоимость - " + entry.getValue() + " ₽"));
    }


}
