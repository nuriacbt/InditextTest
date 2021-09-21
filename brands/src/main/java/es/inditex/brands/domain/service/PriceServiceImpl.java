package es.inditex.brands.domain.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.inditex.brands.domain.model.Price;
import es.inditex.brands.domain.ports.primary.IBrandService;
import es.inditex.brands.domain.ports.primary.IPriceService;
import es.inditex.brands.domain.ports.primary.IProductService;
import es.inditex.brands.domain.ports.secundary.IReadPriceRepository;
import es.inditex.brands.infrastructure.inbound.api.dto.PriceDTO;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectBrandIdException;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectProductIdException;
import es.inditex.brands.infrastructure.inbound.api.exception.PriceNotFoundException;
import es.inditex.brands.infrastructure.inbound.api.mapper.IPriceMapper;

/**
 * @author Núria Curto
 *
 */
@Service
public class PriceServiceImpl implements IPriceService{
	
	@Autowired
	IBrandService brandService;
	
	@Autowired
	IProductService productService;
	
	@Autowired
	IReadPriceRepository priceRepository;
	
	@Autowired
	IPriceMapper priceMapper;
	
	private static final String PRICE_NOT_FOUND_MSG = "No se ha el precio final para el producto con Id %s, en la fecha indicada";
	
	/**
	 * Método servicio que busca el precio y la tarifa de un determinado producto de una cadena, en la fecha indicada.
	 * En el caso en que haya varias tarifas aplicadas, para la misma fecha, se busca la que tiene más prioridad
	 */
	@Override
	public PriceDTO getPrice(Integer brandId, Integer productId, LocalDateTime aplicationDate) throws IncorrectBrandIdException, IncorrectProductIdException, PriceNotFoundException {	
		this.validateFields(brandId, productId);
		List<Price> priceList = this.priceRepository.findPricesByFilters(brandId, productId, aplicationDate);
		
		if(priceList == null || priceList.isEmpty()) {
			throw new PriceNotFoundException(String.format(PRICE_NOT_FOUND_MSG, productId));
		}
		return priceList.size() == 1 ? 
				this.priceMapper.priceToPriceDto(priceList.get(0), brandId, productId) 
				: this.getPriceMaxPriority(priceList);

	}
	
	/**
	 * Método que valida si las Ids de cadena y de producto existen.
	 * 
	 * @param brandId
	 * @param productId
	 * @throws IncorrectBrandIdException
	 * @throws IncorrectProductIdException
	 */
	private void validateFields(Integer brandId, Integer productId) throws IncorrectBrandIdException, IncorrectProductIdException {
		this.brandService.validateBrandId(brandId);
		this.productService.validateProductId(productId);
	}
	
	
	/**
	 * Método que busca la tarifa que tiene más prioridad
	 * 
	 * @param priceList
	 * @return PriceDTO
	 */
	private PriceDTO getPriceMaxPriority(List<Price> priceList) {		
		Price price = Collections.max(priceList, new Comparator<Price>() {
			public int compare(Price price1, Price price2) {
			    return Integer.compare(price1.getPriority(), price2.getPriority());
			}
		});	
		return this.priceMapper.priceToPriceDto(price, price.getBrand().getBrandId(), price.getProduct().getProductId());
	}
}
