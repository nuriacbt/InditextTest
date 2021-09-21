package es.inditex.brands.domain.ports.secundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.inditex.brands.domain.model.Brand;

/**
 * @author Núria Curto
 *
 */
@Repository
public interface IReadBrandRepository extends JpaRepository<Brand, Integer> {

}
