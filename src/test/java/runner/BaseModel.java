package runner;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseModel {

    private final WebDriver driver;
    private final Map<Integer, WebDriverWait> waitMap = new HashMap<>();
    private Actions action;

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait(int seconds) {
        return waitMap.computeIfAbsent(seconds, duration -> new WebDriverWait(driver, Duration.ofSeconds(duration)));
    }

    protected Actions getAction() {
        if (action == null) {
            action = new Actions(driver);
        }

        return action;
    }

    public BaseModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(getDriver(), this);
    }

    @Step("Get the current URL")
    public String getCurrentURL() {

        return getDriver().getCurrentUrl();
    }
}
