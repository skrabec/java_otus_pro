package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import pages.CoursesPage;

public class CoursesPageSteps {

    @Inject
    private CoursesPage coursesPage;

    @Then("Open courses page")
    public void openCoursesPage() {
        coursesPage.open();
    }

    @Then("Search course {string} page")
    public void findCourseByTile(String courseTitle) {
        coursesPage.clickLessonTitleByTitle(courseTitle);
    }

    @Then("Check {string} page is opened")
    public void validateCourseName(String courseTitle) {
        coursesPage.coursePageIsOpened(courseTitle);
    }

    @Then("Search course by {string}")
    public void searchCourseByDate(String date) {
        coursesPage.findCoursesByDateOrLater(date);

    }
}
