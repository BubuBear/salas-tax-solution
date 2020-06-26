package it.bububear.plutus.themis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that models the data in a product inside the cart
 * 
 * @author BubuBear
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
	private int id;
	private Product product;
	private int quantity;
	private double unitPrice;

}
