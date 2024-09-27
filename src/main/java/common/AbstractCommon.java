package common;

import com.google.inject.Guice;
import modules.GuicePageModules;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractCommon<T> {

    protected WebDriver driver;
    protected Actions actions;

    public AbstractCommon(WebDriver driver){
        this.driver=driver;
        this.actions = new Actions(driver);

        Guice.createInjector(new GuicePageModules()).injectMembers(this);

        PageFactory.initElements(driver, this);
    }

    protected WebElement $findBy(By locator) {
        return driver.findElement(locator);
    }
}
