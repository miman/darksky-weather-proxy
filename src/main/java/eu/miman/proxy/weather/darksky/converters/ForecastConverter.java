package eu.miman.proxy.weather.darksky.converters;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import eu.miman.proxy.weather.darksky.db.entities.ForecastEntity;
import tk.plogitech.darksky.forecast.model.DailyDataPoint;
import tk.plogitech.darksky.forecast.model.Forecast;

/**
 * ForecastConverter
 */
@Component
public class ForecastConverter {

    /**
     * Copies all data from the Forecast object to the entity
     * 
     * OBS !! The name is NOT copied while it isn't part of the Forecast object.
     * 
     * @param forecast
     * @param entity
     */
    public void copyToEntity(Forecast forecast, ForecastEntity entity) {
        if (forecast == null || forecast.getDaily() == null || forecast.getDaily().getData() == null) {
            return;
        }
        List<DailyDataPoint> ddpList = forecast.getDaily().getData();
        for (DailyDataPoint dailyDataPoint : ddpList) {
            entity.setCloudCover(dailyDataPoint.getCloudCover());
            entity.setDewPoint(dailyDataPoint.getDewPoint());
            entity.setHumidity(dailyDataPoint.getHumidity());
            entity.setIconUrl(dailyDataPoint.getIcon());
            entity.setMoonPhase(dailyDataPoint.getMoonPhase());
            entity.setOzone(dailyDataPoint.getOzone());
            entity.setPrecipAccumulation(dailyDataPoint.getPrecipAccumulation());
            entity.setPrecipIntensity(dailyDataPoint.getPrecipIntensity());
            entity.setPrecipIntensityMax(dailyDataPoint.getPrecipIntensityMax());
            entity.setPrecipProbability(dailyDataPoint.getPrecipProbability());
            entity.setPrecipType(dailyDataPoint.getPrecipType());
            entity.setPressure(dailyDataPoint.getPressure());
            entity.setSummary(dailyDataPoint.getSummary());
            if (dailyDataPoint.getSunriseTime() != null) {
                entity.setSunriseTime(new Timestamp(dailyDataPoint.getSunriseTime().toEpochMilli()));
            }
            if (dailyDataPoint.getSunsetTime() != null) {
                entity.setSunsetTime(new Timestamp(dailyDataPoint.getSunsetTime().toEpochMilli()));
            }
            entity.setTemperatureHigh(dailyDataPoint.getTemperatureHigh());
            if (dailyDataPoint.getTemperatureHighTime() != null) {
                entity.setTemperatureHighTime(new Timestamp(dailyDataPoint.getTemperatureHighTime().toEpochMilli()));
            }
            entity.setTemperatureLow(dailyDataPoint.getTemperatureLow());
            if (dailyDataPoint.getTemperatureLowTime() != null) {
                entity.setTemperatureLowTime(new Timestamp(dailyDataPoint.getTemperatureLowTime().toEpochMilli()));
            }
            if (dailyDataPoint.getTime() != null) {
                entity.setTime(new Timestamp(dailyDataPoint.getTime().toEpochMilli()));
            }
            entity.setUvIndex(dailyDataPoint.getUvIndex());
            if (dailyDataPoint.getUvIndexTime() != null) {
                entity.setUvIndexTime(new Timestamp(dailyDataPoint.getUvIndexTime().toEpochMilli()));
            }
            entity.setVisibility(dailyDataPoint.getVisibility());
            entity.setWindBearing(dailyDataPoint.getWindBearing());
            entity.setWindGust(dailyDataPoint.getWindGust());
            if (dailyDataPoint.getWindGustTime() != null) {
                entity.setWindGustTime(new Timestamp(dailyDataPoint.getWindGustTime().toEpochMilli()));
            }
            entity.setWindSpeed(dailyDataPoint.getWindSpeed());
        }
//        entity.setId(""); // Will be set by Db
//        entity.setName("");
        if (entity.getPid() == null) {
            entity.setPid(UUID.randomUUID().toString());
        }
        entity.setLatitude(forecast.getLatitude().value());
        entity.setLongitude(forecast.getLongitude().value());
    }
}