Feature: Adding new address

  Scenario: Adding the second address as an existing user

    Given Im'm on the "https://mystore-testlab.coderslab.pl"
    When I sign in using email "hhunbhpyknwhgwmqkq@tcwlx.com" and password "12345"
    And I click on the Addresses icon in Your account section
    Then I land on the "https://mystore-testlab.coderslab.pl/index.php?controller=addresses" page
