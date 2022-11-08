package es.inditex.brands.infrastructure.inbound.persistence.mapper;

import org.mapstruct.Mapper;

import es.inditex.brands.domain.model.Product;
import es.inditex.brands.infrastructure.inbound.persistence.h2.entities.ProductEntity;

@Mapper
public interface IProductEntityMapper {
	Product productEntityToProduct(ProductEntity productEntity);
}
