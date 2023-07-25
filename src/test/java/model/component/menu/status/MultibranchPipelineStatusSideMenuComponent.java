package model.component.menu.status;

import io.qameta.allure.Step;
import model.component.base.BaseStatusSideMenuComponent;
import model.page.config.MultibranchPipelineConfigPage;
import model.page.status.MultibranchPipelineStatusPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class MultibranchPipelineStatusSideMenuComponent extends BaseStatusSideMenuComponent<MultibranchPipelineStatusPage, MultibranchPipelineConfigPage> {

    @FindBy(xpath = "//div[@id=\"tasks\"]/*")
    private List<WebElement> menuOptions;

    @Override
    public MultibranchPipelineConfigPage getConfigPage() {
        return new MultibranchPipelineConfigPage(getDriver());
    }

    public MultibranchPipelineStatusSideMenuComponent(WebDriver driver, MultibranchPipelineStatusPage statusPage) {
        super(driver, statusPage);
    }

    @Step("Get the list of side menu items")
    public List<String> getMenuOptions() {
        return menuOptions
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}