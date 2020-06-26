package it.bububear.plutus.themis.service;

import it.bububear.plutus.themis.model.ProductType;

/**
 * Management of product types
 * 
 * @author BubuBear
 *
 */
public interface ProductTypeService {

	ProductType extractProductType(String productName);

}
