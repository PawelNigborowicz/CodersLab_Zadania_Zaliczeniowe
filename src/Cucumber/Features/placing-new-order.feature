Feature: Placing new order

Scenario: Placing a new order for Hummingbird Printed Sweater

  Given I'm on the "https://mystore-testlab.coderslab.pl" page
  When I log in using email "hhunbhpyknwhgwmqkq@tcwlx.com" and password "12345"
  And I go to the Clothes product list page
  And I see that Hummingbird Printed Sweater is 20% discounted
  Then I add 5 units of size "M" to my basket
  When I proceed to checkout