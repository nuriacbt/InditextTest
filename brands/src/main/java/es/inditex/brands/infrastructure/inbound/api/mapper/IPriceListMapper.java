package es.inditex.brands.infrastructure.inbound.api.mapper;

import es.inditex.brands.domain.model.PriceList;
import es.inditex.brands.infrastructure.inbound.api.dto.PriceListDTO;

/**
 * @author Núria Curto
 *
 */
public interface IPriceListMapper {
	
	PriceListDTO priceListToPriceListDto(PriceList priceList);
}
