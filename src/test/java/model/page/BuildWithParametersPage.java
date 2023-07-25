package model.page;

import io.qameta.allure.Step;
import model.page.base.BaseStatusPage;
import model.page.base.MainBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BuildWithParametersPage<StatusPage extends BaseStatusPage<?, ?>> extends MainBasePage {

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(xpath = "//div[@id='main-panel']/p")
    private WebElement descriptionText;

    @FindBy(xpath = "//div[@name='parameter']/input[@name='name']")
    private List<WebElement> listInputParameterNames;

    @FindBy(xpath = "//div[@name='parameter']/input[@name='value']")
    private List<WebElement> listInputParameterValues;

    @FindBy(xpath = "//select[@name='value']")
    private WebElement selectParameter;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement defaultValueCheckbox;

    @FindBy(id = "yui-gen1-button")
    private WebElement buildButton;

    private final StatusPage statusPage;


    public BuildWithParametersPage(WebDriver driver, StatusPage statusPage) {
        super(driver);
        this.statusPage = statusPage;
    }

    public String getNameText() {
        return getWait(5).until(ExpectedConditions.elementToBeClickable(header)).getText();
    }

    public String getDescriptionText() {
        return descriptionText.getText();
    }

    @Step("Get list of parameters' names")
    public String getNthParameterName(int n) {
        return listInputParameterNames.get(n - 1).getAttribute("value");
    }

    public String getNthParameterValue(int n) {
        return listInputParameterValues.get(n - 1).getAttribute("value");
    }

    public String getSelectParametersValues() {
        return selectParameter.getText();
    }

    public boolean isBooleanParameterSetByDefault() {
        return defaultValueCheckbox.isSelected();
    }

    @Step("Select parameter '{text}' from dropdown")
    public BuildWithParametersPage<StatusPage> selectParameterByText(String text) {
        new Select(selectParameter).selectByVisibleText(text);

        return this;
    }

    @Step("Click on 'Build' button")
    public StatusPage clickBuildButton() {
        buildButton.click();

        return statusPage;
    }
}
