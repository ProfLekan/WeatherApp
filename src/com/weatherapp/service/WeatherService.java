package com.weatherapp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.JSONObject;

import com.weatherapp.model.WeatherData;

import com.weatherapp.util.ConfigReader;

import com.weatherapp.model.ForecastData;

import java.util.ArrayList;
import java.util.List;

import com.weatherapp.model.ForecastEntry;

/**
 * WeatherService
 *
 * Handles communication with the OpenWeatherMap API and
 * converts the returned JSON response into WeatherData objects.
 *
 * This class serves as the service layer of the application,
 * separating API and JSON processing logic from the user interface.
 *
 * Future enhancements:
 * - Forecast retrieval
 * - Weather icon downloads
 * - Geolocation support
 * - Weather caching
 *
 * @author: Olalekan Olatunji
 * @version 2.0
 */
public class WeatherService {

    /**
     * OpenWeatherMap API key used for authentication.
     */
	private static final String API_KEY =
	        ConfigReader.getProperty("api.key");

    /**
     * Retrieves current weather information for a specified city
     * and stores the results in a WeatherData object.
     *
     * @param city Name of the city entered by the user
     * @return WeatherData object containing weather information
     * @throws Exception if the API request fails
     */
	public static WeatherData getWeather(String city)
	        throws Exception {
		
	     	    
        // Build the API request URL
        String urlString =
                "https://api.openweathermap.org/data/2.5/weather?q="
                        + city
                        + "&appid="
                        + API_KEY
                        + "&units=metric";

        // Convert URL string into URL object
        URL url = URI.create(urlString).toURL();

        // Open connection to the weather service
        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        // Use HTTP GET request
        connection.setRequestMethod("GET");

        // Set connection timeout values
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // Read the API response
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

        StringBuilder response =
                new StringBuilder();

        String line;

        // Read response line-by-line
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        // Convert JSON response into a JSONObject
        JSONObject json =
                new JSONObject(response.toString());

        // Create a WeatherData object to store weather information
        WeatherData weather =
                new WeatherData();

        // Store city information
        weather.setCity(city);

        // Store country code
        weather.setCountry(
                json.getJSONObject("sys")
                        .getString("country"));

        // Store temperature information
        weather.setTemperature(
                json.getJSONObject("main")
                        .getDouble("temp"));

        weather.setFeelsLike(
                json.getJSONObject("main")
                        .getDouble("feels_like"));

        // Store humidity and pressure
        weather.setHumidity(
                json.getJSONObject("main")
                        .getInt("humidity"));

        weather.setPressure(
                json.getJSONObject("main")
                        .getInt("pressure"));

        // Store wind information
        weather.setWindSpeed(
                json.getJSONObject("wind")
                        .getDouble("speed"));

        // Store visibility information
        weather.setVisibility(
                json.getInt("visibility"));

        // Store weather condition
        weather.setCondition(
                json.getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("main"));

        // Store icon code provided by OpenWeatherMap
        weather.setIconCode(
                json.getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("icon"));

        // Store sunrise and sunset timestamps
        weather.setSunrise(
                json.getJSONObject("sys")
                        .getLong("sunrise"));

        weather.setSunset(
                json.getJSONObject("sys")
                        .getLong("sunset"));

        // Store cloud coverage percentage
        weather.setCloudCoverage(
                json.getJSONObject("clouds")
                        .getInt("all"));
        
        

        // Return populated WeatherData object
        return weather;

    }
	
