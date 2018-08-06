package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    public WebElement btnSingIn;

    @FindBy(xpath = ".//input[@type='email']")
    public WebElement fieldEmail;

    @FindBy(xpath = ".//div[@id='identifierNext']")
    public WebElement btnNext;

    @FindBy(xpath = ".//div[@id='profileIdentifier']")
    public WebElement profileIdentifier;

    @FindBy(xpath = ".//input[@type='password']")
    public WebElement fieldPass;

    @FindBy(xpath = ".//div[@id='passwordNext']")
    public WebElement btnPassNext;

    @FindBy(xpath = ".//div[contains(text(),'НАПИСАТЬ')]")
    public WebElement titleInbox;

    @FindBy(xpath = ".//div[contains(text(),'Не удалось найти аккаунт Google')]")
    public WebElement inValidTitle;

    public void openLoginPage(String url){
        getUrl(driver, url);
    }

    public void clickBtnSingIn(){
        lib.clickOnElement(btnSingIn);
    }

    public void enterLoginToInput(String login){
    lib.setText(fieldEmail, login);
    }

    public void enterBtnNext(){
        lib.clickOnElement(btnNext);
    }

    public void enterPassToInput(String Pass){
        lib.setText(fieldPass, Pass);
    }

    public void enterBtnPassNext(){
        lib.clickOnElement(btnPassNext);
    }
}
