package it.bububear.plutus.themis.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.bububear.plutus.themis.exception.ConvertInputException;
import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.service.io.InputDataService;
import it.bububear.plutus.themis.service.io.InputDataServiceConsole;

public class InputFeature {

	InputDataService<String> inputDataService = new InputDataServiceConsole();
	String inputString;
	CartProduct cartProduct;

	@Given("an input to add to cart {string}")
	public void an_input_to_add_to_cart(String string) {
		inputString = string;
	}

	@When("I transform the input into the cart item")
	public void i_transform_the_input_into_the_cart_item() throws ConvertInputException {
		cartProduct = inputDataService.convertIntputInCartProduct(inputString);
	}

	@Then("I verify that the quantity is {int}")
	public void i_verify_that_the_quantity_is(Integer int1) {
		assertEquals(int1, cartProduct.getQuantity());
	}

	@Then("I verify that the name of the product is {string}")
	public void i_verify_that_the_name_of_the_product_is(String string) {
		assertEquals(string, cartProduct.getProduct().getName());
	}

	@Then("I verify that the price of the product is {double}")
	public void i_verify_that_the_price_of_the_product_is(Double double1) {
		assertEquals(double1, cartProduct.getUnitPrice());
	}

	@Then("I verify that the imported status of the product is {string}")
	public void i_verify_that_the_imported_status_of_the_product_is(String string) {
		assertEquals(Boolean.valueOf(string), cartProduct.getProduct().isImported());
	}

}
