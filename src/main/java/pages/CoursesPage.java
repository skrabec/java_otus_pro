package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import com.google.inject.Inject;
import data.LessonCard;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import scopes.GuiceScoped;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Path("/catalog/courses")
public class CoursesPage extends AbstractBasePage<CoursesPage> {
    @FindBy(xpath = "//section[2]//a[contains(@href, '/lessons')]")
    private List<WebElement> lessonItems;

    @FindBy(xpath = "//a[contains(@href, '/categories/')]")
    private List<WebElement> categories;

    @Inject
    public CoursesPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
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

    private LocalDate extractDateFromText(String text, DateTimeFormatter formatter) {
        String datePattern = "\\d{1,2} [а-яА-Я]+, \\d{4}";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String datePart = matcher.group();
            return LocalDate.parse(datePart, formatter);
        } else {
            throw new IllegalArgumentException("Date not found in text: " + text);
        }
    }

    public void validateCourseData(List<LessonCard> lessonCards) {
        for (LessonCard course : lessonCards) {
            String courseUrl = course.getHref();
            String[] courseDateFormatted = course.getDate().split(",");

            try {
                Document doc = Jsoup.connect(courseUrl).get();
                String courseTitle = doc.select("h1").text();
                String courseStartDateText = doc.selectXpath("(//p[contains(@class, 'sc-1og4wiw-0 sc-3cb1l3-0 gcChXs dgWykw')])[1]").text();

                assertThat(course.getName()).isEqualTo(courseTitle);
                assertThat(courseDateFormatted[0]).isEqualTo(courseStartDateText);

            } catch (IOException e) {
                System.out.println("Error fetching course data from: " + courseUrl + " - " + e.getMessage());
            }
        }
    }


    public List<LessonCard> getLessonCards() {
        List<LessonCard> lessonCards = new ArrayList<>();

        List<WebElement> courseLinks = driver.findElements(By.xpath("//a[contains(@class, 'sc-zzdkm7-0')]"));
        List<WebElement> courseNames = driver.findElements(By.xpath("//h6"));
        List<WebElement> courseDates = driver.findElements(By.xpath("//div[contains(@class, 'sc-hrqzy3-0 cYNMRM sc-157icee-1 ieVVRJ')][.//div]"));

        for (int i = 0; i < courseLinks.size(); i++) {
            String href = courseLinks.get(i).getAttribute("href");
            String courseName = courseNames.get(i).getText().trim();
            String courseDate = courseDates.get(i).getText().trim();

            if (!courseName.isEmpty()) {
                String formattedCourseDate = formatCourseDate(courseDate);

                LessonCard lessonCard = new LessonCard(href, courseName, formattedCourseDate);
                lessonCards.add(lessonCard);
            }
        }

        return lessonCards;
    }


    public List<LessonCard> findCourses(boolean isEarly) {
        List<LessonCard> lessonCards = getLessonCards();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));

        Optional<LessonCard> targetCourse = lessonCards.stream()
            .filter(card -> !card.getDate().isEmpty())
            .reduce((a, b) -> {
                int comparison = extractDateFromText(a.getDate(), formatter).compareTo(extractDateFromText(b.getDate(), formatter));
                return (isEarly ? comparison <= 0 : comparison >= 0) ? a : b;
            });

        return targetCourse
            .map(course -> lessonCards.stream()
                .filter(card -> extractDateFromText(card.getDate(), formatter).equals(extractDateFromText(course.getDate(), formatter)))
                .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
    }


    private String formatCourseDate(String courseDate) {
        String[] dateParts = courseDate.split(" · ");
        return dateParts[0];
    }


    public void validateCategory(String categoryName) {
        WebElement category = findBy(By.xpath(String.format("//label[text()='%s']", categoryName)));
        waiter.waitForVisible(category);
        String checkboxId = category.getAttribute("for");
        WebElement checkbox = driver.findElement(By.id(checkboxId));
        boolean isChecked = checkbox.isSelected();
        assertThat(isChecked).as(String.format("Checkbox for '%s' should be checked", categoryName)).isTrue();
    }
}
