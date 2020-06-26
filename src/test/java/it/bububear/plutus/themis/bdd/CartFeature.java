package it.bububear.plutus.themis.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.bububear.plutus.themis.service.CartService;
import it.bububear.plutus.themis.service.CartServiceInMemory;
import it.bububear.plutus.themis.test.utils.TestCaseUtils;

public class CartFeature {

	CartService cartServiceInMemory;

	@Given("A cart")
	public void a_cart() {
		cartServiceInMemory = new CartServiceInMemory();
	}

	@When("I add to cart the following product: {string}")
	public void i_add_to_cart_the_following_product(String productName) {

		cartServiceInMemory.addProductToCart(TestCaseUtils.createCartProduct(productName));
	}

	@Then("I will have the following product in cart {string}")
	public void i_will_have_the_following_product_in_cart(String string) {
		assertEquals(string, cartServiceInMemory.getProducts().iterator().next().getProduct().getName());
	}

	@Then("The the size of product cart list will be {int}")
	public void the_the_size_of_product_cart_list_will_be(Integer int1) {
		assertEquals(1, cartServiceInMemory.getProducts().size());
	}

}
