package loginTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import parentTests.ParentTest;

import java.io.File;

public class LoginTest extends ParentTest {

    public LoginTest(){
    }

    @Before
    public void setBrowserSetting() {
        lib.setDriverSettings(driver, 10);
    }

    @After
    public void saveScreenShot() {
        File file = new File("");
        pathToScreenShot = file.getAbsolutePath() + "\\target\\screenshot\\"
                + this.getClass().getSimpleName() + "\\" + this.testName.getMethodName() + ".jpg";
        lib.waitABit(5);
        lib.screenShot(driver, pathToScreenShot);
    }

    @Test
    public void validLoginTest() {
        loginPage.openLoginPage("Url_test");
        loginPage.clickBtnSingIn();
        loginPage.enterLoginToInput("validLogin");
        loginPage.enterBtnNext();
        soft.assertThat(lib.getText(loginPage.profileIdentifier))
                .as("Expected profile Identifier - testdmitry5@gmail.com")
                .isEqualTo("testdmitry5@gmail.com");
        loginPage.enterPassToInput("validPass");
        loginPage.enterBtnPassNext();
        soft.assertThat(lib.getText(loginPage.titleInbox))
                .as("Expected title inbox - НАПИСАТЬ")
                .isEqualTo("НАПИСАТЬ");
    }

    @Test
    public void inValidLoginTest() {
        loginPage.openLoginPage("Url_test");
        loginPage.clickBtnSingIn();
        loginPage.enterLoginToInput("inValidLogin");
        loginPage.enterBtnNext();
        soft.assertThat(lib.getText(loginPage.inValidTitleRU))
                .as("Expected invalid title - Не удалось найти аккаунт Google")
                .isEqualTo("Не удалось найти аккаунт Google");
    }
}
