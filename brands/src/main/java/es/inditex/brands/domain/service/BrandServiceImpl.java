package es.inditex.brands.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.inditex.brands.domain.exception.IncorrectBrandIdException;
import es.inditex.brands.domain.model.Brand;
import es.inditex.brands.domain.ports.primary.IBrandService;
import es.inditex.brands.domain.ports.secundary.IReadBrandPersistence;
import lombok.AllArgsConstructor;

/**
 * @author Núria Curto
 *
 */
@Service
@AllArgsConstructor
public class BrandServiceImpl implements IBrandService {
	
	@Autowired
	private final IReadBrandPersistence brandPersistence;

	private static final String INCORRECT_BRAND_ID_MSG = "No se ha encontrado la cadena con Id %s";

	@Override
	public void validateBrandId(Integer brandId) throws IncorrectBrandIdException {		
		Optional<Brand> brandOptional = this.brandPersistence.findById(brandId);				
		if(!brandOptional.isPresent()) {
			throw new IncorrectBrandIdException(String.format(INCORRECT_BRAND_ID_MSG, brandId));
		}
	}

}
