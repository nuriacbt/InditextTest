package es.inditex.brands.infrastructure.inbound.api.exception;

import java.io.Serializable;

/**
 * @author Núria Curto
 *
 */
public class IncorrectProductIdException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public IncorrectProductIdException(String errorMessage) {
        super(errorMessage);
    }

}
