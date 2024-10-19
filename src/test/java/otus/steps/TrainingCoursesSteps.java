package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TrainingCoursesPage;

public class TrainingCoursesSteps {
    @Inject
    private TrainingCoursesPage trainingCoursesPage;

    @When("Open training courses page")
    public void openTrainingCoursesPage() {
        trainingCoursesPage.open();
    }

    @Then("Print cheapest and the most expensive course")
    public void printCourses() {
        trainingCoursesPage.printCheapiestAndTheMostExpensiveCourse();
    }
}
