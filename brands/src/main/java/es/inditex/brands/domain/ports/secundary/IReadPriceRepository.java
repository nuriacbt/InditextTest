package es.inditex.brands.domain.ports.secundary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.inditex.brands.domain.model.Price;

/**
 * @author Núria Curto
 *
 */
@Repository
public interface IReadPriceRepository extends JpaRepository<Price, Integer>{
	
	/**
	 * Método busca en BDD los distintos precios y tarifas que pueden aplicarse a un 
	 * determinado producto de una cadena, en la fecha indicada
	 * 
	 * @param brandId
	 * @param productId
	 * @param aplicationDate
	 * @return List<Price>
	 */
	@Query("SELECT p FROM Price p "
			+ "WHERE p.brand.brandId = :brandId "
			+ "AND p.product.productId = :productId "
			+ "AND :aplicationDate BETWEEN p.startDate AND p.endDate")
	public List<Price> findPricesByFilters(Integer brandId, Integer productId, LocalDateTime aplicationDate);

}
