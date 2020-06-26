package it.bububear.plutus.themis.service.io;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.bububear.plutus.themis.exception.ConvertInputException;
import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.model.Product;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InputDataServiceConsole implements InputDataService<String> {

	public static final String IMPORTED = "imported";
	public static final String AT = "at";
	int id = 0;

	@Override
	public CartProduct convertIntputInCartProduct(String inputString) throws ConvertInputException {

		int quantity;
		double unitPrice;
		String productName;
		boolean isImported;
		try {
			quantity = extractQuantity(inputString);
			unitPrice = extractPrice(inputString);
			productName = extractName(inputString);
			isImported = isImported(inputString);
		} catch (Exception e) {

			log.error("The following problem occurred when extrapolating the data: {}", e.getMessage());
			log.debug(e.getStackTrace().toString());
			throw new ConvertInputException("");
		}

		Product product = new Product(productName, isImported);
		return new CartProduct(id++, product, quantity, unitPrice);
	}

	int extractQuantity(String inputString) {
		return Integer.valueOf(inputString.split(" ")[0]);
	}

	boolean isImported(String inputString) {
		return inputString.contains(IMPORTED);
	}

	double extractPrice(String inputString) {
		int indexOfATValue = inputString.lastIndexOf(AT);
		return Double.valueOf(inputString.substring(indexOfATValue + 2));
	}

	String extractName(String inputString) {

		List<String> test = Arrays.stream(inputString.split(" ")).map(String::trim).collect(Collectors.toList());
		test.remove(0);
		if (inputString.contains(IMPORTED))
			test.remove(test.indexOf(IMPORTED));
		test.remove(test.lastIndexOf(AT));
		test.remove(test.size() - 1);

		return test.stream().collect(Collectors.joining(" "));
	}

}
