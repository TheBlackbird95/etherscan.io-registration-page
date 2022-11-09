import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RegistrationPage{
    private WebDriver driver;
    private WebDriverWait wdwait;

    public RegistrationPage (WebDriver driver,WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
        PageFactory.initElements(driver, this);
    }
    @FindBy (id = "ContentPlaceHolder1_txtUserName")
    private WebElement usernameInput;

    @FindBy (id = "ContentPlaceHolder1_txtEmail")
    private WebElement emailInput;

    @FindBy (id = "ContentPlaceHolder1_txtConfirmEmail")
    private WebElement confirmEmailInput;

    @FindBy (id = "ContentPlaceHolder1_txtPassword")
    private WebElement passwordInput;

    @FindBy (id = "ContentPlaceHolder1_txtPassword2")
    private WebElement confirmPasswordInput;

    @FindBy (id = "ContentPlaceHolder1_btnRegister")
    private WebElement createAnAccountButton;

    @FindBy (xpath = "/html[1]/body[1]/div[1]/main[1]/main[1]/div[1]/form[1]/div[4]/div[7]/div[1]/div[1]/div[1]/iframe[1]")
    private WebElement reCaptchaIframe;

    @FindBy (id = "recaptcha-anchor")
    private WebElement reCaptchaCheckbox;

    @FindBy (className = "link-muted")
    private List<WebElement> hyperlinks;

    @FindBy (id = "ContentPlaceHolder1_MyCheckBox")
    private WebElement termsCheckBox;

    @FindBy (id = "ContentPlaceHolder1_SubscribeNewsletter")
    private WebElement subscriptionCheckBox;

    @FindBy (id = "ContentPlaceHolder1_txtUserName-error")
    public WebElement usernameError;

    @FindBy (id = "ContentPlaceHolder1_txtEmail-error")
    public WebElement emailError;

    @FindBy (id = "ContentPlaceHolder1_txtConfirmEmail-error")
    public WebElement confirmEmailError;

    @FindBy (id = "ContentPlaceHolder1_txtPassword-error")
    public WebElement passwordError;

    @FindBy (id = "ContentPlaceHolder1_txtPassword2-error")
    public WebElement confirmPasswordError;

    @FindBy (id = "ctl00$ContentPlaceHolder1$MyCheckBox-error")
    public WebElement termsError;

    @FindBy (css = ".alert.alert-danger")
    public WebElement reCaptchaError;

    @FindBy (xpath = "//a[contains(text(),'Please Try Again')]")
    public WebElement reCaptchaHrefLink;

    @FindBy (css = ".alert.alert-info")
    public WebElement successfullyRegistered;

    @FindBy (id = "passstrength")
    public WebElement passwordStrengthNotification;

    public void reCaptcha(WebDriver driver){
        driver.switchTo().frame(reCaptchaIframe);
        reCaptchaCheckbox.click();
        driver.switchTo().parentFrame();
    }

    //region Getters
    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getConfirmEmailInput() {
        return confirmEmailInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getConfirmPasswordInput() {
        return confirmPasswordInput;
    }

    public WebElement getCreateAnAccountButton() {
        return createAnAccountButton;
    }
    //endregion

    //region getAttribute
    public String getAttributeUsername(String attribute){
       return usernameInput.getAttribute(attribute);
    }
    public String getAttributeEmail(String attribute){
        return emailInput.getAttribute(attribute);
    }
    public String getAttributeConfirmEmail(String attribute){
        return confirmEmailInput.getAttribute(attribute);
    }
    public String getAttributePassword(String attribute){
        return passwordInput.getAttribute(attribute);
    }
    public String getAttributeConfirmPassword(String attribute){
        return confirmPasswordInput.getAttribute(attribute);
    }

    //endregion

    public void goToTermsAndConditions(){
        hyperlinks.get(0).click();
    }

    public void goToHowToUnsubscribe(){
        hyperlinks.get(1).click();
    }

    public void inputUsername(String username){
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void inputEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void inputConfirmationEmail (String email){
        confirmEmailInput.clear();
        confirmEmailInput.sendKeys(email);
    }

    public void inputPassword (String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void inputConfirmationPassword (String password){
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
    }

    public void agreeWithTermsAndConditions(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", termsCheckBox);;
    }

    public void agreeWithSubscription(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", subscriptionCheckBox);;
    }

    public void clickCreateAnAccount(){
        createAnAccountButton.click();
    }
}
