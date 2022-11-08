package es.inditex.brands.infrastructure.inbound.persistence.h2.readadapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.inditex.brands.domain.model.Price;
import es.inditex.brands.domain.ports.secundary.IReadPricePersistence;
import es.inditex.brands.infrastructure.inbound.persistence.h2.repository.IReadPriceRepository;
import es.inditex.brands.infrastructure.inbound.persistence.mapper.IPriceEntityMapper;

@Transactional
public class PricePersistenceH2ReadAdapter implements IReadPricePersistence{

	private IReadPriceRepository priceRepository;

	private IPriceEntityMapper priceMapper;
			
	public PricePersistenceH2ReadAdapter(IReadPriceRepository priceRepository, IPriceEntityMapper priceMapper) {
		this.priceRepository = priceRepository;
		this.priceMapper = priceMapper;
	}

	@Override
	public Optional<Price> findById(Integer id) {
		return Optional.of(priceMapper.priceEntityToPrice(priceRepository.getById(id)));
	}

	@Override
	public List<Price> findPricesByFilters(Integer brandId, Integer productId, LocalDateTime aplicationDate) {
		return priceMapper.priceEntityToPriceList(priceRepository.findPricesByFilters(brandId, productId, aplicationDate));
	}

}
