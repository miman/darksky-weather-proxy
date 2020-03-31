package eu.miman.proxy.weather.darksky.rest;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.miman.proxy.weather.darksky.db.entities.ForecastEntity;
import eu.miman.proxy.weather.darksky.db.repos.ForecastRepository;

/**
 * ForecastController
 */
@RestController
public class ForecastController {

	@Autowired
	ForecastRepository forecastRepository;

	/**
	 * Retrieves the  weather forecasts for a locationname
	 * @param name	The name of the location we should return all forecasts for
	 * @return -
	 * @throws IOException
	 */
	@GetMapping("/weatherforecast")
	public String getWeatherForecast(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "year", required = false) Integer year) throws IOException {
		try {
            String jsonString = "";
            if (year != null) {
                Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                c.set(year, 0, 1, 0, 0, 0);
                c.set(year-1, 11, 31, 23, 59, 59);
                LocalDateTime from = LocalDateTime.ofInstant(c.toInstant(), ZoneId.of("GMT"));
                c.set(year+1, 0, 1, 0, 0, 0);
                LocalDateTime to = LocalDateTime.ofInstant(c.toInstant(), ZoneId.of("GMT"));
                List<ForecastEntity> forecasts = forecastRepository.findByNameAndTimeGreaterThanAndTimeLessThan(name, from, to);
                System.out.println("from: " + from.toString() + ", to: " + to.toString());
                ObjectMapper mapper = new ObjectMapper();
                jsonString = mapper.writeValueAsString(forecasts);
            } else {
                List<ForecastEntity> forecasts = forecastRepository.findByName(name);
                ObjectMapper mapper = new ObjectMapper();
                jsonString = mapper.writeValueAsString(forecasts);
            }
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
}