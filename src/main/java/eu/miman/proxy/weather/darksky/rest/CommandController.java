package eu.miman.proxy.weather.darksky.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.miman.proxy.weather.darksky.excel.ExcelRepository;
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Language;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

@RestController
public class CommandController {
	Logger log = Logger.getLogger(CommandController.class.getName());

	String darkskyApiKey = "";

	@Autowired
	ExcelRepository excelRepository;

	public CommandController() {
		darkskyApiKey = System.getProperty("darkskyApiKey");
		log.info("CommandController started");
	}
	
	/**
	 * Retrieves the current weather for a location & stores this into the Excel file with the given name (in the project folder)
	 * @param longitude	The longitude of the location to fetch the weather for
	 * @param latitude	The latitude of the location to fetch the weather for
	 * @param name	The name of the Excel file to store the data into
	 * @return -
	 * @throws IOException
	 */
	@GetMapping("/currentweather")
	public String getCurrentWeather(@RequestParam(value = "longitude") Double longitude,
			@RequestParam(value = "latitude") Double latitude, @RequestParam(value = "name", defaultValue = "unknown") String name) throws IOException {
		ForecastRequest request = new ForecastRequestBuilder().key(new APIKey(darkskyApiKey))
				.location(new GeoCoordinates(new Longitude(longitude), new Latitude(latitude))).language(Language.en).build();

		DarkSkyJacksonClient client = new DarkSkyJacksonClient();
		Forecast forecast;
		try {
			forecast = client.forecast(request);
//			System.out.println("forecast " + forecast);
			System.out.println("forecast " + forecast.getCurrently().getTemperature());

			excelRepository.storeDarkSkyReport(forecast, name);
			return "forecast stored Ok";
		} catch (ForecastException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the weather for a specific date & location & stores this into the Excel file with the given name (in the project folder)
	 * @param longitude	The longitude of the location to fetch the weather for
	 * @param latitude	The latitude of the location to fetch the weather for
	 * @param name	The name of the Excel file to store the data into
	 * @param time	the date to get the data for (formatted according to '2011-12-03T10:15:30Z')
	 * @return -
	 * @throws IOException
	 */
	@GetMapping("/historicaltweather")
	public String getHistoricalWeather(@RequestParam(value = "longitude") Double longitude,
			@RequestParam(value = "latitude") Double latitude, 
			@RequestParam(value = "name", defaultValue = "unknown") String name, 
			@RequestParam(value = "time") String time) throws IOException {
		Instant inst = Instant.parse(time);
		ForecastRequest request = new ForecastRequestBuilder().key(new APIKey(darkskyApiKey))
				.location(new GeoCoordinates(new Longitude(longitude), new Latitude(latitude))).language(Language.en).time(inst).build();

		DarkSkyJacksonClient client = new DarkSkyJacksonClient();
		Forecast forecast;
		try {
			forecast = client.forecast(request);
//			System.out.println("forecast " + forecast);
			System.out.println("forecast " + forecast.getCurrently().getTemperature());

			excelRepository.storeDarkSkyReport(forecast, name);
			return "forecast stored Ok";
		} catch (ForecastException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the weather for a specific date & location & stores this into the Excel file with the given name (in the project folder)
	 * @param longitude	The longitude of the location to fetch the weather for
	 * @param latitude	The latitude of the location to fetch the weather for
	 * @param name	The name of the Excel file to store the data into
	 * @param time	the date to get the data for (formatted according to '2011-12-03T10:15:30Z')
	 * @return -
	 * @throws IOException
	 */
	@GetMapping("/yearlyweather")
	public String getYearlyWeather(@RequestParam(value = "longitude") Double longitude,
			@RequestParam(value = "latitude") Double latitude, 
			@RequestParam(value = "name", defaultValue = "unknown") String name, 
			@RequestParam(value = "year") String year) throws IOException {
		List<String> dateStrList = getAllYearDates(year);
		List<Forecast> forecastList = new ArrayList<Forecast>();
		
		for (String date : dateStrList) {
			Instant inst = Instant.parse(date);
			ForecastRequest request = new ForecastRequestBuilder().key(new APIKey(darkskyApiKey))
					.location(new GeoCoordinates(new Longitude(longitude), new Latitude(latitude))).language(Language.en).time(inst).build();
	
			DarkSkyJacksonClient client = new DarkSkyJacksonClient();
			Forecast forecast;
			try {
				log.info("Retriving weather info for date: " + date);
				forecast = client.forecast(request);
				forecastList.add(forecast);
				log.info("Added retreived weather info for date: " + date);
			} catch (ForecastException e) {
				e.printStackTrace();
				return null;
			}
		}
		excelRepository.storeDarkSkyReports(forecastList, name);
		return "forecast stored Ok";
}

	static public List<String> getAllYearDates(String year) {
		int iYear = Integer.parseInt(year);
		List<String> dateStrList = new ArrayList<String>();
		Calendar c = Calendar.getInstance();
		c.set(iYear, 0, 1, 12, 0, 0);
		String dateStr;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		while (c.get(Calendar.YEAR) == iYear) {
			dateStr = df.format(c.getTime());
			dateStrList.add(dateStr);
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		return dateStrList;
	}
}
