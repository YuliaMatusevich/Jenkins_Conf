package model.page;

import io.qameta.allure.Step;
import model.page.base.MainBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RestApiPage extends MainBasePage {

    @FindBy(xpath = "//dt/a[@href='xml']")
    private WebElement xmlApiLink;

    @FindBy(xpath = "//*[@id='main-panel']/h1")
    private WebElement h1RestApi;

    public RestApiPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on 'XML API' link")
    public XmlPage clickXmlApi() {
        xmlApiLink.click();

        return new XmlPage(getDriver());
    }

    public String getTextH1RestApi() {
        return h1RestApi.getText();
    }
}