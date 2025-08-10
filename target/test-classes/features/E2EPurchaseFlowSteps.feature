Feature: Complete user purchase flow at Demoblaze

  Scenario: User signs up, logs in, adds product to cart, places an order, and gets thank you
    Given user opens Demoblaze homepage
    When user registers with a new username and logs in
    And user selects the "Samsung galaxy s6" product
    And user adds the product to the cart
    And user places the order with valid details
    Then user should see a "Thank you for your purchase!" confirmation

