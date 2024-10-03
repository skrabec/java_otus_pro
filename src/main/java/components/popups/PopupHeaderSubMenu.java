package components.popups;

import annotations.components.Component;
import components.AbsComponent;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Component("internal_component:.//div[./a[contains(@href, '/categories')]]")
public class PopupHeaderSubMenu extends AbsComponent<PopupHeaderSubMenu> implements IPopUp<PopupHeaderSubMenu> {
    public PopupHeaderSubMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    public PopupHeaderSubMenu popUpShouldNotBeVisible() throws Exception {
        assertThat(waiter.waitForInvisible(getComponentEntity().findElement(getComponentBy())))
                .as("PopUp is visible")
                .isTrue();

        return this;
    }

    @Override
    public PopupHeaderSubMenu popUpShouldBeVisible() throws Exception {
        assertThat(waiter.waitForVisible(getComponentEntity().findElement(getComponentBy())))
                .as("PopUp is invisible")
                .isTrue();

        return this;
    }
}
