package it.bububear.plutus.themis;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import it.bububear.plutus.themis.model.CartProduct;
import it.bububear.plutus.themis.model.Product;
import it.bububear.plutus.themis.service.CartService;
import it.bububear.plutus.themis.service.CartServiceInMemory;
import it.bububear.plutus.themis.service.ProductTypeService;
import it.bububear.plutus.themis.service.ProductTypeServiceInMemory;
import it.bububear.plutus.themis.service.PuchaseOrderService;
import it.bububear.plutus.themis.service.PuchaseOrderServiceEasy;
import it.bububear.plutus.themis.service.TaxesService;
import it.bububear.plutus.themis.service.TaxesServiceInMemory;
import it.bububear.plutus.themis.service.io.InputDataService;
import it.bububear.plutus.themis.service.io.InputDataServiceConsole;
import it.bububear.plutus.themis.service.io.OutputDataService;
import it.bububear.plutus.themis.service.io.OutputDataServiceConsole;

@SpringBootTest(classes = { InputDataServiceConsole.class, ProductTypeServiceInMemory.class, TaxesServiceInMemory.class,
		CartServiceInMemory.class, PuchaseOrderServiceEasy.class, OutputDataServiceConsole.class })
class ThemisApplicationTests {

	@Autowired
	Environment environment;
	@Autowired
	InputDataService<String> inputDataService;
	@Autowired
	ProductTypeService productTypeService;
	@Autowired
	TaxesService<String, Product> taxesService;
	@Autowired
	CartService cartService;
	@Autowired
	PuchaseOrderService<List<CartProduct>> puchaseOrderService;
	@Autowired
	OutputDataService<String> outputDataService;

	@Test
	void contextLoads() {
		String applicationName = environment.getProperty("spring.application.name");
		assertEquals("THEMIS", applicationName);
	}

	@Test
	void contextLoadsInputDataService() {
		assertNotNull(inputDataService);
	}

	@Test
	void contextLoadsProductTypeService() {
		assertNotNull(productTypeService);
	}

	@Test
	void contextLoadsTaxesService() {
		assertNotNull(taxesService);
	}

	@Test
	void contextLoadsCartService() {
		assertNotNull(cartService);
	}

	@Test
	void contextLoadsPuchaseOrderService() {
		assertNotNull(puchaseOrderService);
	}

	@Test
	void contextLoadsOutputDataService() {
		assertNotNull(outputDataService);
	}

}
