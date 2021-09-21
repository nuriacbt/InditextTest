package es.inditex.brands.infrastructure.inbound.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.inditex.brands.domain.model.Brand;
import es.inditex.brands.domain.model.Price;
import es.inditex.brands.domain.model.PriceList;
import es.inditex.brands.domain.model.Product;
import es.inditex.brands.infrastructure.inbound.api.dto.PriceDTO;

@ExtendWith(SpringExtension.class)
public class PriceMapperTest {
	
	private IPriceMapper priceMapper = Mappers.getMapper(IPriceMapper.class);

	@Test
	public void priceToPriceDtoTest() {
		Price price = this.getPriceEntity();
		PriceDTO dto = this.priceMapper.priceToPriceDto(price, price.getBrand().getBrandId(), price.getProduct().getProductId());
		assertEquals(price.getBrand().getBrandId(), dto.getBrandId());
		assertEquals(price.getProduct().getProductId(), dto.getProductId());
		assertEquals(price.getPriceList().getPriceListId(), dto.getPriceList().getPriceListId());
		assertEquals(price.getStartDate(), dto.getStartDate());
		assertEquals(price.getEndDate(), dto.getEndDate());
		assertEquals(price.getPrice(), dto.getPrice());
		assertEquals(price.getCurrency(), dto.getCurrency());
	}
	
	private Price getPriceEntity() {	
		
		Brand brand = Brand.builder()
				.brandId(1)
				.description("ZARA").build();
		
		Product product = Product.builder()
				.productId(35455)
				.description("Producto num 35455").build();
		
		PriceList priceList = PriceList.builder()
				.priceListId(1)
				.description("Tarifa num 1").build();
	
		LocalDateTime startDate = LocalDateTime.parse("2011-12-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime endDate = LocalDateTime.parse("2011-12-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		

		Price price = Price.builder()
				.priceId(1)
				.brand(brand)
				.product(product)
				.priceList(priceList)
				.startDate(startDate)
				.endDate(endDate)
				.priority(1)
				.price(35.50)
				.currency("EUR").build();	
		
		return price;
	}

}
