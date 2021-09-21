package es.inditex.brands.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import es.inditex.brands.domain.ports.primary.IBrandService;
import es.inditex.brands.domain.ports.primary.IProductService;
import es.inditex.brands.domain.ports.secundary.IReadPriceRepository;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectBrandIdException;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectProductIdException;
import es.inditex.brands.infrastructure.inbound.api.exception.PriceNotFoundException;

/**
 * @author Núria Curto
 *
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class PriceServiceTest {
	
	@Mock
	IBrandService brandService;
	
	@Mock
	IProductService productService;
	
	@Mock
	IReadPriceRepository priceRepository;
	
	@InjectMocks
	PriceServiceImpl priceService;
	
	@Test
	public void getPriceErrorTest() throws IncorrectBrandIdException, IncorrectProductIdException, PriceNotFoundException {	
		doNothing().when(brandService).validateBrandId(1);
		doNothing().when(productService).validateProductId(1);
		when(priceRepository.findPricesByFilters(1, 1, LocalDateTime.now())).thenReturn(null);	
		assertThrows(PriceNotFoundException.class, () -> {
			priceService.getPrice(1, 1, LocalDateTime.now());
	    });
	}
	
}
