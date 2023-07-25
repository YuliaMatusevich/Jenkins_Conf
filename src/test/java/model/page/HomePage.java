package model.page;

import io.qameta.allure.Step;
import model.component.base.BaseComponent;
import model.component.base.IMenu;
import model.page.base.BaseConfigPage;
import model.page.base.BaseStatusPage;
import model.component.menu.HomeSideMenuComponent;
import model.page.base.MainBasePageWithSideMenu;
import model.page.config.FolderConfigPage;
import model.page.config.FreestyleProjectConfigPage;
import model.page.config.PipelineConfigPage;
import model.page.status.*;
import model.page.view.NewViewFromDashboardPage;
import model.page.view.ViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static runner.TestUtils.scrollToElement;

public class HomePage extends MainBasePageWithSideMenu<HomeSideMenuComponent> {

    private static class DropdownMenu<StatusPage extends BaseStatusPage<?, ?>,
            ConfigPage extends BaseConfigPage<?, ?>> extends BaseComponent implements IMenu<StatusPage, ConfigPage> {

        private StatusPage statusPage;

        private ConfigPage configPage;

        public DropdownMenu(WebDriver driver) {
            super(driver);
        }

        public DropdownMenu(WebDriver driver, StatusPage statusPage) {
            this(driver);
            this.statusPage = statusPage;
        }

        public DropdownMenu(WebDriver driver, ConfigPage configPage) {
            this(driver);
            this.configPage = configPage;
        }

        @Override
        public StatusPage getStatusPage() {
            return statusPage;
        }

        @Override
        public ConfigPage getConfigPage() {
            return configPage;
        }
    }

    @FindBy(css = "tr td a.model-link")
    private List<WebElement> jobList;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(css = ".tabBar>.tab>a.addTab")
    private WebElement addViewLink;

    @FindBy(xpath = "//span[text()='Move']")
    private WebElement moveButtonDropdown;

    @FindBy(xpath = "//div[@class='tabBar']/div/a")
    private List<WebElement> viewList;

    @FindBy(xpath = "//span[contains(@class, 'build-status-icon')]/span/child::*")
    private WebElement buildStatusIcon;

    @FindBy(xpath = "(//a[@class='yuimenuitemlabel'])[3]/span")
    private WebElement buildNowButton;

    @FindBy(css = "#projectstatus th")
    private List<WebElement> listJobTableHeaders;

    @FindBy(xpath = "//div[@class='tabBar']//a[text()='All']/..")
    private WebElement viewAllTab;

    @FindBy(css = "#tasks a")
    private List<WebElement> sideMenuList;

    @FindBy(id = "systemmessage")
    private WebElement systemMessage;

    @FindBy(xpath = "//a[@href='computer/new']")
    private WebElement setUpAnAgent;

