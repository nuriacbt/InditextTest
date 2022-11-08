package es.inditex.brands.infrastructure.inbound.persistence.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.inditex.brands.domain.model.Brand;
import es.inditex.brands.infrastructure.inbound.persistence.h2.entities.BrandEntity;

/**
 * @author Núria Curto
 *
 */

public interface IReadBrandRepository extends JpaRepository<BrandEntity, Integer> {

}
