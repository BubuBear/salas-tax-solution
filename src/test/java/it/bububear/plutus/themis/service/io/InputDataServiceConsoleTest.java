package it.bububear.plutus.themis.service.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InputDataServiceConsoleTest {

	InputDataServiceConsole inputDataServiceConsole = new InputDataServiceConsole();

	@Test
	void test001ExtractQuantity() {
		String inputString = "2 book at 12.49";
		int extractedQuantity = inputDataServiceConsole.extractQuantity(inputString);
		assertEquals(2, extractedQuantity);

	}

	@Test
	void test002IsImportedTrue() {
		String inputString = "1 imported box of chocolates at 10.00";
		boolean extractedImported = inputDataServiceConsole.isImported(inputString);
		assertEquals(true, extractedImported);
	}

	@Test
	void test003IsImportedFalse() {
		String inputString = "2 book at 12.49";
		boolean extractedImported = inputDataServiceConsole.isImported(inputString);
		assertEquals(false, extractedImported);
	}

	@Test
	void test004ExtractName() {
		String inputString = "1 imported box of chocolates at 10.00";
		String extractedImported = inputDataServiceConsole.extractName(inputString);
		assertEquals("box of chocolates", extractedImported);
	}

	@Test
	void test005ExtractPrice() {
		String inputString = "2 book at 12.49";
		double extractedPrice = inputDataServiceConsole.extractPrice(inputString);
		assertEquals(12.49, extractedPrice);
	}

}
