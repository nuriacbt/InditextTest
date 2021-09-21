package es.inditex.brands.infrastructure.inbound.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Núria Curto
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer brandId;
	private Integer productId;
	private PriceListDTO priceList;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double price;
	private String currency;

}
