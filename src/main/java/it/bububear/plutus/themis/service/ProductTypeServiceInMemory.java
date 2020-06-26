package it.bububear.plutus.themis.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import it.bububear.plutus.themis.model.ProductType;

@Service
public class ProductTypeServiceInMemory implements ProductTypeService {

	Map<String, ProductType> productTypes = new HashMap<>();

	public ProductTypeServiceInMemory() {
		productTypes.put("book", ProductType.BOOK);
		productTypes.put("music CD", ProductType.OTHER);
		productTypes.put("chocolate bar", ProductType.FOOD);
		productTypes.put("chocolate", ProductType.FOOD);
		productTypes.put("box of chocolates", ProductType.FOOD);
		productTypes.put("bottle of perfume", ProductType.OTHER);
		productTypes.put("packet of headache pills", ProductType.MEDICAL);
		productTypes.put("apple", ProductType.FOOD);
	}

	@Override
	public ProductType extractProductType(String productName) {
		return productTypes.get(productName);
	}

}
