package pages;

import annotations.PathTemplate;
import com.google.inject.Inject;
import scopes.GuiceScoped;

@PathTemplate("/lessons/$1")
public class LessonCardPage extends AbstractBasePage<LessonCardPage> {

    @Inject
    public LessonCardPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }


}
