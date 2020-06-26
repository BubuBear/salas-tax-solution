package it.bububear.plutus.themis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that defines model a product
 * 
 * @author BubuBear
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private String name;
	private boolean isImported;
}
