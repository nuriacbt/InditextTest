package es.inditex.brands.infrastructure.inbound.persistence.h2.readadapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.inditex.brands.domain.model.Product;
import es.inditex.brands.domain.ports.secundary.IReadProductPersistence;
import es.inditex.brands.infrastructure.inbound.persistence.h2.repository.IReadProductRepository;
import es.inditex.brands.infrastructure.inbound.persistence.mapper.IProductEntityMapper;
import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
public class ProductPersistenceH2ReadAdapter implements IReadProductPersistence{
	
	@Autowired
	private IReadProductRepository productRepository;
	
	@Autowired
	private IProductEntityMapper productMapper;
	
	@Override
	public Optional<Product> findById(Integer id) {
		return Optional.of(productMapper.productEntityToProduct(productRepository.getById(id)));
	}

}
