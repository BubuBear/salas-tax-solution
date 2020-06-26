package it.bububear.plutus.themis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.bububear.plutus.themis.model.CartProduct;

@Service
public class CartServiceInMemory implements CartService {

	Map<Integer, CartProduct> cartProductsMap = new HashMap<>();

	@Override
	public void addProductToCart(CartProduct cartProduct) {
		cartProductsMap.put(cartProduct.getId(), cartProduct);
	}

	@Override
	public List<CartProduct> getProducts() {
		return cartProductsMap.values().stream().collect(Collectors.toList());
	}

}
