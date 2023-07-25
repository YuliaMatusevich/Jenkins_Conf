package model.page;

import io.qameta.allure.Step;
import model.page.base.MainBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConsoleOutputPage extends MainBasePage {

    @FindBy(className = "console-output")
    private WebElement consoleOutput;

    @FindBy(xpath = "//a[@class='jenkins-table__link model-link model-link--float'][1]")
    private WebElement consoleOutputUserName;

    @FindBy(xpath = "//span/span/*[name()='svg' and (@tooltip != 'In progress')]")
    private WebElement buildStatusIcon;

    public ConsoleOutputPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get text of the 'Console Output' log")
    public String getConsoleOutputText() {
        getWait(60).until(ExpectedConditions.visibilityOf(buildStatusIcon));

        return getWait(3).until(ExpectedConditions.visibilityOf(consoleOutput)).getText();
    }

    public String getTextConsoleOutputUserName() {
        return consoleOutputUserName.getText();
    }
}
