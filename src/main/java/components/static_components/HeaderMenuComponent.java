package components.static_components;

import annotations.components.Component;
import components.AbsComponent;
import data.menu.HeaderMenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Component("css:a[href='/'] ~ nav")
public class HeaderMenuComponent extends AbsComponent<HeaderMenuComponent> {
    public HeaderMenuComponent(WebDriver driver) {
        super(driver);
    }

    public void setFocusMenuItem(HeaderMenuData headerMenuData) {
        String locator = String.format("//div[./span[text()='%s']]", headerMenuData.getName());
        actions.moveToElement(findBy(By.xpath(locator))).build().perform();

    }
}
