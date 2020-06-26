package it.bububear.plutus.themis.service.io;

import it.bububear.plutus.themis.exception.ConvertInputException;
import it.bububear.plutus.themis.model.CartProduct;

public interface InputDataService<I> {

	CartProduct convertIntputInCartProduct(I i) throws ConvertInputException;

}
