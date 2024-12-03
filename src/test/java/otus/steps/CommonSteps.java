package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import scopes.GuiceScoped;

public class CommonSteps {

    @Inject
    private GuiceScoped guiceScoped;

    @Given("Open browser {string}")
    public void openBrowser(String browserName) {
        guiceScoped.newBrowser(browserName);
    }
}
