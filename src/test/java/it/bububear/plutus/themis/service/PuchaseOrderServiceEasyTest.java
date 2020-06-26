package it.bububear.plutus.themis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.model.OrderedProduct;
import it.bububear.plutus.themis.test.utils.TestCaseUtils;

class PuchaseOrderServiceEasyTest {

	PuchaseOrderServiceEasy puchaseOrderServiceEasy;
	List<OrderedProduct> orderedProducts;

	@BeforeEach
	void before() {
		TaxesServiceInMemory taxesService = new TaxesServiceInMemory();
		ProductTypeServiceInMemory productTypeService = new ProductTypeServiceInMemory();
		taxesService.setProductTypeService(productTypeService);
		puchaseOrderServiceEasy = new PuchaseOrderServiceEasy();
		puchaseOrderServiceEasy.setTaxesService(taxesService);
		orderedProducts = new LinkedList<>();
	}

	@Test
	void test001ApplyBasicTaxOtherType() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "music CD", false, 14.99);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.applyBasicTax(orderedProducts);
		assertEquals(1.5, orderedProducts.iterator().next().getBasicTaxes());
	}

	@Test
	void test002ApplyBasicTaxFoodType() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "chocolate bar", false, 0.85);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.applyBasicTax(orderedProducts);
		assertEquals(0, orderedProducts.iterator().next().getBasicTaxes());
	}

	@Test
	void test003ApplyBasicTaxBooksType() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "book", false, 12.49);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.applyBasicTax(orderedProducts);
		assertEquals(0, orderedProducts.iterator().next().getBasicTaxes());
	}

	@Test
	void test004ApplyBasicTaxMedicineType() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "book", false, 55.0);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.applyBasicTax(orderedProducts);
		assertEquals(0, orderedProducts.iterator().next().getBasicTaxes());
	}

	@Test
	void test005ApplyImportTaxImportedProduct() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "box of chocolates", true, 10.00);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.applyImportTax(orderedProducts);
		assertEquals(0.50, orderedProducts.iterator().next().getImportTaxes());
	}

	@Test
	void test006ApplyImportTaxNotImportedProduct() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "box of chocolates", false, 10.00);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.applyImportTax(orderedProducts);
		assertEquals(0.0, orderedProducts.iterator().next().getImportTaxes());
	}

	@Test
	void test007ApplyImportTaxAndApplyBasicTax() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "bottle of perfume", true, 47.50);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.applyBasicTax(orderedProducts);
		puchaseOrderServiceEasy.applyImportTax(orderedProducts);
		assertEquals(7.15, orderedProduct.getImportTaxes() + orderedProduct.getBasicTaxes());
	}

	@Test
	void test008CalculateTaxes() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "bottle of perfume", true, 47.50);
		orderedProducts.add(orderedProduct);
		orderedProduct.setBasicTaxes(5);
		orderedProduct.setImportTaxes(15);
		puchaseOrderServiceEasy.calculateTaxes(orderedProducts);
		assertEquals(20, orderedProduct.getTaxes());
	}

	@Test
	void test009CalculateTaxesNoTax() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "bottle of perfume", true, 47.50);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.calculateTaxes(orderedProducts);
		assertEquals(0.0, orderedProduct.getTaxes());
	}

	@Test
	void test010CalculateGrossValue() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "bottle of perfume", true, 11.25);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.calculateGrossValue(orderedProducts);
		assertEquals(11.25, orderedProduct.getGrossPrice());
	}

	@Test
	void test011CalculateGrossValueMultipleQuantity() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(3, "bottle of perfume", true, 10.10);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.calculateGrossValue(orderedProducts);
		assertEquals(30.30, orderedProduct.getGrossPrice());
	}

	@Test
	void test012CalculateGrossValueFreeProduct() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "bottle of perfume", true, 0.0);
		orderedProducts.add(orderedProduct);
		puchaseOrderServiceEasy.calculateGrossValue(orderedProducts);
		assertEquals(0, orderedProduct.getGrossPrice());
	}

	@Test
	void test013CaclulateTotalAmount() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(3, "chocolate bar", false, 5.0);
		orderedProduct.setGrossPrice(15);
		orderedProducts.add(orderedProduct);
		assertEquals(15, PuchaseOrderServiceEasy.caclulateTotalAmount(orderedProducts));
	}

	@Test
	void test014CaclulateTotalAmountMultipleProducts() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(3, "chocolate bar", false, 5.0);
		orderedProduct.setGrossPrice(15);
		orderedProducts.add(orderedProduct);
		OrderedProduct orderedProductAnother = TestCaseUtils.createOrderedProduct(3, "packet of headache pills", false,
				5.0);
		orderedProductAnother.setGrossPrice(2.00);
		orderedProducts.add(orderedProductAnother);
		assertEquals(17, PuchaseOrderServiceEasy.caclulateTotalAmount(orderedProducts));
	}

	@Test
	void test015CaclulateTotalTaxes() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(3, "chocolate bar", false, 5.0);
		orderedProduct.setTaxes(15.5);
		orderedProducts.add(orderedProduct);
		assertEquals(15.5, PuchaseOrderServiceEasy.caclulateTotalTaxes(orderedProducts));
	}

	@Test
	void test016CaclulateTotalTaxesMultipleProducts() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(3, "chocolate bar", false, 5.0);
		orderedProduct.setTaxes(15.5);
		orderedProducts.add(orderedProduct);
		OrderedProduct orderedProductAnother = TestCaseUtils.createOrderedProduct(3, "packet of headache pills", false,
				5.0);
		orderedProductAnother.setTaxes(2.05);
		orderedProducts.add(orderedProductAnother);
		assertEquals(17.55, PuchaseOrderServiceEasy.caclulateTotalTaxes(orderedProducts));
	}

	@Test
	void test017MapListOfCartProductListSize() {
		List<CartProduct> cartProducts = new LinkedList<>();
		CartProduct cartProduct = TestCaseUtils.createCartProduct(1);
		cartProducts.add(cartProduct);
		CartProduct cartProductSecond = TestCaseUtils.createCartProduct(2);
		cartProducts.add(cartProductSecond);
		List<OrderedProduct> orderedProducts = PuchaseOrderServiceEasy.map(cartProducts);
		assertEquals(2, orderedProducts.size());
	}

	@Test
	void test018MapListOfCartProductDataQuality() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(1, "banana", false, 3.9);
		List<CartProduct> cartProducts = new LinkedList<>();
		CartProduct cartProduct = TestCaseUtils.createCartProduct(1, 1, "banana", 3.9, false);
		cartProducts.add(cartProduct);
		List<OrderedProduct> orderedProducts = PuchaseOrderServiceEasy.map(cartProducts);
		assertEquals(orderedProduct, orderedProducts.iterator().next());
	}

	@Test
	void test019MapCartProduct() {
		OrderedProduct orderedProduct = TestCaseUtils.createOrderedProduct(3, "apple", true, 5.0);
		CartProduct cartProduct = TestCaseUtils.createCartProduct(1, 3, "apple", 5.0, true);
		OrderedProduct orderedProductFromMap = PuchaseOrderServiceEasy.map(cartProduct);
		assertEquals(orderedProduct, orderedProductFromMap);
	}

}
