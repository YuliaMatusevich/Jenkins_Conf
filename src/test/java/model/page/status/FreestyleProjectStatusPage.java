package model.page.status;

import io.qameta.allure.Step;
import model.page.HomePage;
import model.page.base.BaseStatusPage;
import model.component.menu.status.FreestyleProjectStatusSideMenuComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class FreestyleProjectStatusPage extends BaseStatusPage<FreestyleProjectStatusPage, FreestyleProjectStatusSideMenuComponent> {

    @FindBy(xpath = "//textarea[@name = 'description']")
    private WebElement fieldDescriptionText;

    @FindBy(id = "yui-gen2")
    private WebElement buttonSave;

    @FindBy(id = "enable-project")
    private WebElement warningForm;

    @FindBy(name = "Submit")
    private WebElement disableProjectBtn;

    @FindBy(linkText = "Edit description")
    private WebElement buttonEditDescription;

    @FindBy(xpath = "//div[@id = 'main-panel']//h2")
    private List<WebElement> listOfH2Headers;

    @FindBy(xpath = "//a[@class = 'model-link jenkins-icon-adjacent']")
    private List<WebElement> listOfDownstreamProjects;

    @FindBy(xpath = "//a[@href='lastCompletedBuild/testReport/']")
    private WebElement latestTestResultLink;

    @FindBy(xpath = "//div[contains(@id,'history-chart')]")
    private WebElement testResultHistoryChart;

    @Override
    protected FreestyleProjectStatusSideMenuComponent createSideMenuComponent() {
        return new FreestyleProjectStatusSideMenuComponent(getDriver(), this);
    }

    public FreestyleProjectStatusPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Disable Project' button")
    public FreestyleProjectStatusPage clickDisableProjectBtn() {
        disableProjectBtn.click();

        return this;
    }

    @Step("Clear description field and input new description text, Click 'OK' button then")
    public FreestyleProjectStatusPage inputAndSaveDescriptionText(String description) {
        getWait(10).until(ExpectedConditions.elementToBeClickable(fieldDescriptionText)).clear();
        fieldDescriptionText.sendKeys(description);
        getWait(10).until(ExpectedConditions.elementToBeClickable(buttonSave)).click();

        return this;
    }

    @Step("Click 'OK' in the alert message to confirm deletion")
    public HomePage confirmAlertAndDeleteProject() {
        getDriver().switchTo().alert().accept();

        return new HomePage(getDriver());
    }

    @Step("Get warning message text")
    public String getWarningMsg() {
        return warningForm.getText().substring(0, warningForm.getText().indexOf("\n"));
    }

    @Step("Click 'Edit description' link")
    public FreestyleProjectStatusPage clickButtonEditDescription() {
        buttonEditDescription.click();

        return this;
    }

    @Step("Confirm alert and delete Project from the folder")
    public FolderStatusPage confirmAlertAndDeleteProjectFromFolder() {
        getDriver().switchTo().alert().accept();

        return new FolderStatusPage(getDriver());
    }

    @Step("Collect list of h2 headers")
    public List<String> getH2HeaderNamesList() {
        return listOfH2Headers
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Collect list of downstream projects' names")
    public List<String> getDownstreamProjectNamesList() {
        return listOfDownstreamProjects
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Click the upstream project name '{name}")
    public FreestyleProjectStatusPage clickUpstreamProjectName(String name) {
        getDriver().findElement(By.xpath("//a[@href='/job/" + name + "/']")).click();

        return new FreestyleProjectStatusPage(getDriver());
    }

    @Step ("get 'Latest Test Result' link text")
    public String getTestResultLinkText() {
        return latestTestResultLink.getText();
    }

    @Step ("Check if 'Latest Test Result' link is displayed")
    public boolean isTestResultLinkDisplayed() {
        getWait(10).until(ExpectedConditions.elementToBeClickable(latestTestResultLink));

        return latestTestResultLink.isDisplayed();
    }

    @Step("Check if Test result history chart is Displayed")
    public boolean isTestResultHistoryChartDisplayed() {
        getWait(10).until(ExpectedConditions.visibilityOf(testResultHistoryChart));

        return testResultHistoryChart.isDisplayed();
    }

    @Step("Refresh FreestyleProjectStatus page")
    public FreestyleProjectStatusPage refreshFreestyleProjectStatusPage() {
        getDriver().navigate().refresh();

        return this;
    }
}