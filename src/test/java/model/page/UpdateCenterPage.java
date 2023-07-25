package model.page;

import io.qameta.allure.Step;
import model.page.base.MainBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

import static runner.TestUtils.scrollToElement;

public class UpdateCenterPage extends MainBasePage {

    public UpdateCenterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text() = 'Go back to the top page']")
    private WebElement buttonGoBackToTopPage;

    @Step("Click on 'Go back to the top page' link")
    public HomePage clickButtonGoBackToTopPage() {
        scrollToElement(getDriver(), buttonGoBackToTopPage);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(buttonGoBackToTopPage));
        getWait(5).until(ExpectedConditions.elementToBeClickable(buttonGoBackToTopPage)).click();

        return new HomePage(getDriver());
    }
}
