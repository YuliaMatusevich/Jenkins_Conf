package model.page;

import io.qameta.allure.Step;
import model.page.base.MainBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageOldDataPage extends MainBasePage {

    public ManageOldDataPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "main-panel")
    private WebElement mainPanel;

    @Step("Get text under the table on the main panel")
    public String getMainPanelNoticeText() {
        String[] actualText = mainPanel.getText().split("\n");

        return actualText[actualText.length - 1];
    }
}
