@output
Feature: Output Management

  Scenario Outline: Data transformation from Terminal
    Given ordered product with <quantity>,"<name>","<imported>",<gross-price>
    When I transform the ordered product into terminal output
    Then I verify that the output is "<output-value>"

    Examples: 
      | quantity | name                     | gross-price  | imported | output-value     			              |      
      |  2   		 | book			                | 24.98        | false    | 2 book: 24.98			                  |
      |  1   		 | music CD			            | 16.49        | false    | 1 music CD: 16.49	                  |
      |  1   		 | chocolate bar			      | 0.85         | false    | 1 chocolate bar: 0.85               |
      |  1   		 | box of chocolates	      | 10.50        | true     | 1 imported box of chocolates: 10.50	|
      |  1   		 | bottle of perfume	      | 54.65        | true     | 1 imported bottle of perfume: 54.65 |
      |  1   		 | bottle of perfume	      | 32.19        | true     | 1 imported bottle of perfume: 32.19 |	
      |  1   		 | bottle of perfume			  | 20.89        | false    | 1 bottle of perfume: 20.89         	|
      |  1   		 | packet of headache pills | 9.75         | false    | 1 packet of headache pills: 9.75    | 
      |  3   		 | box of chocolates	      | 35.55        | true     | 3 imported box of chocolates: 35.55 |
     

