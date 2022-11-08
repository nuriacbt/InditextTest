package es.inditex.brands.infrastructure.inbound.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import es.inditex.brands.domain.model.Price;
import es.inditex.brands.infrastructure.inbound.persistence.h2.entities.PriceEntity;

@Mapper
public interface IPriceEntityMapper {
	Price priceEntityToPrice(PriceEntity priceEntity);
	
	List<Price> priceEntityToPriceList(List<PriceEntity> priceEntity);
}
