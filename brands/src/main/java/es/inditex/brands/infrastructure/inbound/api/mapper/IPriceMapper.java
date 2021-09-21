package es.inditex.brands.infrastructure.inbound.api.mapper;

import org.mapstruct.Mapper;

import es.inditex.brands.domain.model.Price;
import es.inditex.brands.infrastructure.inbound.api.dto.PriceDTO;

/**
 * @author Núria Curto
 *
 */
@Mapper
public interface IPriceMapper {

	PriceDTO priceToPriceDto(Price price, Integer brandId, Integer productId);
}
