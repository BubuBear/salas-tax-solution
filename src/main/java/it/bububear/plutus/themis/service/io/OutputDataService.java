package it.bububear.plutus.themis.service.io;

import it.bububear.plutus.themis.model.OrderedProduct;
import it.bububear.plutus.themis.model.PurchaseOrder;

public interface OutputDataService<O> {

	O convertOrderedItemIntoOutput(OrderedProduct orderedItem);

	O convertPurchaseOrderIntoOutput(PurchaseOrder purchaseOrder);

}
