package it.bububear.plutus.themis.service;

import it.bububear.plutus.themis.model.PurchaseOrder;

/**
 * Purchase Order Management
 * 
 * @author BubuBear
 *
 * @param <I>
 */
public interface PuchaseOrderService<I> {

	/**
	 * Method that takes care of creating a purchase order from input data
	 * 
	 * @param i
	 * @return
	 */
	PurchaseOrder placeOrder(I i);

}
