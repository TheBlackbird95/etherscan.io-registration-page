import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestRegistrationPageFailedTestsCaptcha extends BaseTest{

    //These test are not totally fine because reCaptcha can not be automated

    @BeforeTest
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to("https://etherscan.io/register");
    }

    @Test
    public void happyPathAllFieldsUsernameAlphanumeric() {
        String username = Faker.instance().bothify("???###?#?#");
        String email = Faker.instance().internet().emailAddress();
        String password = Faker.instance().internet().password();

        registrationPage.inputUsername(username);
        registrationPage.inputEmail(email);
        registrationPage.inputConfirmationEmail(email);
        registrationPage.inputPassword(password);
        registrationPage.inputConfirmationPassword(password);
        registrationPage.agreeWithTermsAndConditions();
        registrationPage.agreeWithSubscription();
        registrationPage.reCaptcha(driver);
        scrollIntoView(registrationPage.getCreateAnAccountButton());
        registrationPage.clickCreateAnAccount();

        Assert.assertTrue(registrationPage.reCaptchaError.isDisplayed());
        //        Assert.assertTrue(registrationPage.successfullyRegistered.isDisplayed());

        registrationPage.reCaptchaHrefLink.click();
    }

    @Test
    public void happyPathMandatoryFieldsOnlyUsername5LettersOnlyPassword5Characters(){
        String username = Faker.instance().bothify("?????");
        String email = Faker.instance().internet().emailAddress();
        String password = Faker.instance().bothify("#?#?!");

        registrationPage.inputUsername(username);
        registrationPage.inputEmail(email);
        registrationPage.inputConfirmationEmail(email);
        registrationPage.inputPassword(password);
        registrationPage.inputConfirmationPassword(password);
        registrationPage.agreeWithTermsAndConditions();
        registrationPage.reCaptcha(driver);
        scrollIntoView(registrationPage.getCreateAnAccountButton());
        registrationPage.clickCreateAnAccount();

        Assert.assertTrue(registrationPage.reCaptchaError.isDisplayed());
        //        Assert.assertTrue(registrationPage.successfullyRegistered.isDisplayed());

        registrationPage.reCaptchaHrefLink.click();
    }

    @Test
    public void successfullyRegisteredUsername30NumbersOnly(){
        String username = Faker.instance().bothify("##############################");
        String email = Faker.instance().internet().emailAddress();
        String password = Faker.instance().internet().password();

        registrationPage.inputUsername(username);
        registrationPage.inputEmail(email);
        registrationPage.inputConfirmationEmail(email);
        registrationPage.inputPassword(password);
        registrationPage.inputConfirmationPassword(password);
        registrationPage.agreeWithTermsAndConditions();
        registrationPage.reCaptcha(driver);
        scrollIntoView(registrationPage.getCreateAnAccountButton());
        registrationPage.clickCreateAnAccount();

        Assert.assertTrue(registrationPage.reCaptchaError.isDisplayed());
        //        Assert.assertTrue(registrationPage.successfullyRegistered.isDisplayed());

        registrationPage.reCaptchaHrefLink.click();
    }
}
