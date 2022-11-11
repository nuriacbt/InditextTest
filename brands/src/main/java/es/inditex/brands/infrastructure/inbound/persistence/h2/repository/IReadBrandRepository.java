package es.inditex.brands.infrastructure.inbound.persistence.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.inditex.brands.infrastructure.inbound.persistence.h2.entities.BrandEntity;

/**
 * @author Núria Curto
 *
 */

public interface IReadBrandRepository extends JpaRepository<BrandEntity, Integer> {

}
