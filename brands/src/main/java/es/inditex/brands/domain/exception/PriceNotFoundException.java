package es.inditex.brands.domain.exception;

import java.io.Serializable;

/**
 * @author Núria Curto
 *
 */
public class PriceNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public PriceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
