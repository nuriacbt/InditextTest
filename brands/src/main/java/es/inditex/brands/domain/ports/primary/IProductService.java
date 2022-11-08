package es.inditex.brands.domain.ports.primary;

import es.inditex.brands.domain.exception.IncorrectProductIdException;

/**
 * @author Núria Curto
 *
 */
public interface IProductService {
	
	/**
	 * Método que valida si una id de producto existe.
	 * 
	 * @param productId
	 * @throws IncorrectProductIdException
	 */
	void validateProductId(Integer productId) throws IncorrectProductIdException;

}
