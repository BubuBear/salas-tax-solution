package it.bububear.plutus.themis.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.model.OrderedProduct;
import it.bububear.plutus.themis.model.Product;
import it.bububear.plutus.themis.model.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;

@Service
public class PuchaseOrderServiceEasy implements PuchaseOrderService<List<CartProduct>> {
	int orderId = 1;

	@Getter
	@Setter
	@Autowired
	TaxesService<String, Product> taxesService;

	@Override
	public PurchaseOrder placeOrder(List<CartProduct> cartProducts) {

		List<OrderedProduct> orderedProducts = map(cartProducts);
		applyBasicTax(orderedProducts);
		applyImportTax(orderedProducts);
		calculateTaxes(orderedProducts);
		calculateGrossValue(orderedProducts);
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setOrderName(String.format("Output: %s", orderId));
		purchaseOrder.setOrderedProducts(orderedProducts);
		purchaseOrder.setTotalSalesTax(caclulateTotalTaxes(purchaseOrder.getOrderedProducts()));
		purchaseOrder.setTotalAmount(caclulateTotalAmount(purchaseOrder.getOrderedProducts()));
		return purchaseOrder;
	}

	/**
	 * It takes the list of products applied and applies the basic tax to each of
	 * them
	 * 
	 * @param orderedProducts
	 */
	void applyBasicTax(List<OrderedProduct> orderedProducts) {
		orderedProducts.stream().forEach(op -> {
			double tax = taxesService.getBasicSalesTax(op.getProduct().getName());
			double taxValue = taxesService.taxValue(op.getNetUnitPrice(), tax);
			double taxRounded = taxesService.roundTaxValue(taxValue);
			op.setBasicTaxes(taxRounded);
		});
	}

	/**
	 * It takes the list of products applied and applies the import tax to each of
	 * them
	 * 
	 * @param orderedProducts
	 */
	void applyImportTax(List<OrderedProduct> orderedProducts) {
		orderedProducts.stream().forEach(op -> {
			double tax = taxesService.getImportSalesTax(op.getProduct());
			double taxValue = taxesService.taxValue(op.getNetUnitPrice(), tax);
			double taxRounded = taxesService.roundTaxValue(taxValue);
			op.setImportTaxes(taxRounded);
		});
	}

	/**
	 * It takes the list of products applied and for each of them it calculates the
	 * total amount of the corresponding fees
	 * 
	 * @param orderedProducts
	 */
	void calculateTaxes(List<OrderedProduct> orderedProducts) {
		orderedProducts.stream().forEach(op -> {
			double taxes = (op.getBasicTaxes() + op.getImportTaxes()) * op.getQuantity();
			op.setTaxes(taxes);
		});

	}

	/**
	 * It takes the list of products applied and for each of them it calculates the
	 * total unit cost of the product given by the sum of the taxes plus the net
	 * value.
	 * 
	 * @param orderedProducts
	 */
	void calculateGrossValue(List<OrderedProduct> orderedProducts) {
		orderedProducts.stream().forEach(op -> {
			double unitGrossPrice = op.getNetUnitPrice() + (op.getTaxes() / op.getQuantity());
			double grossPrice = Math.round((unitGrossPrice * op.getQuantity()) * 100.0) / 100.0;
			op.setGrossPrice(grossPrice);
		});

	}

	/**
	 * Turns the list of items in the shopping cart into the list of items in a
	 * purchase order
	 * 
	 * @param cartProducts
	 * @return
	 */
	static List<OrderedProduct> map(List<CartProduct> cartProducts) {
		return cartProducts.stream().map(PuchaseOrderServiceEasy::map).collect(Collectors.toList());
	}

	/**
	 * Turns the item in the shopping cart into an item in the purchase order
	 * 
	 * @param cartProduct
	 * @return
	 */
	static OrderedProduct map(CartProduct cartProduct) {
		OrderedProduct orderedProduct = new OrderedProduct();
		orderedProduct.setProduct(cartProduct.getProduct());
		orderedProduct.setQuantity(cartProduct.getQuantity());
		orderedProduct.setNetUnitPrice(cartProduct.getUnitPrice());
		return orderedProduct;
	}

	/**
	 * It takes the list of products applied and derives the total amount of the
	 * products contained therein
	 * 
	 * @param orderedProduct
	 * @return
	 */
	static double caclulateTotalAmount(List<OrderedProduct> orderedProduct) {
		return orderedProduct.stream().map(OrderedProduct::getGrossPrice)
				.mapToDouble(d -> Math.round(d * 100.0) / 100.0).sum();
	}

	/**
	 * It takes the list of products applied and derives the total amount of taxes
	 * of the products contained therein
	 * 
	 * @param orderedProduct
	 * @return
	 */
	static double caclulateTotalTaxes(List<OrderedProduct> orderedProduct) {
		return orderedProduct.stream().map(OrderedProduct::getTaxes).mapToDouble(d -> Math.round(d * 100.0) / 100.0)
				.sum();
	}

}
