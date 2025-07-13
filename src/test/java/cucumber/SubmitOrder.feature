@tag
  Feature: Purchase the order from Ecommerce Website

    Background:
      Given I landed on Ecommerce Page

      @Regression
      Scenario Outline: Positive Test of Submitting the order
        Given Logged in with username <name> and password <password>
        When  I add product <productName> to Cart
        And Checkout <productName> and submit the order
        Then "Thankyou for the order." message is displayed on Confirmation Page

        Examples:
          | name               | password     | productName |
          | henry111@gmail.com | Henry180803@ | ZARA COAT 3 |