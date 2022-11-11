package es.inditex.brands.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.inditex.brands.domain.exception.IncorrectProductIdException;
import es.inditex.brands.domain.model.Product;
import es.inditex.brands.domain.ports.primary.IProductService;
import es.inditex.brands.domain.ports.secundary.IReadProductPersistence;
import lombok.AllArgsConstructor;

/**
 * @author Núria Curto
 *
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private final IReadProductPersistence productPersistence;
	
	private static final String INCORRECT_PRODUCT_ID_MSG = "No se ha encontrado el producto con Id %s";

	@Override
	public void validateProductId(Integer productId) throws IncorrectProductIdException {		
		Optional<Product> productOptional = this.productPersistence.findById(productId);				
		if(!productOptional.isPresent()) {
			throw new IncorrectProductIdException(String.format(INCORRECT_PRODUCT_ID_MSG, productId));
		}
	}

}
