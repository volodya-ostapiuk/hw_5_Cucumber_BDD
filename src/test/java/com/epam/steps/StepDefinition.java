package com.epam.steps;

import com.epam.business.GmailLogInBO;
import com.epam.business.GmailMessageBO;
import com.epam.model.MessageEntity;
import com.epam.utils.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StepDefinition implements Constants {
    private GmailLogInBO logInBO;
    private GmailMessageBO gmailMessageBO;
    private String userGmail;

    public StepDefinition() {
        logInBO = new GmailLogInBO();
        gmailMessageBO = new GmailMessageBO();
    }

    @When("^user fills email as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void logIn(String userEmail, String userPassword) {
        userGmail = userEmail;
        logInBO.logIn(userEmail, userPassword);
    }

    @Then("^verify user is login successfully$")
    public void verifyLogIn() {
        Assert.assertTrue(logInBO.getPageTitle().contains(userGmail.toLowerCase()), WRONG_LOGIN);
    }

    @When("^user creates draft message$")
    public void createDraftMessage() {
        gmailMessageBO.createDraftMessage(TEST_MESSAGE);
    }

    @Then("^user goes to drafts folder$")
    public void goToDraftsFolder() {
        gmailMessageBO.goToDraftsFolder();
    }

    @And("^user clicks on last draft message$")
    public void clickOnLastDraftMessage() {
        gmailMessageBO.clickOnLastDraftMessage();
    }

    @Then("^verify all fields of last created draft are saved correctly$")
    public void verifyAllFieldsOfLastCreatedDraftAreSavedCorrectly() {
        MessageEntity filledDraftMessage = gmailMessageBO.getDraftMessageEntity();
        Assert.assertEquals(filledDraftMessage.toString(), TEST_MESSAGE.toString(), WRONG_SAVED_DRAFT);
    }

    @When("^user sends draft message$")
    public void sendDraftMessage() {
        gmailMessageBO.sendLastDraftMessage();
    }

    @Then("^verify draft message is sent successfully$")
    public void verifyDraftMessageIsSentSuccessfully() {
        Assert.assertTrue(gmailMessageBO.isDraftSent(), WRONG_DRAFT_SENT);
    }
}
