package es.inditex.brands.domain.ports.secundary;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface IReadGenericPersistence<T, ID> {
	Optional<T> findById(ID id);
}
