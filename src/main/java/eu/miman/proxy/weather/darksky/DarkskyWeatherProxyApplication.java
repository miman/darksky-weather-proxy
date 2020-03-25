package eu.miman.proxy.weather.darksky;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DarkskyWeatherProxyApplication {

	public static void main(String[] args) {
		// Need to set global locale for DarkSky API to work properly (to use . instead of , in doubles).
		Locale.setDefault(Locale.ENGLISH);
		SpringApplication.run(DarkskyWeatherProxyApplication.class, args);
	}

}
