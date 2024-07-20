
@tag
Feature: Purchase the Order from Ecommerce Website 
  I want to use this template for my feature file
  
  Background:
  Given I landed on ecommerce Page


  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from cart 
    And checkout <productName> and submit the order 
    Then "THANKYOU FOR THE ORDER." message is displayed on confimationPage

    Examples: 
      | name             | password   | productName |
      | rena30@gmail.com | Renadai123 | ZARA COAT 3 |
    
