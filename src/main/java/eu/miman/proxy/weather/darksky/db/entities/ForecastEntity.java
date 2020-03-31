package eu.miman.proxy.weather.darksky.db.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
@Table(name="forecast", schema="weather")
public class ForecastEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**
	 * PlatformId
	 */
	private String pid;
	private String name;
    private Double latitude;
    private Double longitude;
    private String summary;
    private String iconUrl;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime time;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sunriseTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sunsetTime;
    private Double moonPhase;
    private Double precipIntensity;
    private Double precipIntensityMax;
    private Double precipProbability;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime precipIntensityMaxTime;
    private Double precipAccumulation;
    private String precipType;
    private Double temperatureHigh;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime temperatureHighTime;
    private Double temperatureLow;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime temperatureLowTime;
    private Double dewPoint;
    private Double humidity;
    private Double pressure;
    private Double windSpeed;
    private Double windGust;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime windGustTime;
    private Integer windBearing;
    private Double cloudCover;
    private Integer uvIndex;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime uvIndexTime;
    private Double visibility;
    private Double ozone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(LocalDateTime sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public LocalDateTime getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(LocalDateTime sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public Double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public void setPrecipIntensityMax(Double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public LocalDateTime getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public void setPrecipIntensityMaxTime(LocalDateTime precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    public Double getPrecipAccumulation() {
        return precipAccumulation;
    }

    public void setPrecipAccumulation(Double precipAccumulation) {
        this.precipAccumulation = precipAccumulation;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public Double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(Double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public LocalDateTime getTemperatureHighTime() {
        return temperatureHighTime;
    }

    public void setTemperatureHighTime(LocalDateTime temperatureHighTime) {
        this.temperatureHighTime = temperatureHighTime;
    }

    public Double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(Double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public LocalDateTime getTemperatureLowTime() {
        return temperatureLowTime;
    }

    public void setTemperatureLowTime(LocalDateTime temperatureLowTime) {
        this.temperatureLowTime = temperatureLowTime;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public LocalDateTime getWindGustTime() {
        return windGustTime;
    }

    public void setWindGustTime(LocalDateTime windGustTime) {
        this.windGustTime = windGustTime;
    }

    public Integer getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Integer windBearing) {
        this.windBearing = windBearing;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Integer getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }

    public LocalDateTime getUvIndexTime() {
        return uvIndexTime;
    }

    public void setUvIndexTime(LocalDateTime uvIndexTime) {
        this.uvIndexTime = uvIndexTime;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
