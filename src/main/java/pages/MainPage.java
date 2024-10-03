package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

@Path("/")
public class MainPage extends AbstractBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href, '/categories/')]")
    private List<WebElement> categories;


    public String clickRandomCategory() {
        int randomNumber = (int) (Math.random() * 12) + 1;

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement randomCategory = categories.get(randomNumber);

        String categoryText = (String) jsExecutor.executeScript("return arguments[0].textContent;", randomCategory);

        String cleanCategoryText = categoryText.split("\\(")[0].trim();

        WebElement elementToClick = driver.findElement(By.xpath(
            String.format("//a[contains(@class, 'sc-1pgqitk-0 dNitgt') and contains(text(), '%s')]", cleanCategoryText)
        ));

        actions.moveToElement(findBy(By.xpath("//span[@title='Обучение']"))).perform();

        elementToClick.click();

        return cleanCategoryText;
    }

}
