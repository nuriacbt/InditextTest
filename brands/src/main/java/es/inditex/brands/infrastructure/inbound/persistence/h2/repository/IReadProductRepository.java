package es.inditex.brands.infrastructure.inbound.persistence.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.inditex.brands.infrastructure.inbound.persistence.h2.entities.ProductEntity;

/**
 * @author Núria Curto
 *
 */
@Repository
public interface IReadProductRepository extends JpaRepository<ProductEntity, Integer> {

}
