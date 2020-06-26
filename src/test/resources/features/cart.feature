@cart
Feature: Cart Management

  Scenario Outline: Adding item to cart
    Given A cart
    When I add to cart the following product: "<name-input>"
  	Then I will have the following product in cart "<name-output>"
  	And The the size of product cart list will be <list-size> 

    Examples: 
      | name-input     			      | name-output              | list-size |  
      | book     									| book     								 |	  1      |
      | music CD	 								| music CD	 							 |    1      |
      | chocolate bar 						| chocolate bar 					 |	  1      |
      | box of chocolates 				| box of chocolates 			 |	  1      |
      | bottle of perfume	       	| bottle of perfume	       |	  1      |
      | bottle of perfume	       	| bottle of perfume	       |	  1      |
      | bottle of perfume			   	| bottle of perfume			   |	  1      |
      | packet of headache pills  | packet of headache pills |	  1      |
      | box of chocolates	       	| box of chocolates	       |	  1      |
      | apple			               	| apple			               |	  1      |
