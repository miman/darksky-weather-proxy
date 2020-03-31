package eu.miman.proxy.weather.darksky.db.repos;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
	public List<ForecastEntity> findByName(String name);
	public List<ForecastEntity> findByNameAndTimeGreaterThanAndTimeLessThan(String name, LocalDateTime from, LocalDateTime to);
}
