import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRegistrationPage extends BaseTest{
    @BeforeTest
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to("https://etherscan.io/register");
    }

    @Test
    public void allFieldsAreBlank(){
        registrationPage.clickCreateAnAccount();
        Assert.assertTrue(registrationPage.usernameError.isDisplayed(), "Username is invalid message should appear!");
        Assert.assertTrue(registrationPage.emailError.isDisplayed(), "Please enter a valid email address message should appear!");
        Assert.assertTrue(registrationPage.confirmEmailError.isDisplayed(), "Please re-enter your email address message should appear!");
        Assert.assertTrue(registrationPage.passwordError.isDisplayed(), "Your password must be at least 5 characters long message should appear!");
        Assert.assertTrue(registrationPage.confirmPasswordError.isDisplayed(), "Your password must be at least 5 characters long message should appear!");
        Assert.assertTrue(registrationPage.termsError.isDisplayed(), "Please accept our Terms and Conditions. message should appear!");
    }

    @Test
    public void allMandatoryFieldsAreBlank(){
        registrationPage.agreeWithSubscription();
        registrationPage.clickCreateAnAccount();
        Assert.assertTrue(registrationPage.usernameError.isDisplayed(), "Username is invalid message should appear!");
        Assert.assertTrue(registrationPage.emailError.isDisplayed(), "Please enter a valid email address message should appear!");
        Assert.assertTrue(registrationPage.confirmEmailError.isDisplayed(), "Please re-enter your email address message should appear!");
        Assert.assertTrue(registrationPage.passwordError.isDisplayed(), "Your password must be at least 5 characters long message should appear!");
        Assert.assertTrue(registrationPage.confirmPasswordError.isDisplayed(), "Your password must be at least 5 characters long message should appear!");
        Assert.assertTrue(registrationPage.termsError.isDisplayed(), "Please accept our Terms and Conditions. message should appear!");
    }

    @Test
    public void reCaptchaNotChecked(){
        String username = Faker.instance().bothify("##??????");
        String email = Faker.instance().internet().emailAddress();
        String password = Faker.instance().internet().password();

        registrationPage.inputUsername(username);
        registrationPage.inputEmail(email);
        registrationPage.inputConfirmationEmail(email);
        registrationPage.inputPassword(password);
        registrationPage.inputConfirmationPassword(password);
        registrationPage.agreeWithTermsAndConditions();
        registrationPage.clickCreateAnAccount();
        Assert.assertTrue(registrationPage.reCaptchaError.isDisplayed(), "reCaptcha alert should appear!");
        registrationPage.reCaptchaHrefLink.click();
    }

    @Test
    public void termsAndConditionsHyperlink() throws InterruptedException {
        registrationPage.goToTermsAndConditions();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://etherscan.io/terms", "New tab should be opened with url: https://etherscan.io/terms");
        driver.close();
        driver.switchTo().window(tabs.get(0));
        Assert.assertEquals(driver.getCurrentUrl(), "https://etherscan.io/register", "URL https://etherscan.io/register should be opened!");
    }

    @Test
    public void unsubscribeHyperlink(){
        registrationPage.goToHowToUnsubscribe();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://info.etherscan.com/how-to-subscribe-unsubscribe-newsletter/", "New tab should be opened with url: https://info.etherscan.com/how-to-subscribe-unsubscribe-newsletter/");
        driver.close();
        driver.switchTo().window(tabs.get(0));
        Assert.assertEquals(driver.getCurrentUrl(), "https://etherscan.io/register", "URL https://etherscan.io/register should be opened!");
    }

    @Test
    public void verifyPlaceholders(){
        Assert.assertEquals(registrationPage.getAttributeUsername("placeholder"), "Username has to be from 5 to 30 characters in length, only alphanumeric characters allowed.", "Username placeholder is wrong!");
        Assert.assertEquals(registrationPage.getAttributeEmail("placeholder"), "A confirmation code will be sent to this address", "Email Address placeholder is wrong!");
        Assert.assertEquals(registrationPage.getAttributeConfirmEmail("placeholder"), "Re-enter your email address", "Confirm Email Address placeholder is wrong!");
        Assert.assertEquals(registrationPage.getAttributePassword("placeholder"), "******", "Password placeholder is wrong!");
        Assert.assertEquals(registrationPage.getAttributeConfirmPassword("placeholder"), "******", "Confirm Password placeholder is wrong!");
    }

    @Test
    public void usernameTooShort(){
        String username = Faker.instance().bothify("??##");

        registrationPage.inputUsername(username);
        Assert.assertTrue(registrationPage.usernameError.isDisplayed());
        Assert.assertEquals(registrationPage.usernameError.getText(), "Username is invalid.");
    }

    @Test
    public void usernameContainsSymbol(){
        String username = Faker.instance().bothify("???@###");

        registrationPage.inputUsername(username);
        Assert.assertTrue(registrationPage.usernameError.isDisplayed());
        Assert.assertEquals(registrationPage.usernameError.getText(), "Username is invalid.");
    }

    @Test
    public void invalidEmailAddress(){
        String email = Faker.instance().internet().emailAddress().replace("@", "l");

        registrationPage.inputEmail(email);
        Assert.assertTrue(registrationPage.emailError.isDisplayed());
        Assert.assertEquals(registrationPage.emailError.getText(), "Please enter a valid email address.");
    }

    @Test
    public void confirmationEmailAddressIsDifferent(){
        String emailAddress = Faker.instance().internet().emailAddress();
        String confirmationEmailAddress = Faker.instance().internet().emailAddress();

        registrationPage.inputEmail(emailAddress);
        registrationPage.inputConfirmationEmail(confirmationEmailAddress);
        Assert.assertTrue(registrationPage.confirmEmailError.isDisplayed());
        Assert.assertEquals(registrationPage.confirmEmailError.getText(), "Email address does not match.");
    }

    @Test
    public void confirmationEmailAddressIsInvalid(){
        String emailAddress = Faker.instance().internet().emailAddress();
        String confirmationEmailAddress = Faker.instance().internet().emailAddress().replace("@", "l");

        registrationPage.inputEmail(emailAddress);
        registrationPage.inputConfirmationEmail(confirmationEmailAddress);
        Assert.assertTrue(registrationPage.confirmEmailError.isDisplayed());
        Assert.assertEquals(registrationPage.confirmEmailError.getText(), "Please re-enter your email address.");
    }

    @Test
    public void passwordTooShort(){
        String password = Faker.instance().bothify("?#?#");

        registrationPage.inputPassword(password);
        Assert.assertTrue(registrationPage.passwordError.isDisplayed());
        Assert.assertEquals(registrationPage.passwordError.getText(), "Your password must be at least 5 characters long.");
    }

    @Test
    public void confirmationPasswordIsDifferent(){
        String password = Faker.instance().internet().password();
        String confirmationPassword = Faker.instance().internet().password();

        registrationPage.inputPassword(password);
        registrationPage.inputConfirmationPassword(confirmationPassword);
        Assert.assertTrue(registrationPage.confirmPasswordError.isDisplayed());
        Assert.assertEquals(registrationPage.confirmPasswordError.getText(), "Password does not match, please check again.");
    }

    @Test
    public void confirmationPasswordIsTooShort(){
        String password = Faker.instance().internet().password();
        String confirmationPassword = Faker.instance().bothify("?#?#");

        registrationPage.inputPassword(password);
        registrationPage.inputConfirmationPassword(confirmationPassword);
        Assert.assertTrue(registrationPage.confirmPasswordError.isDisplayed());
        Assert.assertEquals(registrationPage.confirmPasswordError.getText(), "Your password must be at least 5 characters long.");
    }

    @Test
    public void tabButtonFunctionality(){
        registrationPage.getUsernameInput().click();
        registrationPage.getUsernameInput().sendKeys(Keys.TAB);
        Assert.assertTrue(registrationPage.getEmailInput().isEnabled());
        Assert.assertTrue(registrationPage.emailError.isDisplayed());
        registrationPage.getEmailInput().sendKeys(Keys.TAB);
        Assert.assertTrue(registrationPage.getConfirmEmailInput().isEnabled());
        Assert.assertTrue(registrationPage.confirmEmailError.isDisplayed());
        registrationPage.getConfirmEmailInput().sendKeys(Keys.TAB);
        Assert.assertTrue(registrationPage.passwordError.isDisplayed());
        Assert.assertTrue(registrationPage.getPasswordInput().isEnabled());
        registrationPage.getPasswordInput().sendKeys(Keys.TAB);
        Assert.assertTrue(registrationPage.getConfirmPasswordInput().isEnabled());
        Assert.assertTrue(registrationPage.confirmPasswordError.isDisplayed());
    }

    @Test
    public void passwordStrength(){
        String weakPassword = "asdqwe";
        String mediumPassword = "asdqwe123";
        String strongPassword = "Milos135!";

        registrationPage.inputPassword(weakPassword);
        Assert.assertTrue(registrationPage.passwordStrengthNotification.isDisplayed());
        Assert.assertEquals(registrationPage.passwordStrengthNotification.getText(), "Strength: Weak!", "This should be weak password!");

        registrationPage.inputPassword(mediumPassword);
        Assert.assertTrue(registrationPage.passwordStrengthNotification.isDisplayed());
        Assert.assertEquals(registrationPage.passwordStrengthNotification.getText(), "Strength: Medium!", "This should be medium password!");

        registrationPage.inputPassword(strongPassword);
        Assert.assertTrue(registrationPage.passwordStrengthNotification.isDisplayed());
        Assert.assertEquals(registrationPage.passwordStrengthNotification.getText(), "Strength: Strong!", "This should be strong password!");
    }
}
