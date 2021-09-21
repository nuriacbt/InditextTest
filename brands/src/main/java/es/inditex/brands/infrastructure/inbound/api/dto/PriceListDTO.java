package es.inditex.brands.infrastructure.inbound.api.dto;

import java.io.Serializable;

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
public class PriceListDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer priceListId;
	private String description;
}
