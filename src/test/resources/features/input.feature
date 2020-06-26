@input
Feature: Input Management

  Scenario Outline: Data transformation from Terminal
    Given an input to add to cart "<input-string>"
    When I transform the input into the cart item
    Then I verify that the quantity is <input-quantity>
  	And I verify that the name of the product is "<input-name>"
  	And I verify that the price of the product is <input-price>
  	And I verify that the imported status of the product is "<input-import-status>"

    Examples: 
      | input-string     			                  | input-quantity | input-name                      | input-price  | input-import-status |
      | 2 book at 12.49  			                  | 2   					 | book			                       | 12.49        | false               |
      | 1 music CD at 14.99  	                  | 1   					 | music CD			                   | 14.99        | false               |
      | 1 chocolate bar at 0.85  	              | 1   					 | chocolate bar			             | 0.85         | false               |
      | 1 imported box of chocolates at 10.00  	| 1   					 | box of chocolates	             | 10.00        | true                |
      | 1 imported bottle of perfume at 47.50  	| 1   					 | bottle of perfume	             | 47.50        | true                |
      | 1 imported bottle of perfume at 27.99  	| 1   					 | bottle of perfume	             | 27.99        | true                |
      | 1 bottle of perfume at 18.99          	| 1   					 | bottle of perfume			         | 18.99        | false               |
      | 1 packet of headache pills at 9.75      | 1   					 | packet of headache pills        | 9.75         | false               |
      | 3 box of imported chocolates at 11.25   | 3   					 | box of chocolates	             | 11.25        | true                |
      | 1 imported apple at 0.85  	            | 1   					 | apple			                     | 0.85         | true                |
     

