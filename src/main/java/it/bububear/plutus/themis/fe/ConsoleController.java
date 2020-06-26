package it.bububear.plutus.themis.fe;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.bububear.plutus.themis.exception.ConvertInputException;
import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.model.PurchaseOrder;
import it.bububear.plutus.themis.model.io.InputCommands;
import it.bububear.plutus.themis.service.CartService;
import it.bububear.plutus.themis.service.PuchaseOrderService;
import it.bububear.plutus.themis.service.io.InputDataService;
import it.bububear.plutus.themis.service.io.OutputDataService;

/**
 * This is the component of the application that is concerned with user
 * interaction
 * 
 * @author BubuBear
 *
 */
@Component
public class ConsoleController {

	@Autowired
	InputDataService<String> inputDataService;
	@Autowired
	CartService cartService;
	@Autowired
	PuchaseOrderService<List<CartProduct>> puchaseOrderService;
	@Autowired
	OutputDataService<String> outputDataService;

	@Value("${console.message.add-item}")
	String addItemToCartMessage;
	@Value("${console.message.place-order}")
	String placeOrderMessage;

	@Value("${console.message.wrong-input}")
	String wrongInputMessage;

	/*
	 * Method that takes care of managing the application execution flow
	 */
	public void startAppConsole() {

		System.out.println(addItemToCartMessage);
		System.out.println(placeOrderMessage);
		System.out.println("");
		System.out.println("");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String inputData = scanner.nextLine();
			if (!inputData.trim().toLowerCase().contains(InputCommands.PLACE_ORDER_OPERATION)) {
				try {
					CartProduct cartProduct = inputDataService.convertIntputInCartProduct(inputData);
					cartService.addProductToCart(cartProduct);
				} catch (ConvertInputException e) {
					System.out.println(wrongInputMessage);
					System.out.println(addItemToCartMessage);
				}

			} else {
				break;
			}

		}
		scanner.close();
		System.out.println("");
		PurchaseOrder purchaseOrder = puchaseOrderService.placeOrder(cartService.getProducts());
		System.out.println(outputDataService.convertPurchaseOrderIntoOutput(purchaseOrder));

	}

}
