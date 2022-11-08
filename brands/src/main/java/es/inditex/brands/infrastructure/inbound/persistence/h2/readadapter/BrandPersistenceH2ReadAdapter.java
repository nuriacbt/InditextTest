package es.inditex.brands.infrastructure.inbound.persistence.h2.readadapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.inditex.brands.domain.model.Brand;
import es.inditex.brands.domain.ports.secundary.IReadBrandPersistence;
import es.inditex.brands.infrastructure.inbound.persistence.h2.repository.IReadBrandRepository;
import es.inditex.brands.infrastructure.inbound.persistence.mapper.IBrandEntityMapper;

@Transactional
public class BrandPersistenceH2ReadAdapter implements IReadBrandPersistence{

	private IReadBrandRepository brandRepository;

	private IBrandEntityMapper brandMapper;
	
	public BrandPersistenceH2ReadAdapter(IReadBrandRepository brandRepository, IBrandEntityMapper brandMapper) {
		this.brandRepository = brandRepository;
		this.brandMapper = brandMapper;
	}

	@Override
	public Optional<Brand> findById(Integer id) {
		return Optional.of(brandMapper.brandEntityToBrand(brandRepository.getById(id)));
	}

}
