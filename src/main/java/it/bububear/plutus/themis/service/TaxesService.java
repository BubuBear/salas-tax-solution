package it.bububear.plutus.themis.service;

public interface TaxesService<I, P> {
	double getBasicSalesTax(I i);

	double getImportSalesTax(P p);

	/**
	 * Rounding of fees following the rule of (np/100 rounded up to the nearest
	 * 0.05)
	 * 
	 * @param taxValue
	 * @return
	 */
	double roundTaxValue(double taxValue);

	/**
	 * Calculates the value of the tax given the value to apply it and its
	 * percentage
	 * 
	 * @param value
	 * @param percentage
	 * @return
	 */
	double taxValue(double value, double percentage);
}
