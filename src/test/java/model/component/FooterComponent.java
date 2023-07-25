package model.component;

import io.qameta.allure.Step;
import model.component.base.BaseComponent;
import model.page.ExternalJenkinsPage;
import model.page.RestApiPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class FooterComponent extends BaseComponent {

    @FindBy(xpath = "//a[@href='https://www.jenkins.io/']")
    private WebElement jenkinsFooterLink;

    @FindBy(id = "footer")
    private WebElement footer;

    @FindBy(xpath = "//div/a[@href = 'api/']")
    private WebElement restApi;

    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Click on 'Jenkins Version' link")
    public ExternalJenkinsPage clickJenkinsVersion() {
        jenkinsFooterLink.click();
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));

        return new ExternalJenkinsPage(getDriver());
    }

    public boolean isDisplayedFooter() {

        return footer.isDisplayed();
    }

    public WebElement getJenkinsFooterLink() {
        return jenkinsFooterLink;
    }

    @Step("Click on 'REST API' link")
    public RestApiPage clickRestApiLink() {
        restApi.click();

        return new RestApiPage(getDriver());
    }
}