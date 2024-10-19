package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MainPage;

public class MainPageSteps {
    @Inject
    private MainPage mainPage;

    @When("Open main page")
    public void openMainPage() {
        mainPage.open();
    }

    @Then("Search course {string} page")
    public void findCourseByTitile(String courseTitle) {
        mainPage.findCourseByTitle(courseTitle);
    }
}
