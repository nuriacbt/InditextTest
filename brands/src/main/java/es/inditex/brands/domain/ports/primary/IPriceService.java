package es.inditex.brands.domain.ports.primary;

import java.time.LocalDateTime;

import es.inditex.brands.domain.exception.IncorrectBrandIdException;
import es.inditex.brands.domain.exception.IncorrectProductIdException;
import es.inditex.brands.domain.exception.PriceNotFoundException;
import es.inditex.brands.domain.model.Price;


/**
 * @author Núria Curto
 *
 */
public interface IPriceService {
	
	/**
	 * Método servicio que busca el precio y la tarifa de un determinado producto de una cadena, en la fecha indicada
	 * 
	 * @param brandId
	 * @param productId
	 * @param aplicationDate
	 * @return PriceDTO
	 * @throws IncorrectBrandIdException
	 * @throws IncorrectProductIdException
	 * @throws PriceNotFoundException
	 */
	Price getPrice(Integer brandId, Integer productId, LocalDateTime aplicationDate) throws PriceNotFoundException;

}
