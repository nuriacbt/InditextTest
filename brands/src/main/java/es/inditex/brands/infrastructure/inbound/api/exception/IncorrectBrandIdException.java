package es.inditex.brands.infrastructure.inbound.api.exception;

import java.io.Serializable;

/**
 * @author Núria Curto
 *
 */
public class IncorrectBrandIdException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public IncorrectBrandIdException(String errorMessage) {
        super(errorMessage);
    }

}
