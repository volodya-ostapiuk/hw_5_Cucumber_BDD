package com.epam.steps;

import com.epam.business.GmailLogInBO;
import com.epam.business.GmailMessageBO;
import com.epam.model.MessageEntity;
import com.epam.utils.Constants;
import com.epam.utils.providers.DriverProvider;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class StepDefinition implements Constants {
    private static Logger logger = LogManager.getLogger(StepDefinition.class);
    private GmailLogInBO logInBO;
    private GmailMessageBO gmailMessageBO;
    private String userGmail;

    public StepDefinition() {
        logInBO = new GmailLogInBO();
        gmailMessageBO = new GmailMessageBO();
    }

    @Before
    public void setUp(){
        DriverProvider.getInstance().get(BASE_URL);
    }

    @Given("^user is on Gmail login page$")
    public void userIsOnGmailLoginPage() {
        DriverProvider.getInstance().get(BASE_URL);
    }

    @When("^user fills email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void userFillsEmailAndPassword(String userEmail, String userPassword) {
        userGmail = userEmail;
        logInBO.logIn(userEmail, userPassword);
    }

    @Then("^user is login successfully$")
    public void userIsLoginSuccessfully() {
        Assert.assertTrue(logInBO.getPageTitle().contains(userGmail.toLowerCase()), WRONG_LOGIN);
        logger.info("Successful login.");
    }

    @When("^user creates draft message$")
    public void userCreatesDraftMessage() {
        gmailMessageBO.createDraftMessage(TEST_MESSAGE);
    }

    @Then("^user goes to drafts folder$")
    public void userGoesToDraftsFolder() {
        gmailMessageBO.goToDraftsFolder();
    }

    @And("^user clicks on last draft message$")
    public void userClicksOnLastDraftMessage() {
        gmailMessageBO.clickOnLastDraftMessage();
    }

    @Then("^verify all fields of last created draft are saved correctly$")
    public void verifyAllFieldsOfLastCreatedDraftAreSavedCorrectly() {
        MessageEntity filledDraftMessage = gmailMessageBO.getDraftMessageEntity();
        Assert.assertEquals(filledDraftMessage, TEST_MESSAGE, WRONG_SAVED_DRAFT);
        logger.info("Fields of last draft letter are saved properly.");
    }

    @When("^user sends draft message$")
    public void userSendsDraftMessage() {
        gmailMessageBO.sendLastDraftMessage();
    }

    @Then("^verify draft message is sent successfully$")
    public void verifyDraftMessageIsSentSuccessfully() {
        //Assert додати
        logger.info("Message is sent successfully.");
    }

    @After
    public void tearDown(){
        DriverProvider.quit();
    }
}
