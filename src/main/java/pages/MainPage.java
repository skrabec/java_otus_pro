package pages;

import annotations.Path;
import com.google.inject.Inject;
import scopes.GuiceScoped;

@Path("/")
public class MainPage extends AbstractBasePage<MainPage> {

    @Inject
    public MainPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public void findCourseByTitle(String test) {

    }
}
