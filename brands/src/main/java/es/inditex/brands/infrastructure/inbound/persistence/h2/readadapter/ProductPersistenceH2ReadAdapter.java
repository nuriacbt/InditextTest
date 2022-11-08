package es.inditex.brands.infrastructure.inbound.persistence.h2.readadapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.inditex.brands.domain.model.Product;
import es.inditex.brands.domain.ports.secundary.IReadProductPersistence;
import es.inditex.brands.infrastructure.inbound.persistence.h2.repository.IReadProductRepository;
import es.inditex.brands.infrastructure.inbound.persistence.mapper.IProductEntityMapper;

@Transactional
public class ProductPersistenceH2ReadAdapter implements IReadProductPersistence{

	private IReadProductRepository productRepository;
	
	private IProductEntityMapper productMapper;
			
	public ProductPersistenceH2ReadAdapter(IReadProductRepository productRepository,
			IProductEntityMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return Optional.of(productMapper.productEntityToProduct(productRepository.getById(id)));
	}

}
