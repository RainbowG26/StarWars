package libs;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebLibrary {

    private WebDriver driver;
    private Logger logger;
    private WebDriverWait webDriverWait15;

    public WebLibrary(WebDriver driver) {
        this.driver = driver;
        logger = Logger.getLogger(getClass());
        webDriverWait15 = new WebDriverWait(driver, 15);
    }

    public void setDriverSettings(WebDriver driver, int timeout) {
        logger.info("Get resolution of window");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        System.out.println(driver.manage().window().getSize().width + ": Resolution Width");
        System.out.println(driver.manage().window().getSize().height + ": Resolution Height");
        logger.info("Done");
        logger.info("Set WebDriverWait " + timeout + " seconds");
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        logger.info("Done");
    }

    public void setText(WebElement element, String key) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Clearing text field");
            element.clear();
            logger.info("Set text into " + element);
            File fileName = new File(ConfigData.getFilePathValue(key));
            element.sendKeys(fileName.getName());
            logger.info("Was inputted - " + key);
        } catch (Exception e) {
            logger.error("Element is missing - " + key + e.getMessage());
            Assert.fail("Element is missing - " + key + e.getMessage());
        }
    }

    public void clickOnElement(WebElement element) {
        try {
            if (isElementPresent(element)) {
                element.click();
                logger.info("Element was clicked");
            } else {
                logger.error("Element is missing - " + element);
                Assert.fail("Element is missing - " + element);
            }
        } catch (Exception e) {
            logger.error("Element is missing - " + element + e.getMessage());
            Assert.fail("Element is missing - " + element + e.getMessage());
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            logger.info("Get text / Element was found");
            element.getText();
            logger.info("Done");
            return true;
        } catch (Exception e) {
            logger.error("Element is missing - " + element + e.getMessage());
            Assert.fail("Element is missing - " + element + e.getMessage());
            return false;
        }
    }

    public String getText(WebElement element) {
        String elementText = "";
        try {
            if (isElementPresent(element) == true) {
                elementText = element.getText().trim();
                logger.info("Getting text from web element - " + element);
            } else {
                logger.error("Can't get text from element - " + element);
                Assert.fail("Can't get text from element - " + element);
            }
        } catch (Exception e) {
            logger.error("Can't get text from element - " + element + e.getMessage());
            Assert.fail("Can't get text from element - " + element + e.getMessage());
        }
        return elementText;
    }

    public void waitABit(int second) {
        logger.info("Wait - " + second);
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            logger.info("Exception with method Thread.sleep()");
            Assert.fail("Exception with method Thread.sleep()");
        }
    }

    public void screenShot(WebDriver driver, String pathToScreenShot) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(pathToScreenShot));
            logger.info("ScreenShot: " + pathToScreenShot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
