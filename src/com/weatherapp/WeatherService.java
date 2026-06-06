package com.weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;


/**
 * WeatherService Class
 *
 * This class handles communication with the OpenWeatherMap API.
 * It is responsible for sending HTTP requests and retrieving
 * weather and forecast information in JSON format.
 *
 * The returned JSON data is later processed and displayed
 * by the graphical user interface.
 *
 * @author: Olalekan Olatunji
 * @version 1.0
 */
public class WeatherService {

    /**
     * OpenWeatherMap API key used for authentication.
     */
    private static final String API_KEY =
    		"0f628c41d7e80e46fe43d08e1d6972b9";

    /**
     * Retrieves current weather information for a specified city.
     *
     * @param city Name of the city entered by the user
     * @return JSON response containing current weather information
     * @throws Exception if the request fails
     */
    public static String getWeatherData(String city)
            throws Exception {

        // Construct the API request URL
        String urlString =
                "https://api.openweathermap.org/data/2.5/weather?q="
                        + city
                        + "&appid="
                        + API_KEY
                        + "&units=metric";

        // Convert URL string into URL object
        URL url = URI.create(urlString).toURL();

        // Open HTTP connection
        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        // Specify GET request method
        connection.setRequestMethod("GET");

        // Configure connection timeout settings
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // Read API response
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

        StringBuilder response =
                new StringBuilder();

        String line;

        // Read response line by line
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        // Return JSON response as String
        return response.toString();
    }

    /**
     * Retrieves forecast weather information for a specified city.
     *
     * OpenWeatherMap provides forecast data in
     * 3-hour intervals for up to 5 days.
     *
     * @param city Name of the city entered by the user
     * @return JSON response containing forecast information
     * @throws Exception if the request fails
     */
    public static String getForecastData(String city)
            throws Exception {

        // Construct forecast API URL
        String urlString =
                "https://api.openweathermap.org/data/2.5/forecast?q="
                        + city
                        + "&appid="
                        + API_KEY
                        + "&units=metric";

        // Convert URL string into URL object
        URL url = URI.create(urlString).toURL();

        // Open HTTP connection
        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        // Specify GET request method
        connection.setRequestMethod("GET");

        // Read API response
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

        StringBuilder response =
                new StringBuilder();

        String line;

        // Read response data line by line
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        // Return JSON response
        return response.toString();
    }
}