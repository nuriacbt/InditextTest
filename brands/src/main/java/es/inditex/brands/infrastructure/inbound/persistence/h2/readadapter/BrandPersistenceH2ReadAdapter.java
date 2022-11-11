package es.inditex.brands.infrastructure.inbound.persistence.h2.readadapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.inditex.brands.domain.model.Brand;
import es.inditex.brands.domain.ports.secundary.IReadBrandPersistence;
import es.inditex.brands.infrastructure.inbound.persistence.h2.repository.IReadBrandRepository;
import es.inditex.brands.infrastructure.inbound.persistence.mapper.IBrandEntityMapper;
import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
public class BrandPersistenceH2ReadAdapter implements IReadBrandPersistence{
	
	@Autowired
	private IReadBrandRepository brandRepository;
	
	@Autowired
	private IBrandEntityMapper brandMapper;

	@Override
	public Optional<Brand> findById(Integer id) {
		return Optional.of(brandMapper.brandEntityToBrand(brandRepository.getById(id)));
	}

}
