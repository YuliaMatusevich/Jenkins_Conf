package model.page;

import io.qameta.allure.Step;
import model.page.base.BaseConfigPage;
import model.page.base.MainBasePage;
import model.page.config.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class NewItemPage<ConfigPage extends BaseConfigPage<?, ?>> extends MainBasePage {

    @FindBy(id = "name")
    private WebElement itemName;

    @FindBy(id = "itemname-required")
    private WebElement itemNameRequiredMessage;

    @FindBy(id = "itemname-invalid")
    private WebElement itemNameInvalidMsg;

    @FindBy(xpath = "//div[@class='icon']")
    private List<WebElement> itemsList;

    @FindBy(xpath = "//label/span[@class='label']")
    private List<WebElement> namesItemsList;

    @FindBy(id = "ok-button")
    private WebElement okButton;

    @FindBy(className = "hudson_model_FreeStyleProject")
    private WebElement freestyleProject;

    @FindBy(className = "com_cloudbees_hudson_plugins_folder_Folder")
    private WebElement folderType;

    @FindBy(xpath = "//li[@class = 'jenkins_branch_OrganizationFolder']")
    private WebElement orgFolder;

    @FindBy(xpath = "//span[contains(text(), 'Multi-configuration project')]")
    private WebElement multiConfigurationProject;

    @FindBy(xpath = "//li[@class='org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject']")
    private WebElement multibranchPipeline;

    @FindBy(xpath = "//span[text() = 'Pipeline']")
    private WebElement pipeline;

    @FindBy(id = "from")
    private WebElement copyFrom;

    @FindBy(className = "h3")
    private WebElement h3Header;

    private final ConfigPage configPage;

    public NewItemPage(WebDriver driver, ConfigPage configPage) {
        super(driver);
        this.configPage = configPage;
    }

    public NewItemPage<ConfigPage> clearItemName() {
        itemName.clear();

        return this;
    }

    @Step("Input the New Item name: {name}")
    public NewItemPage<ConfigPage> setItemName(String name) {
        getWait(2).until(ExpectedConditions.visibilityOf(itemName)).sendKeys(name);

        return this;
    }

    @Step("Select 'Freestyle project' type on NewItemPage")
    public NewItemPage<FreestyleProjectConfigPage> selectFreestyleProjectType() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(freestyleProject)).click();

        return new NewItemPage<>(getDriver(), new FreestyleProjectConfigPage(getDriver()));
    }

    @Step("Select 'Pipeline' project type")
    public NewItemPage<PipelineConfigPage> selectPipelineType() {
        pipeline.click();

        return new NewItemPage<>(getDriver(), new PipelineConfigPage(getDriver()));
    }

    @Step("Select 'Multi-configuration project' type")
    public NewItemPage<MultiConfigurationProjectConfigPage> selectMultiConfigurationProjectType() {
        multiConfigurationProject.click();

        return new NewItemPage<>(getDriver(), new MultiConfigurationProjectConfigPage(getDriver()));
    }

    @Step("Select 'Folder' type")
    public NewItemPage<FolderConfigPage> selectFolderType() {
        folderType.click();

        return new NewItemPage<>(getDriver(), new FolderConfigPage(getDriver()));
    }

    @Step("Select 'Multibranch Pipeline' item")
    public NewItemPage<MultibranchPipelineConfigPage> selectMultibranchPipelineType() {
        getWait(1).until(ExpectedConditions.visibilityOf(multibranchPipeline));
        multibranchPipeline.click();

        return new NewItemPage<>(getDriver(), new MultibranchPipelineConfigPage(getDriver()));
    }

    @Step("Select 'Organization folder' item")
    public NewItemPage<OrgFolderConfigPage> selectOrgFolderType() {
        TestUtils.scrollToElement(getDriver(), orgFolder);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(orgFolder));
        getWait(5).until(ExpectedConditions.elementToBeClickable(orgFolder)).click();

        return new NewItemPage<>(getDriver(), new OrgFolderConfigPage(getDriver()));
    }

    @Step("Click 'OK' button")
    public ConfigPage clickOkButton() {
        okButton.click();

        return configPage;
    }

    @Step("Click 'Ok' button to create an item")
    public CreateItemErrorPage clickOkToCreateItemErrorPage() {
        okButton.click();

        return new CreateItemErrorPage(getDriver());
    }

    public int getItemsListSize() {
        getWait(5).until(ExpectedConditions.visibilityOfAllElements(itemsList));
        return itemsList.size();
    }

    @Step("get message name cannot be empty")
    public String getItemNameRequiredMessage() {
        return itemNameRequiredMessage.getText();
    }

    @Step("Get an Error message text")
    public String getItemNameInvalidMessage() {

        return getWait(2).until(ExpectedConditions.visibilityOf(itemNameInvalidMsg)).getText();
    }

    @Step("Check if 'OK' button on the NewItemPage is enabled")
    public boolean isOkButtonEnabled() {
        return okButton.isEnabled();
    }

    @Step("Input '{name}' into 'Copy from' field")
    public NewItemPage<?> setCopyFrom(String name) {
        getAction().moveToElement(copyFrom).click().sendKeys(name).perform();

        return this;
    }

    @Step("Enter name of existing project to make a copy from it")
    public NewItemPage<ConfigPage> setCopyFromItemName(String name) {
        TestUtils.scrollToEnd(getDriver());
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(copyFrom)).sendKeys(name);

        return this;
    }

    @Step("Check if 'Copy from' field is displayed")
    public boolean isDisplayedFieldCopyFrom() {
        try {
            TestUtils.scrollToEnd(getDriver());
            getWait(5).until(ExpectedConditions.visibilityOf(itemName));
            return copyFrom.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Get NewItemPage header")
    public String getH3HeaderText() {

        return getWait(10).until(ExpectedConditions.visibilityOf(h3Header)).getText();
    }

    public List<String> newItemsNameList() {
        getWait(5).until(ExpectedConditions.visibilityOf(h3Header));

        return namesItemsList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
