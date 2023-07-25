package runner;

import io.qameta.allure.Step;
import model.page.HomePage;
import org.openqa.selenium.WebDriver;

public class ProjectMethodsUtils {

    @Step("Create new pipeline project with valid random name '{name}' and return to dashboard")
    public static void createNewPipelineProject(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickNewItem()
                .setItemName(name)
                .selectPipelineType()
                .clickOkButton()
                .clickSaveButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    @Step("Create Freestyle project with valid random name and return to dashboard")
    public static void createNewFreestyleProject(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickNewItem()
                .setItemName(name)
                .selectFreestyleProjectType()
                .clickOkButton()
                .clickSaveButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }
    @Step("Create Multi-configuration project with valid random name and return to dashboard")
    public static void createNewMultiConfigurationProject(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickNewItem()
                .setItemName(name)
                .selectMultiConfigurationProjectType()
                .clickOkButton()
                .clickSaveButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    @Step("Create new Folder with valid random name and return to dashboard")
    public static void createNewFolder(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickNewItem()
                .setItemName(name)
                .selectFolderType()
                .clickOkButton()
                .clickSaveButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    @Step("Create new MultibranchPipeline project with valid random name and return to dashboard")
    public static void createNewMultibranchPipeline(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickNewItem()
                .setItemName(name)
                .selectMultibranchPipelineType()
                .clickOkButton()
                .clickSaveButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    @Step("Create new Organization folder with valid random name and return to dashboard")
    public static void createNewOrganizationFolder(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickNewItem()
                .setItemName(name)
                .selectOrgFolderType()
                .clickOkButton()
                .clickSaveButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    public static void createNewGlobalViewForMyViews(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickMyViewsSideMenuLink()
                .clickNewView()
                .setViewName(name)
                .selectGlobalViewType()
                .clickCreateButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    public static void createNewListViewForMyViews(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickMyViewsSideMenuLink()
                .clickNewView()
                .setViewName(name)
                .selectListViewType()
                .clickCreateButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    public static void createNewMyViewForMyViews(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickMyViewsSideMenuLink()
                .clickNewView()
                .setViewName(name)
                .selectMyViewType()
                .clickCreateButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    @Step("Create new ListView and return to dashboard")
    public static void createNewListViewForDashboard(WebDriver driver, String name) {
        new HomePage(driver)
                .clickAddViewLink()
                .setViewName(name)
                .selectListViewType()
                .clickCreateButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    public static void createNewMyViewViewForDashboard(WebDriver driver, String name) {
        new HomePage(driver)
                .clickAddViewLink()
                .setViewName(name)
                .selectMyViewType()
                .clickCreateButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    @Step("Create new User and return to dashboard")
    public static void createNewUser(WebDriver driver, String username, String password, String fullName, String email) {
        new HomePage(driver)
                .getSideMenu()
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUsername(username)
                .setPassword(password)
                .confirmPassword(password)
                .setFullName(fullName)
                .setEmail(email)
                .clickCreateUserButton()
                .getHeader()
                .clickJenkinsNameIcon();
    }

    @Step("Edit the description text in the description field ")
    public static void editDescriptionUserActiveField(WebDriver driver, String text) {
        new HomePage(driver)
                .getHeader()
                .clickUserIcon()
                .clickAddDescriptionLink()
                .clearDescriptionInputField()
                .setDescriptionField(text);
    }

    public static void changeDefaultView(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickManageJenkins()
                .clickConfigureSystem()
                .selectDefaultView(name)
                .clickSaveButton();
    }

    public static void createNewNode(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickBuildExecutorStatus()
                .getSideMenu()
                .clickNewNode()
                .setName(TestDataUtils.ITEM_NAME)
                .selectPermanentAgent()
                .clickCreateButton()
                .clickSaveButton();
    }

    @Step("Set Maven in Global Tools Configuration with Maven name '{name}'")
    public static void setMavenVersion(WebDriver driver, String name) {
        new HomePage(driver)
                .getSideMenu()
                .clickManageJenkins()
                .clickConfigureTools()
                .setMavenVersionWithNewName(TestDataUtils.MAVEN_NAME)
                .clickApplyButton()
                .getBreadcrumbs()
                .clickDashboard();
    }
    @Step("Delete all Maven installations that were set on the Configuration page")
    public static void deleteAllMavenInstalled(WebDriver driver) {
        new HomePage(driver)
                .getSideMenu()
                .clickManageJenkins()
                .clickConfigureTools()
                .deleteAllMavenInstallations();
    }
}