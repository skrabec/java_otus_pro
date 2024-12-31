package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;

@Path("/subscription")
public class SubscriptionPage extends AbsBasePage {
    public SubscriptionPage(Page page) {
        super(page);
    }

    public void checkSubscriptionOptions(List<String> expectedSubscriptionOptions) {
        Locator subscription = page.locator("//section[@id='packages']//h4");
        List<String> subscriptionOptions = subscription.allTextContents();
        assertEquals(subscriptionOptions, expectedSubscriptionOptions);
    }

    public void clickOnButtonDetailsForChosenPlan(String planName) {
        page.locator(String.format("//h4[text()='%s']/../..//button[text()='Подробнее']", planName)).click();
    }

    public void verifyAdditionalOptions() {
        assertThat(page.locator("section#packages").getByText("Можно менять курсы раз в месяц").first()).isInViewport();
    }


    public void clickOnButtonHideDetailsForChosenPlan(String planName) {
        page.locator(String.format("//h4[text()='%s']/../..//button[text()='Свернуть']", planName)).click();
    }

    public void verifyAdditionalOptionsIsHidden() {
        assertThat(page.locator("section#packages").getByText("Можно менять курсы раз в месяц").first()).not().isInViewport();
    }

    public void clickOnButtonBuyForChosenPlan(String planName) {
        page.locator(String.format("//h4[text()='%s']/../..//button[text()='Купить']", planName)).click();
    }

    public void clickOnEnterToAccount() {
        page.getByText("Вход").click();
    }


}
