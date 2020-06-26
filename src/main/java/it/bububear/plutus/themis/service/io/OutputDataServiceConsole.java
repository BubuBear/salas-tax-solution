package it.bububear.plutus.themis.service.io;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.bububear.plutus.themis.model.OrderedProduct;
import it.bububear.plutus.themis.model.PurchaseOrder;

@Service
public class OutputDataServiceConsole implements OutputDataService<String> {

	private static final String LINE_SEPARATOR = "line.separator";

	@Override
	public String convertOrderedItemIntoOutput(OrderedProduct orderedItem) {

		String format = orderedItem.getProduct().isImported() ? "%s imported %s: %.2f" : "%s %s: %.2f";
		return String.format(Locale.ENGLISH, format, orderedItem.getQuantity(), orderedItem.getProduct().getName(),
				orderedItem.getGrossPrice());
	}

	@Override
	public String convertPurchaseOrderIntoOutput(PurchaseOrder purchaseOrder) {
		List<String> products = purchaseOrder.getOrderedProducts().stream().map(op -> convertOrderedItemIntoOutput(op))
				.collect(Collectors.toList());

		StringBuilder output = new StringBuilder();
		output.append(purchaseOrder.getOrderName());
		output.append(System.getProperty(LINE_SEPARATOR));
		products.forEach(s -> {
			output.append(s);
			output.append(System.getProperty(LINE_SEPARATOR));
		});
		output.append(String.format(Locale.ENGLISH, "Sales Taxes: %.2f", purchaseOrder.getTotalSalesTax()));
		output.append(System.getProperty(LINE_SEPARATOR));
		output.append(String.format(Locale.ENGLISH, "Total: %.2f", purchaseOrder.getTotalAmount()));
		output.append(System.getProperty(LINE_SEPARATOR));
		return output.toString();
	}

}
