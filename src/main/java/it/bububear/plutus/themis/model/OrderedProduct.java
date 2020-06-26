package it.bububear.plutus.themis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that shapes the data of a Purchased Product
 * 
 * @author BubuBear
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct {

	private int quantity;
	private Product product;
	private double grossPrice;
	private double netUnitPrice;
	private double taxes;
	private double basicTaxes;
	private double importTaxes;

}
