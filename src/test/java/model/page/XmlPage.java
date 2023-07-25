package model.page;

import model.page.base.MainBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class XmlPage extends MainBasePage {

    @FindBy(css = "body > div.header>span")
    private WebElement textOnPageXML;

    public XmlPage(WebDriver driver) {
        super(driver);
    }

    public String getStructureXML() {
        return textOnPageXML.getText();
    }
}
