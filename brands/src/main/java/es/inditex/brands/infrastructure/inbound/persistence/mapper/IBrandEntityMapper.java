package es.inditex.brands.infrastructure.inbound.persistence.mapper;

import org.mapstruct.Mapper;

import es.inditex.brands.domain.model.Brand;
import es.inditex.brands.infrastructure.inbound.persistence.h2.entities.BrandEntity;

@Mapper
public interface IBrandEntityMapper {
	Brand brandEntityToBrand(BrandEntity brandEntity);
}
