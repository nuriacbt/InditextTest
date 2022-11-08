package es.inditex.brands.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.inditex.brands.domain.exception.IncorrectBrandIdException;
import es.inditex.brands.domain.model.Brand;
import es.inditex.brands.domain.ports.primary.IBrandService;
import es.inditex.brands.domain.ports.secundary.IReadBrandPersistence;

@Service
public class BrandServiceImpl implements IBrandService {

	private final IReadBrandPersistence brandPersistence;
	
	public BrandServiceImpl(IReadBrandPersistence brandPersistence) {
		this.brandPersistence = brandPersistence;
	}
	
	private static final String INCORRECT_BRAND_ID_MSG = "No se ha encontrado la cadena con Id %s";

	@Override
	public void validateBrandId(Integer brandId) throws IncorrectBrandIdException {		
		Optional<Brand> brandOptional = this.brandPersistence.findById(brandId);				
		if(!brandOptional.isPresent()) {
			throw new IncorrectBrandIdException(String.format(INCORRECT_BRAND_ID_MSG, brandId));
		}
	}

}
