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

import es.inditex.brands.domain.exception.IncorrectBrandIdException;
import es.inditex.brands.domain.model.Brand;
import es.inditex.brands.domain.ports.secundary.IReadBrandPersistence;

/**
 * @author Núria Curto
 *
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class BrandServiceTest {
	
	@Mock
	IReadBrandPersistence brandPersistence;
	
	@InjectMocks
	BrandServiceImpl brandService;
	
	@Test
	public void validateIncorrectBrandId() {
		Optional<Brand> optional = Optional.empty();
		when(brandPersistence.findById(55)).thenReturn(optional);
		assertThrows(IncorrectBrandIdException.class, () -> {
			brandService.validateBrandId(55);
	    });
	}

}
