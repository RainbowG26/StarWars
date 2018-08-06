package pages;

import libs.ConfigData;
import libs.WebLibrary;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class ParentPage {

    WebDriver driver;
    Logger logger;
    WebLibrary lib;

    public ParentPage(WebDriver driver) {
        this.driver = driver;
        logger = Logger.getLogger(getClass());
        lib = new WebLibrary(driver);
        PageFactory.initElements(driver, this);
    }

    public void getUrl(WebDriver driver, String url) {
        try {
            File fileName = new File(ConfigData.getFilePathValue(url));
            driver.get(String.valueOf(fileName));
            logger.info("Page was opened - " + url);
        } catch (Exception e) {
            logger.error("Page can't opened - " + url + e.getMessage());
            Assert.fail("Page can't opened " + url + e.getMessage());
        }
    }
}
