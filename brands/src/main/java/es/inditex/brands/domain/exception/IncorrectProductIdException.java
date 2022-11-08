package es.inditex.brands.domain.exception;

import java.io.Serializable;

/**
 * @author Núria Curto
 *
 */
public class IncorrectProductIdException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public IncorrectProductIdException(String errorMessage) {
        super(errorMessage);
    }

}
