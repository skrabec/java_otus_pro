package otus.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import scopes.GuiceScoped;

public class Hooks {
    @Inject
    private GuiceScoped guiceScoped;

    @After
    public void afterAll() {
        if (guiceScoped.driver != null) {
            guiceScoped.driver.close();
            guiceScoped.driver.quit();
        }
    }
}
