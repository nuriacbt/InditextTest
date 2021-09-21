package es.inditex.brands.infrastructure.inbound.api.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.inditex.brands.infrastructure.inbound.api.dto.PriceDTO;

/**
 * @author Núria Curto
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerIntegrationTest {
	
	@LocalServerPort
    private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	
	/**
	 * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA). 
	 * Se espera un precio final 35.50 con la tarifa con ID 1. 
	 * 
	 */
	@Test
	public void getPriceIntegrationTestOne() {
		String url = this.createURLWithParams(1, 35455, "2020-06-14T10:00:00.000-00:00");
		PriceDTO response = this.restTemplate.getForObject(url, PriceDTO.class);
		Double expectedPrice = 35.50;
		Integer priceListId = 1;
		assertEquals(response.getPrice(), expectedPrice);
		assertEquals(response.getPriceList().getPriceListId(), priceListId);
	}
	
	/**
	 * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA).
	 * Se espera un precio final 25.45 con la tarifa con ID 2. 
	 * 
	 */
	@Test
	public void getPriceIntegrationTestTwo() {
		String url = this.createURLWithParams(1, 35455, "2020-06-14T16:00:00.000-00:00");
		PriceDTO response = this.restTemplate.getForObject(url, PriceDTO.class);
		Double expectedPrice = 25.45;
		Integer priceListId = 2;
		assertEquals(response.getPrice(), expectedPrice);
		assertEquals(response.getPriceList().getPriceListId(), priceListId);
	}
	
	/**
	 * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA).
	 * Se espera un precio final 35.50 con la tarifa con ID 1. 
	 * 
	 */
	@Test
	public void getPriceIntegrationTestThree() {
		String url = this.createURLWithParams(1, 35455, "2020-06-14T21:00:00.000-00:00");
		PriceDTO response = this.restTemplate.getForObject(url, PriceDTO.class);
		Double expectedPrice = 35.50;
		Integer priceListId = 1;
		assertEquals(response.getPrice(), expectedPrice);
		assertEquals(response.getPriceList().getPriceListId(), priceListId);
	}
	
	/**
	 * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA). 
	 * Se espera un precio final 30.50 con la tarifa con ID 3. 
	 * 
	 */
	@Test
	public void getPriceIntegrationTestFour() {
		String url = this.createURLWithParams(1, 35455, "2020-06-15T10:00:00.000-00:00");
		PriceDTO response = this.restTemplate.getForObject(url, PriceDTO.class);
		Double expectedPrice = 30.50;
		Integer priceListId = 3;
		assertEquals(response.getPrice(), expectedPrice);
		assertEquals(response.getPriceList().getPriceListId(), priceListId);
	}
	
	/**
	 * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA). 
	 * Se espera un precio final 38.95 con la tarifa con ID 4. 
	 * 
	 */
	@Test
	public void getPriceIntegrationTestFive() {
		String url = this.createURLWithParams(1, 35455, "2020-06-16T21:00:00.000-00:00");
		PriceDTO response = this.restTemplate.getForObject(url, PriceDTO.class);
		Double expectedPrice = 38.95;
		Integer priceListId = 4;
		assertEquals(response.getPrice(), expectedPrice);
		assertEquals(response.getPriceList().getPriceListId(), priceListId);
	}
	
	private String createURLWithParams(Integer brandId, Integer productId, String dateStr) {	
		StringBuilder builder = new StringBuilder("http://localhost:").append(port)
				.append("/api/v1/brands/").append(brandId).append("/products/")
				.append(productId).append("/prices?aplicationDate=").append(dateStr);
        return builder.toString();
    }

}
