package com.weatherapp.model;

/**
 * WeatherData Model Class
 *
 * This class serves as a data container for all weather-related
 * information retrieved from the OpenWeatherMap API.
 *
 * The object is used to transfer weather information between
 * different layers of the application without exposing raw JSON data.
 *
 * Future enhancements such as sunrise/sunset display,
 * weather icons, and advanced forecasts can also be stored here.
 *
 * @author Olalekan Olatunji
 * @version 1.0
 */
public class WeatherData {

    // Basic weather information
    private String city;
    private String country;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private double windSpeed;
    private int pressure;
    private int visibility;
    private String condition;

    // Additional weather details
    private long sunrise;
    private long sunset;
    private int cloudCoverage;
    private String iconCode;

    /**
     * Default constructor.
     * Creates an empty WeatherData object.
     */
    public WeatherData() {
    }

    /**
     * Gets the city name.
     *
     * @return city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city name.
     *
     * @param city city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the country code.
     *
     * @return country code
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country code.
     *
     * @param country country code
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the temperature.
     *
     * @return temperature in Celsius
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature.
     *
     * @param temperature temperature value
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the perceived temperature.
     *
     * @return feels-like temperature
     */
    public double getFeelsLike() {
        return feelsLike;
    }

    /**
     * Sets the perceived temperature.
     *
     * @param feelsLike feels-like temperature
     */
    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    /**
     * Gets humidity level.
     *
     * @return humidity percentage
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * Sets humidity level.
     *
     * @param humidity humidity percentage
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets wind speed.
     *
     * @return wind speed value
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Sets wind speed.
     *
     * @param windSpeed wind speed value
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Gets atmospheric pressure.
     *
     * @return pressure in hPa
     */
    public int getPressure() {
        return pressure;
    }

    /**
     * Sets atmospheric pressure.
     *
     * @param pressure pressure value
     */
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets visibility distance.
     *
     * @return visibility in meters
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * Sets visibility distance.
     *
     * @param visibility visibility value
     */
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    /**
     * Gets current weather condition.
     *
     * @return weather condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets current weather condition.
     *
     * @param condition weather condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Gets sunrise time.
     *
     * @return sunrise timestamp
     */
    public long getSunrise() {
        return sunrise;
    }

    /**
     * Sets sunrise time.
     *
     * @param sunrise sunrise timestamp
     */
    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Gets sunset time.
     *
     * @return sunset timestamp
     */
    public long getSunset() {
        return sunset;
    }

    /**
     * Sets sunset time.
     *
     * @param sunset sunset timestamp
     */
    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    /**
     * Gets cloud coverage percentage.
     *
     * @return cloud coverage percentage
     */
    public int getCloudCoverage() {
        return cloudCoverage;
    }

    /**
     * Sets cloud coverage percentage.
     *
     * @param cloudCoverage cloud coverage percentage
     */
    public void setCloudCoverage(int cloudCoverage) {
        this.cloudCoverage = cloudCoverage;
    }

    /**
     * Gets weather icon code.
     *
     * @return icon code provided by OpenWeatherMap
     */
    public String getIconCode() {
        return iconCode;
    }

    /**
     * Sets weather icon code.
     *
     * @param iconCode icon identifier
     */
    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }
}