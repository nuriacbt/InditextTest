package es.inditex.brands.domain.ports.primary;

import es.inditex.brands.domain.exception.IncorrectBrandIdException;

/**
 * @author Núria Curto
 *
 */
public interface IBrandService {
	
	/**
	 * Método que valida si una id de cadena existe.
	 * 
	 * @param brandId
	 * @throws IncorrectBrandIdException
	 */
	void validateBrandId(Integer brandId) throws IncorrectBrandIdException;

}
