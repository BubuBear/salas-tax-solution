package it.bububear.plutus.themis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.test.utils.TestCaseUtils;

class CartServiceInMemoryTest {

	CartServiceInMemory cartServiceInMemory = new CartServiceInMemory();

	@Test
	void test001AddProductToCart() {
		int id = 1;
		CartProduct cartProduct = TestCaseUtils.createCartProduct(id);
		cartServiceInMemory.addProductToCart(cartProduct);
		CartProduct cartProductFromMap = cartServiceInMemory.cartProductsMap.get(id);
		assertEquals(cartProduct, cartProductFromMap);
	}

	@Test
	void test002GetProducts() {
		List<CartProduct> listOfCartProducts = new LinkedList<>();
		CartProduct cartProduct = TestCaseUtils.createCartProduct(1);
		listOfCartProducts.add(cartProduct);
		cartServiceInMemory.addProductToCart(cartProduct);
		CartProduct cartProductFromList = cartServiceInMemory.getProducts().iterator().next();
		assertEquals(cartProduct, cartProductFromList);
	}

}
