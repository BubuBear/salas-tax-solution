package it.bububear.plutus.themis.test.utils;

import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.model.OrderedProduct;
import it.bububear.plutus.themis.model.Product;

public class TestCaseUtils {

	public static CartProduct createCartProduct(Integer id, Integer quantity, String name, Double price,
			Boolean isImported) {
		CartProduct cartProduct = createEmptyCartProduct();
		cartProduct.setId(id);
		cartProduct.setQuantity(quantity);
		cartProduct.setUnitPrice(price);
		cartProduct.getProduct().setName(name);
		cartProduct.getProduct().setImported(isImported);
		return cartProduct;
	}

	public static CartProduct createCartProduct(String productName) {
		CartProduct cartProduct = createEmptyCartProduct();
		cartProduct.getProduct().setName(productName);
		return cartProduct;
	}

	public static CartProduct createCartProduct(Integer id) {
		CartProduct cartProduct = createEmptyCartProduct();
		cartProduct.setId(id);
		return cartProduct;
	}

	public static CartProduct createEmptyCartProduct() {
		Product product = new Product();
		CartProduct cartProduct = new CartProduct();
		cartProduct.setProduct(product);
		return cartProduct;
	}

	public static OrderedProduct createOrderedProduct(Integer quantity, String name, Boolean isImported,
			Double netUnitPrice) {
		OrderedProduct orderedProduct = new OrderedProduct();
		orderedProduct.setQuantity(quantity);
		orderedProduct.setNetUnitPrice(netUnitPrice);
		orderedProduct.setProduct(new Product(name, isImported));

		return orderedProduct;
	}

}
