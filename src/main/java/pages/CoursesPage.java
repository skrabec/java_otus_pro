package pages;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import java.util.List;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage {
    public CoursesPage(Page page) {
        super(page);
    }

    Locator locator = page.locator("//h6");
    List<String> allCoursesBeforeFiltering = locator.allTextContents();

    Locator leftSlider = page.locator("div[role='slider'][aria-valuenow='0']");
    Locator rightSlider = page.locator("div[role='slider'][aria-valuenow='15']");

    public void moveLeftSliderToValue(double targetValue) {
        targetValue++;
        BoundingBox leftSliderBox = leftSlider.boundingBox();
        leftSlider.click();
        leftSlider.hover();
        page.mouse().down();
        page.mouse().move(leftSliderBox.x + 14.4 * targetValue, leftSliderBox.width / 2);
        page.mouse().up();
    }

    public void moveRightSliderToValue(double targetValue) {
        targetValue++;
        targetValue -= 15;
        BoundingBox rightSliderBox = rightSlider.boundingBox();
        rightSlider.click();
        rightSlider.hover();
        page.mouse().down();
        page.mouse().move(rightSliderBox.x + 14.4 * targetValue, rightSliderBox.width / 2);
        page.mouse().up();
    }

    public void validateSliderFilter() {
        page.waitForTimeout(5_000);
        Locator locator = page.locator("//div[contains(@class, 'sc-hrqzy3-0 cYNMRM sc-157icee-1 ieVVRJ')][.//div]");
        List<String> allTexts = locator.allTextContents();

        for (String text : allTexts) {
            String[] parts = text.split(" · ");
            if (parts.length > 1) {
                String monthsPart = parts[1].trim();
                int months = Integer.parseInt(monthsPart.split(" ")[0]);

                assertTrue(months >= 3 && months <= 10,
                    "Found month value outside range 3-10: " + months + " in text: " + text);
            }
        }
    }

    public void validateCheckBoxFilter() {
        Locator locator = page.locator("//h6");
        List<String> allCoursesAfterFiltering = locator.allTextContents();
        assertNotEquals(allCoursesBeforeFiltering, allCoursesAfterFiltering);
    }

    public void resetFilter() {
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Очистить фильтры"))
            .click();
    }

    public void validateFilterReset() {
        Locator locator = page.locator("//h6");
        List<String> allCoursesAfterFilterReset = locator.allTextContents();
        assertNotEquals(allCoursesBeforeFiltering, allCoursesAfterFilterReset);

    }
}
