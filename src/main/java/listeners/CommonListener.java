package listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class CommonListener implements WebDriverListener {
    private void highlightElement(WebDriver driver, WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='10px solid red'", element);
        }
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        highlightElement(driver, result);
    }


}
