package eu.miman.proxy.weather.darksky.db.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import eu.miman.proxy.weather.darksky.db.entities.ForecastEntity;

/**
 * A repository for managing the Forecast Entities.
 * 
 * @author Mikael
 */
public interface ForecastRepository extends CrudRepository<ForecastEntity, Long> {

	public Optional<ForecastEntity> findByPid(String pid);
}
