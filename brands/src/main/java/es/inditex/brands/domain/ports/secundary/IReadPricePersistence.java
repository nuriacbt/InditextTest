package es.inditex.brands.domain.ports.secundary;

import java.time.LocalDateTime;
import java.util.List;

import es.inditex.brands.domain.model.Price;

public interface IReadPricePersistence extends IReadGenericPersistence<Price, Integer>{
	List<Price> findPricesByFilters(Integer brandId, Integer productId, LocalDateTime aplicationDate);
}
