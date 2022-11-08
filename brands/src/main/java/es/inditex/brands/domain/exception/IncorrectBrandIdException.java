package es.inditex.brands.domain.exception;

import java.io.Serializable;

/**
 * @author Núria Curto
 *
 */
public class IncorrectBrandIdException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public IncorrectBrandIdException(String errorMessage) {
        super(errorMessage);
    }

}
