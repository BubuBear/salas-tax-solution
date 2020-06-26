@order
Feature: Purchase Order Management


  Scenario Outline: Management of One Product in Cart
    Given A cart with the product that has <input-quantity>,"<input-name>",<input-price>,"<input-import-status>"
    When I place the order
    And I select the product which i purchase
    Then I will have puchased product quantity: <quantity>
    And  I will have puchased product name: "<name>"
    And  I will have puchased product gross price: <gross-price>
    And  I will have puchased product import status: "<import-status>"
    And  I will have total order sales tax: <total-sales-tax>
    And  I will have total order total amount: <total-amount>


    Examples: 
      | input-quantity | input-name                      | input-price  | input-import-status | quantity| name                      | gross-price  | import-status | total-sales-tax | total-amount |
			| 2   					 | book			                       | 12.49        | false               | 2   	  | book			                | 24.98        | false         | 0               |  24.98       |
			| 1   					 | music CD			                   | 14.99        | false               | 1   	  | music CD			            | 16.49        | false         | 1.5             |  16.49       |
			| 1   					 | chocolate bar			             | 0.85         | false               | 1   	  | chocolate bar			        | 0.85         | false         | 0               |  0.85        |
			| 1   					 | box of chocolates	             | 10.00        | true                | 1   	  | box of chocolates	        | 10.50        | true          | 0.50            |  10.50       |
			| 1   					 | bottle of perfume	             | 47.50        | true                | 1   	  | bottle of perfume	        | 54.65        | true          | 7.15            |  54.65       |
			| 1   					 | bottle of perfume	             | 27.99        | true                | 1   	  | bottle of perfume	        | 32.19        | true          | 4.2             |  32.19       |
			| 1   					 | bottle of perfume			         | 18.99        | false               | 1   	  | bottle of perfume			    | 20.89        | false         | 1.9             |  20.89       |
			| 1   					 | packet of headache pills        | 9.75         | false               | 1   	  | packet of headache pills  | 9.75         | false         | 0               |  9.75        |
			| 3   					 | box of chocolates	             | 11.25        | true                | 3   	  | box of chocolates	        | 35.55        | true          | 1.8             |  35.55       |
			| 100   				 | apple			                     | 0.30         | false               | 100   	| apple			                | 30           | false         | 0               |  30          |
                                                                                                                                                                                     