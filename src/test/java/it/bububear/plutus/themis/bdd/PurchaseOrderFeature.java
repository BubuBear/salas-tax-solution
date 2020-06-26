package it.bububear.plutus.themis.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.model.OrderedProduct;
import it.bububear.plutus.themis.model.PurchaseOrder;
import it.bububear.plutus.themis.service.ProductTypeServiceInMemory;
import it.bububear.plutus.themis.service.PuchaseOrderServiceEasy;
import it.bububear.plutus.themis.service.TaxesServiceInMemory;
import it.bububear.plutus.themis.test.utils.TestCaseUtils;

public class PurchaseOrderFeature {

	List<CartProduct> cartProducts = new LinkedList<>();
	PuchaseOrderServiceEasy purchaseOrderService = new PuchaseOrderServiceEasy();
	PurchaseOrder purchaseOrder;
	OrderedProduct orderedProduct;

	@Given("A cart with the product that has {int},{string},{double},{string}")
	public void a_cart_with_the_product_that_has(Integer quantity, String name, Double price, String isImported) {
		CartProduct cartProduct = TestCaseUtils.createCartProduct(1, quantity, name, price,
				Boolean.valueOf(isImported));
		cartProducts.add(cartProduct);
	}

	@When("I place the order")
	public void i_place_the_order() {
		ProductTypeServiceInMemory productTypeServiceInMemory = new ProductTypeServiceInMemory();
		TaxesServiceInMemory taxesServiceInMemory = new TaxesServiceInMemory();
		taxesServiceInMemory.setProductTypeService(productTypeServiceInMemory);
		purchaseOrderService.setTaxesService(taxesServiceInMemory);
		purchaseOrder = purchaseOrderService.placeOrder(cartProducts);
	}

	@When("I select the product which i purchase")
	public void i_select_the_product_which_i_purchase() {
		orderedProduct = purchaseOrder.getOrderedProducts().iterator().next();
	}

	@Then("I will have puchased product quantity: {int}")
	public void i_will_have_puchased_product_quantity(Integer int1) {
		assertEquals(int1, orderedProduct.getQuantity());
	}

	@Then("I will have puchased product name: {string}")
	public void i_will_have_puchased_product_name(String string) {
		assertEquals(string, orderedProduct.getProduct().getName());
	}

	@Then("I will have puchased product gross price: {double}")
	public void i_will_have_puchased_product_gross_price(Double double1) {
		assertEquals(double1, orderedProduct.getGrossPrice());
	}

	@Then("I will have puchased product import status: {string}")
	public void i_will_have_puchased_product_import_status_false(String string) {
		assertEquals(Boolean.valueOf(string), orderedProduct.getProduct().isImported());
	}

	@Then("I will have total order sales tax: {double}")
	public void i_will_have_total_order_sales_tax(Double double1) {
		assertEquals(double1, purchaseOrder.getTotalSalesTax());
	}

	@Then("I will have total order total amount: {double}")
	public void i_will_have_total_order_total_amount(Double double1) {
		assertEquals(double1, purchaseOrder.getTotalAmount());
	}

}
