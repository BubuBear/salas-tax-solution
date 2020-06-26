package it.bububear.plutus.themis.service;

import java.util.List;

import it.bububear.plutus.themis.model.CartProduct;

/**
 * Cart management
 * 
 * @author BubuBear
 *
 */
public interface CartService {

	void addProductToCart(CartProduct cartProduct);

	/**
	 * Back to the list of items saved in the cart
	 * 
	 * @return
	 */
	List<CartProduct> getProducts();
}
