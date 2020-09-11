Feature: Send Message

  Scenario Outline: Create draft message and send it from drafts folder
    Given user is on Gmail login page
    When user fills email "<email>" and password "<password>"
    Then user is login successfully
    When user creates draft message
    Then user goes to drafts folder
    And user clicks on last draft message
    Then verify all fields of last created draft are saved correctly
    When user sends draft message
    Then verify draft message is sent successfully

    Examples:
      | email                  | password  |
      | test.automv@gmail.com  | testaut50 |
      | test.automv1@gmail.com | testaut51 |
      | test.automv2@gmail.com | testaut52 |
      | test.automv3@gmail.com | testaut53 |
      | test.automv4@gmail.com | testaut54 |