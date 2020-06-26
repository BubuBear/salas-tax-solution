package it.bububear.plutus.themis.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Entry point class for testing with Cucumber
 * 
 * @author BubuBear
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber" }, features = "src/test/resources/features")
public class ACucumberTests {

}
