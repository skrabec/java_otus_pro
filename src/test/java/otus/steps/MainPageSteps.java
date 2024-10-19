package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.When;
import pages.MainPage;

public class MainPageSteps {
    @Inject
    private MainPage mainPage;

    @When("Open main page")
    public void openMainPage() {
        mainPage.open();
    }
}
