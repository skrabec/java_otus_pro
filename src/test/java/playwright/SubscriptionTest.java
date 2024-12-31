package playwright;

import com.google.inject.Inject;
import extensions.junit.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SubscriptionPage;
import java.util.Arrays;
import java.util.List;

@ExtendWith(UIExtensions.class)
public class SubscriptionTest {
    List<String> expectedSubscriptionOptions = Arrays.asList("Trial", "Standard", "Professional");
    @Inject
    private SubscriptionPage subscriptionPage;

    @Test
    public void subscriptionPageTests() {
        subscriptionPage.open();
        subscriptionPage.checkSubscriptionOptions(expectedSubscriptionOptions);

        subscriptionPage.clickOnButtonDetailsForChosenPlan("Standard");
        subscriptionPage.verifyAdditionalOptions();
        subscriptionPage.clickOnButtonHideDetailsForChosenPlan("Standard");
        subscriptionPage.verifyAdditionalOptionsIsHidden();

        subscriptionPage.clickOnButtonBuyForChosenPlan("Standard");
        subscriptionPage.clickOnEnterToAccount();
        subscriptionPage.login();
    }
}
