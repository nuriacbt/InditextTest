package es.inditex.brands.infrastructure.inbound.api.exception;

import java.io.Serializable;

/**
 * @author Núria Curto
 *
 */
public class PriceNotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public PriceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