	/**
	 * Retrieves a simplified 5-day weather forecast.
	 *
	 * @param city Name of the city
	 * @return ForecastData object containing forecast information
	 * @throws Exception if the API request fails
	 */
	public static ForecastData getForecast(String city)
	        throws Exception {

	    // Build forecast API URL
	    String urlString =
	            "https://api.openweathermap.org/data/2.5/forecast?q="
	                    + city
	                    + "&appid="
	                    + API_KEY
	                    + "&units=metric";

	    // Convert URL string into URL object
	    URL url = URI.create(urlString).toURL();

	    // Open connection
	    HttpURLConnection connection =
	            (HttpURLConnection) url.openConnection();

	    connection.setRequestMethod("GET");

	    // Read API response
	    BufferedReader reader =
	            new BufferedReader(
	                    new InputStreamReader(
	                            connection.getInputStream()));

	    StringBuilder response =
	            new StringBuilder();

	    String line;

	    while ((line = reader.readLine()) != null) {
	        response.append(line);
	    }

	    reader.close();

	    // Convert response into JSON
	    JSONObject forecastJson =
	            new JSONObject(response.toString());

	    // Create forecast object
	    ForecastData forecast =
	            new ForecastData();

	    // Extract forecast information
	 // Day 1 forecast
	    forecast.setDay1(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(8)
	                    .getJSONArray("weather")
	                    .getJSONObject(0)
	                    .getString("main"));

	    // Day 2 forecast
	    forecast.setDay2(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(16)
	                    .getJSONArray("weather")
	                    .getJSONObject(0)
	                    .getString("main"));

	    // Day 3 forecast
	    forecast.setDay3(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(24)
	                    .getJSONArray("weather")
	                    .getJSONObject(0)
	                    .getString("main"));

	    // Day 4 forecast
	    forecast.setDay4(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(32)
	                    .getJSONArray("weather")
	                    .getJSONObject(0)
	                    .getString("main"));

	    // Day 5 forecast
	    forecast.setDay5(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(39)
	                    .getJSONArray("weather")
	                    .getJSONObject(0)
	                    .getString("main"));
	    
	    
	    forecast.setDay1Temp(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(8)
	                    .getJSONObject("main")
	                    .getDouble("temp"));

	    forecast.setDay2Temp(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(16)
	                    .getJSONObject("main")
	                    .getDouble("temp"));

	    forecast.setDay3Temp(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(24)
	                    .getJSONObject("main")
	                    .getDouble("temp"));

	    forecast.setDay4Temp(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(32)
	                    .getJSONObject("main")
	                    .getDouble("temp"));

	    forecast.setDay5Temp(
	            forecastJson.getJSONArray("list")
	                    .getJSONObject(39)
	                    .getJSONObject("main")
	                    .getDouble("temp"));
	    
	    
	 // Store weekday names
	    forecast.setDay1Name(
	            java.time.LocalDate.now()
	                    .plusDays(1)
	                    .getDayOfWeek()
	                    .toString());

	    forecast.setDay2Name(
	            java.time.LocalDate.now()
	                    .plusDays(2)
	                    .getDayOfWeek()
	                    .toString());

	    forecast.setDay3Name(
	            java.time.LocalDate.now()
	                    .plusDays(3)
	                    .getDayOfWeek()
	                    .toString());

	    forecast.setDay4Name(
	            java.time.LocalDate.now()
	                    .plusDays(4)
	                    .getDayOfWeek()
	                    .toString());

	    forecast.setDay5Name(
	            java.time.LocalDate.now()
	                    .plusDays(5)
	                    .getDayOfWeek()
	                    .toString());
	    

	    return forecast;
	
	}
	
	/**
	 * Retrieves all forecast records returned
	 * by OpenWeatherMap and stores them as
	 * ForecastEntry objects.
	 *
	 * @param city Name of the city
	 * @return List of forecast entries
	 * @throws Exception if API request fails
	 */
	public static List<ForecastEntry>
	        getForecastEntries(String city)
	        throws Exception {

	    // Build forecast API URL
	    String urlString =
	            "https://api.openweathermap.org/data/2.5/forecast?q="
	                    + city
	                    + "&appid="
	                    + API_KEY
	                    + "&units=metric";

	    // Convert URL string into URL object
	    URL url = URI.create(urlString).toURL();

	    // Open connection
	    HttpURLConnection connection =
	            (HttpURLConnection) url.openConnection();

	    connection.setRequestMethod("GET");

	    // Read API response
	    BufferedReader reader =
	            new BufferedReader(
	                    new InputStreamReader(
	                            connection.getInputStream()));

	    StringBuilder response =
	            new StringBuilder();

	    String line;

	    while ((line = reader.readLine()) != null) {
	        response.append(line);
	    }

	    reader.close();

	    // Convert response into JSON
	    JSONObject forecastJson =
	            new JSONObject(
	                    response.toString());

	    // Create list of forecast entries
	    List<ForecastEntry> entries =
	            new ArrayList<>();

	    // Forecast records array
	    org.json.JSONArray forecastList =
	            forecastJson.getJSONArray("list");

	    // Loop through all forecast records
	    for (int i = 0;
	         i < forecastList.length();
	         i++) {

	        JSONObject item =
	                forecastList.getJSONObject(i);

	        ForecastEntry entry =
	                new ForecastEntry();

	        // Example:
	        // 2026-06-04 15:00:00
	        String dateTime =
	                item.getString("dt_txt");

	        String[] parts =
	                dateTime.split(" ");

	        String date =
	                parts[0];

	        String time =
	                parts[1];

	        // Store date
	        entry.setDay(date);

	        // Store time
	        entry.setTime(time);

	        // Store condition
	        entry.setCondition(
	                item.getJSONArray("weather")
	                        .getJSONObject(0)
	                        .getString("main"));

	        // Store temperature
	        entry.setTemperature(
	                item.getJSONObject("main")
	                        .getDouble("temp"));

	        entries.add(entry);
	    }

	    return entries;
	}
	
}