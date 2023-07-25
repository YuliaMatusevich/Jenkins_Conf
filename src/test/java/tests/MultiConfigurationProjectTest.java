package tests;


import io.qameta.allure.*;
import model.page.ConsoleOutputPage;
import model.page.HomePage;
import model.page.RenameItemErrorPage;
import model.page.config.MultiConfigurationProjectConfigPage;
import model.page.status.MultiConfigurationProjectStatusPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectMethodsUtils;
import runner.TestDataUtils;
import runner.TestUtils;

import java.util.Arrays;
import java.util.List;

public class MultiConfigurationProjectTest extends BaseTest {

    @TmsLink("Rj2aehGq")
    @Owner("Vita Zharskaya")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Function")
    @Description("Verify that the multi-configuration project can be description can be added")
    @Test
    public void testMultiConfigurationProjectAddDescription() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectStatusPage multiConfPage = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .clickAddOrEditDescription()
                .fillDescription("Description")
                .clickSave();

        Assert.assertEquals(multiConfPage.getDescriptionText(), "Description");
    }

    @TmsLink("qKR0YdeZ")
    @Owner("Artem Chumachenko")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that the multi-configuration project can be renamed in the drop-down menu")
    @Test
    public void testMultiConfigurationProjectRenameProjectViaDropDownMenu() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectStatusPage multiConfigPrStatusPage = new HomePage(getDriver())
                .clickJobDropdownMenu(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .clickRenameMultiConfigurationDropdownMenu()
                .clearFieldAndInputNewName(TestDataUtils.MULTI_CONFIGURATION_PROJECT_RENAME)
                .clickRenameButton();

        Assert.assertEquals(multiConfigPrStatusPage.getNameMultiConfigProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_RENAME), TestDataUtils.MULTI_CONFIGURATION_PROJECT_RENAME);
    }

    @TmsLink("l45PAlUD")
    @Owner("Artem Chumachenko")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that the 'MultiConfigurationProject' can be renamed in the side menu")
    @Test
    public void testMultiConfigurationProjectRenameProjectViaSideMenu() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectStatusPage multiConfigPrStatusPage = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .clickRename()
                .clearFieldAndInputNewName(TestDataUtils.MULTI_CONFIGURATION_PROJECT_RENAME)
                .clickRenameButton();

        Assert.assertEquals(multiConfigPrStatusPage.getNameMultiConfigProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_RENAME), TestDataUtils.MULTI_CONFIGURATION_PROJECT_RENAME);
    }

    @TmsLink("0jufQC14")
    @Owner("Zinaida Konovalova")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that the 'MultiConfigurationProject' can be renamed to project status page")
    @Test
    public void testMultiConfigurationProjectDelete() {
        HomePage homePage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setItemName(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .selectMultiConfigurationProjectType()
                .clickOkButton()
                .clickSaveButton()
                .getBreadcrumbs()
                .clickDashboard()
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .clickDeleteToMyStatusPage()
                .confirmAlertAndDeleteProject();

        Assert.assertFalse(homePage.getJobNamesList().contains(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME));
    }

    @TmsLink("tPQnmQk2")
    @Owner("Vita Zharskaya")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Function")
    @Description("Verify that the 'MultiConfigurationProject' can be disabled to project configuration page")
    @Test
    public void testMultiConfigurationProjectDisable() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectStatusPage multiConfigurationProjectStatusPage = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .clickConfigure()
                .clickEnableOrDisableButton()
                .clickSaveButton();

        Assert.assertTrue(multiConfigurationProjectStatusPage.getTextDisabledWarning().contains("This project is currently disabled"));
    }

    @TmsLink("Po4bZcIE")
    @Owner("Vita Zharskaya")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Function")
    @Description("Verify that the multi-configuration project can be enabled to project configuration page")
    @Test(dependsOnMethods = "testMultiConfigurationProjectDisable")
    public void testMultiConfigurationProjectEnable() {
        MultiConfigurationProjectStatusPage multiConfigurationProjectStatusPage = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .clickConfigure()
                .clickEnableOrDisableButton()
                .clickSaveButton();

        Assert.assertTrue(multiConfigurationProjectStatusPage.disableButtonIsDisplayed());
    }

    @TmsLink("ki8fvOza")
    @Owner("Arailym Ashimova")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Function")
    @Description("Verify that the 'MultiConfigurationProject' I can make to build multi-configuration projects, so that I can build them manually")
    @Test
    public void testMultiConfigurationProjectBuild() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        int countBuildsBeforeNewBuild = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .countBuildsInBuildHistory();

        new MultiConfigurationProjectStatusPage(getDriver())
                .getSideMenu()
                .clickBuildNow();
        getDriver().navigate().refresh();

        int countBuildsAfterNewBuild = new MultiConfigurationProjectStatusPage(getDriver())
                .getSideMenu()
                .countBuildsInBuildHistory();

        Assert.assertNotEquals(countBuildsAfterNewBuild, countBuildsBeforeNewBuild);
    }

    @TmsLink("im1BdS7m")
    @Owner("Natali Chervona")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Function")
    @Description("Verify that the so that can find a previously created multi-configuration project")
    @Test
    public void testFindMultiConfigurationProject() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectStatusPage multiConfigurationProjectStatusPage = new HomePage(getDriver())
                .getHeader().setSearchAndClickEnter(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        Assert.assertEquals(multiConfigurationProjectStatusPage.getNameMultiConfigProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);
    }

    @TmsLink("2r92DxXC")
    @Owner("Stanislaw Aklionak")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Check if multi-configuration project can be disabled")
    @Test
    public void testMultiConfigurationProjectDisableCheckIconDashboardPage() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        String jobStatusIconTooltip = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .clickDisableButton()
                .getBreadcrumbs()
                .clickDashboard()
                .getJobBuildStatus(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        Assert.assertEquals(jobStatusIconTooltip, "Disabled");
    }

    @TmsLink("H8UJ4TPf")
    @Owner("Denis Sebrovsky")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Check if disabled multi-configuration project can be enabled")
    @Test
    public void testMultiConfigurationProjectEnableCheckIconDashboardPage() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .clickDisableButton()
                .getBreadcrumbs()
                .clickDashboard();

        String jobStatusIconTooltip = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .clickEnableButton()
                .getBreadcrumbs()
                .clickDashboard()
                .getJobBuildStatus(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        Assert.assertEquals(jobStatusIconTooltip, "Not built");
    }

    @TmsLink("xUfTdt7U")
    @Owner("Elizaveta Skobeleva")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that the multi-configuration project can be renamed to side menu")
    @Test
    public void testMultiConfigurationProjectRenameToInvalidNameViaSideMenu() {
        RenameItemErrorPage renameItemErrorPage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setItemName(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .selectMultiConfigurationProjectType()
                .clickOkButton()
                .clickSaveButton()
                .getBreadcrumbs()
                .clickDashboard()
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .clickRename()
                .clearFieldAndInputNewName("&")
                .clickRenameButtonWithInvalidData();

        Assert.assertEquals(renameItemErrorPage.getErrorMessage(), "‘&amp;’ is an unsafe character");
    }

    @TmsLink("rFqOXdzk")
    @Owner("Arailym Ashimova")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description()
    @Test(dependsOnMethods = {"testMultiConfigurationProjectBuild"})
    public void testMultiConfigurationProjectsRunJobInBuildHistory() {
        List<String> listNameOfLabels = new HomePage(getDriver())
                .getBreadcrumbs()
                .clickDashboard()
                .getSideMenu()
                .clickMyViewsSideMenuLink()
                .clickBuildHistory()
                .getNameOfLabelsOnTimeLineBuildHistory();

        Assert.assertTrue(listNameOfLabels.contains(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME + " #1"));
    }

    @TmsLink("Bs5nMG6n")
    @Owner("Stanislaw Aklionak")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature("User Interface")
    @Description("Verify that when you turn off the project, the project icon changes its status to 'disable' on the project configuration page")
    @Test
    public void testMultiConfigurationProjectDisableCheckIconProjectName() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectStatusPage multiConfigPrStatusPage = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .clickDisableButton();
        Assert.assertTrue(multiConfigPrStatusPage.iconProjectDisabledIsDisplayed());
    }

    @TmsLink("WdKwAoJS")
    @Owner("Stanislaw Aklionak")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature("User Interface")
    @Description("Verify that when you turn on the project, the project icon changes its status to 'enable' on the project configuration page")
    @Test(dependsOnMethods = {"testMultiConfigurationProjectDisableCheckIconProjectName"})
    public void testMultiConfigurationProjectEnableCheckIconProjectName() {
        MultiConfigurationProjectStatusPage multiConfigPrStatusPage = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .clickEnableButton();

        Assert.assertTrue(multiConfigPrStatusPage.iconProjectEnabledIsDisplayed());
    }

    @TmsLink("Mob6yuVf")
    @Owner("Umida Kakharova")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that multi-configuration project can be enabled on dashboard")
    @Test(dependsOnMethods = "testMultiConfigurationProjectDisableCheckIconDashboardPage")
    public void testEnableMultiConfigurationProject() {
        HomePage buildNowButton = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .clickEnableButton()
                .getBreadcrumbs()
                .clickDashboard()
                .clickProjectDropdownMenu(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        Assert.assertTrue(buildNowButton.buildNowButtonIsDisplayed());
    }

    @TmsLink("sTZpY5Hc")
    @Owner("Dmitry Starski")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that the assembly step verifies the success of the assembly")
    @Flaky
    @Test
    public void testMultiConfigurationProjectWithBuildStepCheckBuildSuccess() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectConfigPage multiConfigProjectPage = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .clickConfigure()
                .scrollAndClickBuildSteps();
        if (TestUtils.isCurrentOSWindows()) {
            multiConfigProjectPage
                    .selectionAndClickExecuteWindowsFromBuildSteps()
                    .enterCommandInExecuteWindowsBuildSteps("echo Hello World");
        } else {
            multiConfigProjectPage
                    .selectionAndClickExecuteShellFromBuildSteps()
                    .enterCommandInExecuteShellBuildSteps("echo Hello World");
        }
        ConsoleOutputPage multiConfigProjectConsole = multiConfigProjectPage
                .clickSaveButton()
                .getSideMenu()
                .clickBuildNow()
                .getSideMenu()
                .clickBuildIcon();

        Assert.assertEquals(multiConfigProjectConsole.getTextConsoleOutputUserName(), new HomePage(getDriver()).getHeader().getUserNameText());
        Assert.assertTrue(multiConfigProjectConsole.getConsoleOutputText().contains("Finished: SUCCESS"));
    }

    @TmsLink("tEFPxZt4")
    @Owner("Nadia Ludanik")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that set build can steps and successfully build the project")
    @Test
    public void testNewestBuildsButton() {
        MultiConfigurationProjectStatusPage mcpStatusPage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setItemName(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .selectMultiConfigurationProjectType()
                .clickOkButton()
                .clickSaveButton()
                .getSideMenu()
                .clickBuildNow()
                .clickBuildHistoryPageNavigationNewestBuild();

        Assert.assertTrue(mcpStatusPage.NewestBuildIsDisplayed());
    }

    @TmsLink("zdWbAye5")
    @Owner("Arailym Ashimova")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that can there wil be create configuration matrix with non build stages")
    @Test
    public void testSetConfigurationMatrix() {
        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectStatusPage configMatrix = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .clickConfigure()
                .scrollAndClickButtonAddAxis()
                .selectUserDefinedAxis()
                .enterNameUserDefinedAxis(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME, "stage", 1)
                .enterValueUserDefinedAxis("sandbox dev uat prod", 1)
                .scrollAndClickButtonAddAxis()
                .selectUserDefinedAxis()
                .enterNameUserDefinedAxis(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME, "maven_tool", 2)
                .enterValueUserDefinedAxis("clean validate compile test", 2)
                .clickApplyButton()
                .clickSaveButton();

        Assert.assertTrue(configMatrix.configurationMatrixIsDisplayed());
    }

    @TmsLink("x19sKw4m")
    @Owner("Kate Bay")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verify that can  set content in three build steps and successfully saved")
    @Test
    public void testSetContentInThreeBuildStepsBuildStatusOnGitHubCommitSaved() {
        final int COUNT_BUILD_STEPS = 3;
        final String contentText = "Content text ";
        String[] expectedContents = new String[COUNT_BUILD_STEPS];
        String[] actualContents = new String[COUNT_BUILD_STEPS];

        ProjectMethodsUtils.createNewMultiConfigurationProject(getDriver(), TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME);

        MultiConfigurationProjectConfigPage configPage = new HomePage(getDriver())
                .clickMultiConfigurationProject(TestDataUtils.MULTI_CONFIGURATION_PROJECT_NAME)
                .getSideMenu()
                .clickConfigure();

        for (int i = 1; i <= COUNT_BUILD_STEPS; i++) {
            configPage
                    .scrollAndClickBuildSteps()
                    .selectionAndClickSetBuildStatusOnGitHubCommitFromBuildSteps()
                    .scrollAndClickLastAdvancedButtonInBuildStepsSection()
                    .setLastContentFieldsInBuildStepsBuildStatusOnGitHubCommit(contentText + i);
            expectedContents[i - 1] = contentText + i;
        }

        configPage
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure();

        for (int i = 1; i <= COUNT_BUILD_STEPS; i++) {
            actualContents[i - 1] = configPage
                    .scrollAndClickSpecificAdvancedButtonInBuildStepsSection(i)
                    .getContentFieldsInBuildStepsBuildStatusOnGitHubCommit(i);
        }
        Assert.assertEquals(Arrays.toString(actualContents), Arrays.toString(expectedContents));
    }
}