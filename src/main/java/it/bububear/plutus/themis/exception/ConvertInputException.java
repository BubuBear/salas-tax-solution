package it.bububear.plutus.themis.exception;

/**
 * Reporting errors during input transformation by the user
 * 
 * @author BubuBear
 *
 */
public class ConvertInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConvertInputException(String message) {
		super(message);
	}

}
