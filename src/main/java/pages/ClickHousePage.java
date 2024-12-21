package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;

@Path("/lessons/clickhouse/")
public class ClickHousePage extends AbsBasePage {
    Locator firstSlide = page.locator("//section//div[contains(@class, 'swiper-slide')]").first();
    Locator slide = page.locator("//section//div[contains(@class, 'swiper-slide')]");
    public ClickHousePage(Page page) {
        super(page);
    }

    public void scrollInstructorsListByDragAndDrop() {
        BoundingBox firstSlideBox = firstSlide.boundingBox();
        page.mouse().wheel(firstSlideBox.x, firstSlideBox.y);
        page.mouse().move(firstSlideBox.x, firstSlideBox.width / 2);
        page.mouse().down();
        page.mouse().move(firstSlideBox.x - 300, firstSlideBox.width / 2, new Mouse.MoveOptions().setSteps(10));
        page.mouse().up();
    }

//    public void checkIfInstructorSlidesWereRotated() {
//        assertThat(firstSlide).isHidden();
//    }

    public void clickOnTheSlideByName(String name) {
        BoundingBox firstSlideBox = firstSlide.boundingBox();
        page.mouse().wheel(firstSlideBox.x, firstSlideBox.y);
        slide.getByText(name).click();
    }

    public void checkTeacherNamePresent(String teacherName) {
        assertThat(page.locator("//h3").getByText(teacherName)).isVisible();
    }

    public void clickOnArrow() {

    }
}
