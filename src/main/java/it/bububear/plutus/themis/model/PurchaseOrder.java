package it.bububear.plutus.themis.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that models the data contained in a purchase order
 * 
 * @author BubuBear
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

	private String orderName;
	private List<OrderedProduct> orderedProducts;
	private double totalSalesTax;
	private double totalAmount;

}
