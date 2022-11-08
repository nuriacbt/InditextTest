package es.inditex.brands.domain.ports.secundary;

import org.springframework.stereotype.Repository;

import es.inditex.brands.domain.model.Brand;


public interface IReadBrandPersistence extends IReadGenericPersistence<Brand, Integer>{

}
