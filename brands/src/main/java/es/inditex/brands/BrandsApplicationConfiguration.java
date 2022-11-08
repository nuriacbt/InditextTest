package es.inditex.brands;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.inditex.brands.domain.ports.primary.IBrandService;
import es.inditex.brands.domain.ports.primary.IPriceService;
import es.inditex.brands.domain.ports.primary.IProductService;
import es.inditex.brands.domain.ports.secundary.IReadBrandPersistence;
import es.inditex.brands.domain.ports.secundary.IReadPricePersistence;
import es.inditex.brands.domain.ports.secundary.IReadProductPersistence;
import es.inditex.brands.domain.service.BrandServiceImpl;
import es.inditex.brands.domain.service.ProductServiceImpl;
import es.inditex.brands.domain.service.PriceServiceImpl;
import es.inditex.brands.infrastructure.inbound.api.exception.handler.CustomGlobalExceptionHandler;
import es.inditex.brands.infrastructure.inbound.persistence.h2.readadapter.BrandPersistenceH2ReadAdapter;
import es.inditex.brands.infrastructure.inbound.persistence.h2.readadapter.PricePersistenceH2ReadAdapter;
import es.inditex.brands.infrastructure.inbound.persistence.h2.readadapter.ProductPersistenceH2ReadAdapter;
import es.inditex.brands.infrastructure.inbound.persistence.h2.repository.IReadBrandRepository;
import es.inditex.brands.infrastructure.inbound.persistence.h2.repository.IReadPriceRepository;
import es.inditex.brands.infrastructure.inbound.persistence.h2.repository.IReadProductRepository;
import es.inditex.brands.infrastructure.inbound.persistence.mapper.IBrandEntityMapper;
import es.inditex.brands.infrastructure.inbound.persistence.mapper.IPriceEntityMapper;
import es.inditex.brands.infrastructure.inbound.persistence.mapper.IProductEntityMapper;

@ComponentScan(basePackageClasses = {CustomGlobalExceptionHandler.class})
@Configuration
@EntityScan(basePackages = {"es.inditex.brands.infrastructure.inbound.persistence.h2.entities"})
@EnableJpaRepositories(basePackages = { "es.inditex.brands.infrastructure.inbound.persistence.h2.repository" })
public class BrandsApplicationConfiguration {
	
	@Bean
    public IBrandService brandService(IReadBrandPersistence brandPersistence) {
        return new BrandServiceImpl(brandPersistence);
    }
	
	@Bean
    public IPriceService priceService(IBrandService brandService, IProductService productService,
			IReadPricePersistence pricePersistence) {
        return new PriceServiceImpl(brandService, productService, pricePersistence);
    }
		
	@Bean
    public IProductService productService(IReadProductPersistence productPersistence) {
        return new ProductServiceImpl(productPersistence);
    }
	
	@Bean
    public IReadBrandPersistence brandPersistence(IReadBrandRepository brandRepository, IBrandEntityMapper brandMapper) {
        return new BrandPersistenceH2ReadAdapter(brandRepository, brandMapper);
    }
	
	@Bean
    public IReadPricePersistence pricePersistence(IReadPriceRepository priceRepository, IPriceEntityMapper priceMapper) {
        return new PricePersistenceH2ReadAdapter(priceRepository, priceMapper);
    }
	
	@Bean
    public IReadProductPersistence productPersistence(IReadProductRepository productRepository, IProductEntityMapper productMapper) {
        return new ProductPersistenceH2ReadAdapter(productRepository, productMapper);
    }
	
}
