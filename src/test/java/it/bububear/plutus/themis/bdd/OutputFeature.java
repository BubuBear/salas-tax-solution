package it.bububear.plutus.themis.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.bububear.plutus.themis.model.OrderedProduct;
import it.bububear.plutus.themis.model.Product;
import it.bububear.plutus.themis.service.io.OutputDataService;
import it.bububear.plutus.themis.service.io.OutputDataServiceConsole;

public class OutputFeature {

	OutputDataService<String> outputDataService = new OutputDataServiceConsole();
	OrderedProduct orderedProduct;
	String realOutput;

	@Given("ordered product with {int},{string},{string},{double}")
	public void ordered_product_with(Integer quantity, String name, String imported, Double price) {
		Product product = new Product(name, Boolean.valueOf(imported));
		orderedProduct = new OrderedProduct(quantity, product, price, 0.0, 0.0, 0.0, 0.0);
	}

	@When("I transform the ordered product into terminal output")
	public void i_transform_the_ordered_product_into_terminal_output() {
		realOutput = outputDataService.convertOrderedItemIntoOutput(orderedProduct);
	}

	@Then("I verify that the output is {string}")
	public void i_verify_that_the_output_is(String output) {
		assertEquals(output, realOutput);
	}

}
