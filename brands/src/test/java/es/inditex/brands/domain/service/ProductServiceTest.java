package es.inditex.brands.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import es.inditex.brands.domain.model.Product;
import es.inditex.brands.domain.ports.secundary.IReadProductRepository;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectProductIdException;

/**
 * @author Núria Curto
 *
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class ProductServiceTest {
	
	@Mock
	IReadProductRepository productRepository;
	
	@InjectMocks
	ProductServiceImpl productService;
	
	@Test
	public void validateIncorrectProductId() {
		Optional<Product> optional = Optional.empty();
		when(productRepository.findById(55)).thenReturn(optional);
		assertThrows(IncorrectProductIdException.class, () -> {
			productService.validateProductId(55);
	    });
	}

}
