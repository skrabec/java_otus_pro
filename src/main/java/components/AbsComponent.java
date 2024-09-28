package components;

import annotations.components.Component;
import common.AbstractCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbsComponent<T> extends AbstractCommon<T> {
    public AbsComponent(WebDriver driver) {
        super(driver);
    }

    protected By getComponentBy() throws Exception {
        Component component = getClass().getAnnotation(Component.class);
        if (component != null) {
            Pattern pattern = Pattern.compile("(.*?):(.*?)");
            Matcher matcher = pattern.matcher(component.value());
            if (matcher.find()) {
                switch (matcher.group(1)) {
                    case "id":
                        return By.id(matcher.group(2));
                    case "name":
                        return By.name(matcher.group(2));
                    case "xpath":
                    case "internal_component":
                        return By.xpath(matcher.group(2));
                    case "css":
                        return By.cssSelector(matcher.group(2));
                    case "class":
                        return By.className(matcher.group(2));
                    case "linkText":
                        return By.linkText(matcher.group(2));
                    case "partialLinkText":
                        return By.partialLinkText(matcher.group(2));
                    default:
                        throw new IllegalArgumentException("Invalid component locator type");
                }
            }
        }
        throw new Exception("");
    }

    protected WebElement getComponentEntity() throws Exception {
        return findBy(getComponentBy());
    }
}
