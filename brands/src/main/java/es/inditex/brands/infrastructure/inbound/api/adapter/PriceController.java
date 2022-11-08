package es.inditex.brands.infrastructure.inbound.api.adapter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.inditex.brands.domain.exception.IncorrectBrandIdException;
import es.inditex.brands.domain.exception.IncorrectProductIdException;
import es.inditex.brands.domain.exception.PriceNotFoundException;
import es.inditex.brands.domain.model.Price;
import es.inditex.brands.domain.ports.primary.IPriceService;
import es.inditex.brands.infrastructure.inbound.api.dto.PriceDTO;
import es.inditex.brands.infrastructure.inbound.api.mapper.IPriceMapper;
import lombok.AllArgsConstructor;

/**
 * @author Núria Curto
 *
 */
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PriceController {
	
	@Autowired
	IPriceService priceService;
	
	@Autowired
	IPriceMapper priceMapper;
	
	/**
	 * Método controlador del servicio getPrice() que busca el precio y la tarifa
	 * de un determinado producto de una cadena, en la fecha indicada
	 * 
	 * @param brandId
	 * @param productId
	 * @param aplicationDate
	 * @return PriceDTO
	 * @throws IncorrectBrandIdException
	 * @throws IncorrectProductIdException
	 * @throws PriceNotFoundException
	 */
	@GetMapping("/brands/{brandId}/products/{productId}/prices")
	public ResponseEntity<PriceDTO> getPrice(
			@PathVariable("brandId") Integer brandId, 
			@PathVariable("productId") Integer productId,
			@RequestParam("aplicationDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime aplicationDate) {
		
		Price price = priceService.getPrice(brandId, productId, aplicationDate);
		PriceDTO priceDto = priceMapper.priceToPriceDto(price, price.getBrand().getBrandId(), price.getProduct().getProductId());
		return new ResponseEntity<>(priceDto, HttpStatus.OK);
	}
	
}
