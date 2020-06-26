package it.bububear.plutus.themis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.bububear.plutus.themis.model.Product;

class TaxesServiceInMemoryTest {

	TaxesServiceInMemory taxesServiceInMemory = new TaxesServiceInMemory();

	@BeforeEach
	void before() {
		ProductTypeServiceInMemory productTypeServiceInMemory = new ProductTypeServiceInMemory();
		taxesServiceInMemory.productTypeService = productTypeServiceInMemory;
	}

	@Test
	void test001GetBasicSalesTaxZero() {
		double taxValue = taxesServiceInMemory.getBasicSalesTax("box of chocolates");
		assertEquals(0, taxValue);
	}

	@Test
	void test002GetBasicSalesTaxFillfiled() {
		double taxValue = taxesServiceInMemory.getBasicSalesTax("bottle of perfume");
		assertEquals(10.0, taxValue);
	}

	@Test
	void test003RoundTaxValueUpperFive() {
		double taxValue = taxesServiceInMemory.roundTaxValue(0.56);
		assertEquals(0.60, taxValue);
	}

	@Test
	void test003RoundTaxValueLowerFive() {
		double taxValue = taxesServiceInMemory.roundTaxValue(0.54);
		assertEquals(0.55, taxValue);
	}

	@Test
	void test004RoundTaxValueLowerFive() {
		double taxValue = taxesServiceInMemory.roundTaxValue(0.51);
		assertEquals(0.55, taxValue);
	}

	@Test
	void test005RoundTaxValueLowerZero() {
		double taxValue = taxesServiceInMemory.roundTaxValue(0.50);
		assertEquals(0.50, taxValue);
	}

	@Test
	void test006TaxValue() {
		double taxValue = taxesServiceInMemory.taxValue(100, 20);
		assertEquals(20, taxValue);
	}

	@Test
	void testGetImportSalesTax() {
		double taxValue = taxesServiceInMemory.getImportSalesTax(new Product("test", true));
		assertEquals(5, taxValue);
	}

}
