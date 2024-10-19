package pageobject;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import scopes.GuiceScoped;
import waiters.Waiter;

public abstract class AbstractCommon<T> {

    protected WebDriver driver;
    protected Actions actions;
    protected Waiter waiter;

    @Inject
    public AbstractCommon(GuiceScoped guiceScoped) {
        this.driver = guiceScoped.driver;
        this.actions = new Actions(driver);
        this.waiter = new Waiter(driver);

        PageFactory.initElements(driver, this);
    }

    protected WebElement findBy(By locator) {
        return driver.findElement(locator);
    }
}
