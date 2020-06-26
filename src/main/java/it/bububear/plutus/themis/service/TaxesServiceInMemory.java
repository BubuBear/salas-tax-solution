package it.bububear.plutus.themis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bububear.plutus.themis.model.Product;
import it.bububear.plutus.themis.model.ProductType;
import lombok.Getter;
import lombok.Setter;

@Service
public class TaxesServiceInMemory implements TaxesService<String, Product> {

	@Getter
	@Setter
	@Autowired
	ProductTypeService productTypeService;
	double basicTax = 10.0;
	double importTax = 5.0;

	@Override
	public double getBasicSalesTax(String productName) {
		ProductType productType = productTypeService.extractProductType(productName);
		return productType.compareTo(ProductType.OTHER) == 0 ? basicTax : 0;
	}

	@Override
	public double roundTaxValue(double taxValue) {
		return Math.round((Math.ceil(taxValue / 0.05) * 0.05) * 100.0) / 100.0;
	}

	@Override
	public double taxValue(double value, double percentage) {
		return Math.round(value * percentage) / 100.0;
	}

	@Override
	public double getImportSalesTax(Product product) {

		return product.isImported() ? importTax : 0.0;
	}

}
