package components.static_components;

import components.AbsComponent;
import org.openqa.selenium.WebDriver;

public abstract class AbsStaticComponent<T> extends AbsComponent<T> {
    {
        try {
            waiter.waitForVisible(getComponentEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AbsStaticComponent(WebDriver driver) {
        super(driver);
    }
}