    @Override
    protected HomeSideMenuComponent createSideMenuComponent() {
        return new HomeSideMenuComponent(getDriver());
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on new view icon")
    public NewViewFromDashboardPage<?> clickAddViewLink() {
        addViewLink.click();

        return new NewViewFromDashboardPage<>(getDriver(), null);
    }

    @Step("Get list of projects' names")
    public List<String> getJobNamesList() {
        return jobList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getNumberOfJobsContainingString(String string) {

        return (int)jobList
                .stream()
                .filter(element -> element.getText().contains(string))
                .count();
    }

    public List<String> getViewList() {
        return viewList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Click 'Freestyle project' on the Home page")
    public FreestyleProjectStatusPage clickFreestyleProjectName() {
        getWait(10).until(ExpectedConditions.visibilityOfAllElements(jobList)).get(0).click();

        return new FreestyleProjectStatusPage(getDriver());
    }

    @Step("Select ‘Freestyle project’ '{name}' on the Dashboard")
    public FreestyleProjectStatusPage clickFreestyleProjectName(String name) {
        getWait(10).until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();

        return new FreestyleProjectStatusPage(getDriver());
    }

    @Step("Click the job drop-down menu with name '{name}' on the dashboard")
    public HomePage clickJobDropdownMenu(String name) {
        getWait(5).until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//tr[@id='job_%s']//button[@class='jenkins-menu-dropdown-chevron']", name)))).click();

        return this;
    }

    @Step("Select the 'Rename' option from the pipeline's dropdown menu")
    public RenameItemPage<PipelineStatusPage> clickRenamePipelineDropdownMenu() {
        return new DropdownMenu<>(getDriver(), new PipelineStatusPage(getDriver())).clickRename();
    }

    @Step("Select the 'Rename' option from the multi-configuration's dropdown menu")
    public RenameItemPage<MultiConfigurationProjectStatusPage> clickRenameMultiConfigurationDropdownMenu() {
        return new DropdownMenu<>(getDriver(), new MultiConfigurationProjectStatusPage(getDriver())).clickRename();
    }

    @Step("Select the 'Rename' option from the folder's dropdown menu")
    public RenameItemPage<FolderStatusPage> clickRenameFolderDropdownMenu() {
        return new DropdownMenu<>(getDriver(), new FolderStatusPage(getDriver())).clickRename();
    }

    @Step("Click 'Rename' in the drop-down menu")
    public RenameItemPage<MultibranchPipelineStatusPage> clickRenameMultibranchPipelineDropDownMenu() {
        return new DropdownMenu<>(getDriver(), new MultibranchPipelineStatusPage(getDriver())).clickRename();
    }

    @Step("Click on 'Configure' dropdown menu")
    public FolderConfigPage clickConfigureFolderDropDownMenu() {
        return new DropdownMenu<>(getDriver(), new FolderConfigPage(getDriver())).clickConfigure();
    }

    @Step("Click on pipeline project name")
    public PipelineStatusPage clickPipelineProjectName() {
        jobList.get(0).click();

        return new PipelineStatusPage(getDriver());
    }

    @Step("Click Delete in the drop-down menu")
    public DeletePage<HomePage> clickDeleteDropDownMenu() {
        return new DropdownMenu<>(getDriver()).clickDelete(new DeletePage<>(getDriver(), this));
    }

    @Step("Click on MultibranchPipeline name '{name}' on the dashboard")
    public MultibranchPipelineStatusPage clickJobMultibranchPipeline(String name) {
        getDriver().findElement(By.xpath("//span[text()='" + name + "']")).click();

        return new MultibranchPipelineStatusPage(getDriver());
    }

    @Step("Click configure in the drop-down menu")
    public PipelineConfigPage clickConfigurePipelineDropDownMenu() {
        return new DropdownMenu<>(getDriver(), new PipelineConfigPage(getDriver())).clickConfigure();
    }

    @Step("Get Header Text")
    public String getHeaderText() {

        return getWait(10).until(ExpectedConditions.visibilityOf(header)).getText();
    }

    @Step("Click on Folder name '{folderName}' on the dashboard")
    public FolderStatusPage clickFolder(String folderName) {
        getDriver().findElement(By.xpath("//span[text()='" + folderName + "']")).click();

        return new FolderStatusPage(getDriver());
    }

    @Step("Get job build status on the Home page")
    public String getJobBuildStatus(String name) {
        return getDriver().findElement(By.id(String.format("job_%s", name)))
                .findElement(By.xpath(".//*[name()='svg']")).getAttribute("tooltip");
    }

    @Step("Click on '{name}' multi-configuration project")
    public MultiConfigurationProjectStatusPage clickMultiConfigurationProject(String name) {
        getWait(5).until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();
        return new MultiConfigurationProjectStatusPage(getDriver());
    }

    @Step("Click on 'Move' in the dropdown menu")
    public <T extends BaseStatusPage<T, ?>> MovePage<T> clickMoveButtonDropdown(T baseStatusPage) {
        getWait(5).until(ExpectedConditions.visibilityOf(moveButtonDropdown));
        scrollToElement(getDriver(), moveButtonDropdown);
        moveButtonDropdown.click();
        return new MovePage<>(getDriver(), baseStatusPage);
    }

    public String getBuildDurationTime() {
        if (getJobBuildStatus().equals("Success")) {

            return getDriver().findElement(By.xpath("//tr[contains(@class, 'job-status')]/td[4]")).getText();
        } else if (getJobBuildStatus().equals("Failed")) {

            return getDriver().findElement(By.xpath("//tr[contains(@class, 'job-status')]/td[5]")).getText();
        }

        return null;
    }

    @Step("Get job build status")
    public String getJobBuildStatus() {
        getWait(3).until(ExpectedConditions.visibilityOf(buildStatusIcon));
        return buildStatusIcon.getAttribute("tooltip");
    }

    public ViewPage clickView(String name) {
        getDriver().findElement(By.xpath(String.format("//a[@href='/view/%s/']", name))).click();

        return new ViewPage(getDriver());
    }

    @Step("Get last success text")
    public String getLastSuccessText(String name) {

        return getDriver().findElement(By.xpath(String.format("//*[@id='job_%s']/td[4]", name))).getText();
    }

    @Step("Get selected project name")
    public String getJobName(String name) {

        return getDriver().findElement(By.xpath(String.format("//span[contains(text(),'%s')]", name))).getText();
    }

    @Step("Move point to checkbox")
    public HomePage movePointToCheckBox() {
        getAction().moveToElement(buildStatusIcon).perform();

        return this;
    }

    @Step("Select 'Configure' in the dropdown menu")
    public FreestyleProjectConfigPage clickConfigureFreestyleDropDownMenu() {
        return new DropdownMenu<>(getDriver(), new FreestyleProjectConfigPage(getDriver())).clickConfigure();
    }

    @Step("Select 'project' in dropdown menu")
    public HomePage clickProjectDropdownMenu(String projectName) {
        getWait(5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[@href='job/" + projectName + "/']/button"))).click();

        return this;
    }

    @Step("Verify that 'build now' button is displayed")
    public boolean buildNowButtonIsDisplayed() {

        return getWait(5).until(ExpectedConditions.visibilityOf(buildNowButton)).isDisplayed();
    }

    @Step("Click Organization Folder name - '{name}' on dashboard")
    public OrgFolderStatusPage clickOrgFolder(String name) {
        getDriver().findElement(By.linkText(name)).click();

        return new OrgFolderStatusPage(getDriver());
    }

    @Step("Click pipeline '{name}' on dashboard")
    public PipelineStatusPage clickPipelineJob(String name) {
        getDriver().findElement(By.xpath("//span[text()='" + name + "']")).click();

        return new PipelineStatusPage(getDriver());
    }

    @Step("Get list of existing jobs")
    public String getJobListAsString() {
        StringBuilder listProjectsNames = new StringBuilder();
        for (WebElement projects : jobList) {
            listProjectsNames.append(projects.getText()).append(" ");
        }

        return listProjectsNames.toString().trim();
    }

    public int getJobTableHeadersSize() {
        return listJobTableHeaders.size();
    }

    @Step("Get attribute view 'All'")
    public String getAttributeViewAll() {
        return viewAllTab.getAttribute("class");
    }

    @Step("Get list of side menu options")
    public List<String> getSideMenuList() {
        return sideMenuList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Click attribute view 'All'")
    public HomePage clickViewAll() {
        viewAllTab.click();

        return this;
    }

    @Step("Get 'System Message' from dashboard")
    public String getSystemMessageFromDashboard() {
        return systemMessage.getText();
    }

    @Step("Click on 'Set up an agent' on dashboard")
    public NewNodePage clickOnSetUpAnAgent() {
        setUpAnAgent.click();
        return new NewNodePage(getDriver());
    }
}