package runner;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtils {

    public static String getRandomStr(int length) {
        return RandomStringUtils.random(length,
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }

    public static String getRandomStr() {
        return getRandomStr(15);
    }

    public static void scrollToEnd(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollToElement_PlaceInCenter(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public static class ExpectedConditions {
        public static ExpectedCondition<WebElement> elementIsNotMoving(final By locator) {
            return new ExpectedCondition<>() {
                private Point location = null;

                @Override
                public WebElement apply(WebDriver driver) {
                    WebElement element;
                    try {
                        element = driver.findElement(locator);
                    } catch (NoSuchElementException e) {
                        return null;
                    }

                    if (element.isDisplayed()) {
                        Point location = element.getLocation();
                        if (location.equals(this.location)) {
                            return element;
                        }
                        this.location = location;
                    }

                    return null;
                }
            };
        }

        public static ExpectedCondition<WebElement> elementIsNotMoving(final WebElement element) {
            return new ExpectedCondition<>() {
                private Point location = null;

                @Override
                public WebElement apply(WebDriver driver) {
                    if (element.isDisplayed()) {
                        Point location = element.getLocation();
                        if (location.equals(this.location)) {
                            return element;
                        }
                        this.location = location;
                    }

                    return null;
                }
            };
        }
    }

    public static boolean isCurrentOSWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    public static String currentDate() {
        Date date = new Date();
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));

        if (day < 10) {

            return new SimpleDateFormat("MMM d, yyyy").format(date);
        }

        return new SimpleDateFormat("MMM dd, yyyy").format(date);
    }

    public static String currentTime() {
        Date date = new Date();
        int hour = Integer.parseInt(new SimpleDateFormat("hh").format(date));

        if (hour < 10) {

            return new SimpleDateFormat("h:mm").format(date);
        }

        return new SimpleDateFormat("hh:mm").format(date);
    }

    public static String currentDayPeriod() {
        Date date = new Date();

        return new SimpleDateFormat("a").format(date);
    }

    public static List<String> getListOfLastKeepElements(int allElements, int keepElements) {
        List<String> listOfLastKeepElements = new ArrayList<>();
        for (int i = 0; i < keepElements; i++) {
            listOfLastKeepElements.add("#" + (allElements - i));
        }
        return listOfLastKeepElements;
    }
}
