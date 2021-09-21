package es.inditex.brands.domain.ports.primary;

import java.time.LocalDateTime;

import es.inditex.brands.infrastructure.inbound.api.dto.PriceDTO;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectBrandIdException;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectProductIdException;
import es.inditex.brands.infrastructure.inbound.api.exception.PriceNotFoundException;


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
	PriceDTO getPrice(Integer brandId, Integer productId, LocalDateTime aplicationDate) throws IncorrectBrandIdException, IncorrectProductIdException, PriceNotFoundException;

}
