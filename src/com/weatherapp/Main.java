package com.weatherapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.scene.control.ToggleButton;
import javafx.stage.Screen;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.UnknownHostException;
import java.net.ConnectException;
import com.weatherapp.util.HistoryManager;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

import javafx.scene.control.ComboBox;
import com.weatherapp.util.FavoritesManager;

import java.util.List;

import com.weatherapp.util.ForecastStatistics;
import com.weatherapp.util.WeatherAlert;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;



/*
 * Main class for the Weather Information Application.
 * This class creates the graphical user interface (GUI),
 * handles user interactions, retrieves weather information
 * from the OpenWeatherMap API, and displays the results.
 */
public class Main extends Application {
	
	// Stores the most recently retrieved temperature value
    // for Celsius/Fahrenheit conversion.
	private double currentTemperature = 0;
	
	private double currentWindSpeed = 0;
		
	// Indicates whether weather data has been successfully loaded.
	private boolean weatherLoaded = false;

    @Override
    public void start(Stage stage) {
    	
    	// Application title displayed at the top of the window
        Label titleLabel = new Label("Weather Information App");
        titleLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        // City Input - Text field where users enter a city name
        TextField cityField = new TextField();
        cityField.setPromptText("Enter City Name");

        // Search Button - Button used to retrieve weather information
        Button searchButton = new Button("Get Weather");
        
        //Favorite Cities
        Button favoriteButton =
                new Button(
                        "Add Current City");

        ComboBox<String> favoritesBox =
                new ComboBox<>();

        favoritesBox.setPromptText(
                "★ Favorite Cities");

        double fieldWidth = 300;

        cityField.setPrefWidth(fieldWidth);
        favoritesBox.setPrefWidth(fieldWidth);

        favoritesBox.setItems(
                FavoritesManager
                        .loadFavorites());        
        
     // Toggle button for Celsius/Fahrenheit conversion
        ToggleButton unitToggle =
                new ToggleButton("Show °F");
        
     // Toggle button for wind conversion
        ToggleButton windToggle =
                new ToggleButton("Show km/h");
        
        //Toggle Button for Weather Forecast
        ToggleButton forecastToggle =
                new ToggleButton(
                        "Show Detailed Forecast");

        // Weather Information Labels
        //Current weather section
        Label weatherHeader = new Label("Current Weather");

        Label temperatureLabel =
                new Label("Temperature: --");

        Label humidityLabel =
                new Label("Humidity: --");

        Label windSpeedLabel =
                new Label("Wind Speed: --");

        Label conditionLabel =
                new Label("Condition: --");

        // Forecast Section
        Label forecastHeader =
                new Label("Weather Forecast");
        
        ImageView weatherIconView =
                new ImageView();

        //Open Weather Icon
        weatherIconView.setFitWidth(80);
        weatherIconView.setFitHeight(80);

        weatherIconView.setPreserveRatio(true);
        
     // Individual forecast labels
        Label day1Label =
                new Label(
                        formatDayName(
                                java.time.LocalDate.now()
                                        .plusDays(1)
                                        .getDayOfWeek()
                                        .toString())
                        + "\n--\n--\n--");

        Label day2Label =
                new Label(
                        formatDayName(
                                java.time.LocalDate.now()
                                        .plusDays(2)
                                        .getDayOfWeek()
                                        .toString())
                        + "\n--\n--\n--");

        Label day3Label =
                new Label(
                        formatDayName(
                                java.time.LocalDate.now()
                                        .plusDays(3)
                                        .getDayOfWeek()
                                        .toString())
                        + "\n--\n--\n--");

        Label day4Label =
                new Label(
                        formatDayName(
                                java.time.LocalDate.now()
                                        .plusDays(4)
                                        .getDayOfWeek()
                                        .toString())
                        + "\n--\n--\n--");

        Label day5Label =
                new Label(
                        formatDayName(
                                java.time.LocalDate.now()
                                        .plusDays(5)
                                        .getDayOfWeek()
                                        .toString())
                        + "\n--\n--\n--");
        // End of individual forecast labels

       // Horizontal forecast container
        HBox forecastBox =
                new HBox(20);

     // Detailed forecast table
        GridPane forecastGrid =
                new GridPane();

        forecastGrid.setHgap(10);
        forecastGrid.setVgap(5);
        forecastGrid.setPadding(
                new Insets(10));

        // Top-left corner
        forecastGrid.add(
                new Label("Time"),
                0,
                0);
        
        //Hide Grid Initially
        forecastGrid.setVisible(false);
        forecastGrid.setManaged(false);

        // Generate Today + Next 5 Days
        for (int i = 0; i <= 5; i++) {

            String dayName =
                    java.time.LocalDate.now()
                            .plusDays(i)
                            .getDayOfWeek()
                            .toString()
                            .substring(0, 1)
                    +
                    java.time.LocalDate.now()
                            .plusDays(i)
                            .getDayOfWeek()
                            .toString()
                            .substring(1, 3)
                            .toLowerCase();

            forecastGrid.add(
                    new Label(dayName),
                    i + 1,     // IMPORTANT
                    0);
        }

        // Time rows
        String[] times = {
                "12am",
                "3am",
                "6am",
                "9am",
                "12pm",
                "3pm",
                "6pm",
                "9pm"
        };

        for (int i = 0; i < times.length; i++) {

            forecastGrid.add(
                    new Label(times[i]),
                    0,
                    i + 1);
        }

      
        forecastBox.getChildren().addAll(
                day1Label,
                day2Label,
                day3Label,
                day4Label,
                day5Label
        );
        
        // Additional weather details
        Label feelsLikeLabel =
                new Label("Feels Like: --");

        Label pressureLabel =
                new Label("Pressure: --");

        Label visibilityLabel =
                new Label("Visibility: --");

        Label cloudLabel =
                new Label("Cloud Coverage: --");

        Label sunriseLabel =
                new Label("Sunrise: --");

        Label sunsetLabel =
                new Label("Sunset: --");
        
     // Next 24 Hours Section
        Label summary24Header =
                new Label(
                        "Next 24 Hours");

        summary24Header.setStyle(
                "-fx-font-size: 16px;"
                + "-fx-font-weight: bold;");

        Label highestTempLabel =
                new Label(
                        "Highest Temperature: --");

        Label lowestTempLabel =
                new Label(
                        "Lowest Temperature: --");

        Label averageTempLabel =
                new Label(
                        "Average Temperature: --");
        
        // Weather Alerts Section
        Label alertsHeader =
                new Label(
                        "Weather Alerts");

        alertsHeader.setStyle(
                "-fx-font-size: 16px;"
                + "-fx-font-weight: bold;");

        ListView<String> alertsList =
                new ListView<>();

        alertsList.setPrefHeight(100);
        
        //Chart components
        Label chartHeader =
                new Label(
                        "Temperature Trend");

        chartHeader.setStyle(
                "-fx-font-size: 16px;"
                + "-fx-font-weight: bold;");

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        xAxis.setLabel("Time");
        yAxis.setLabel("Temperature (°C)");

        LineChart<String, Number> temperatureChart =
                new LineChart<>(
                        xAxis,
                        yAxis);

        temperatureChart.setLegendVisible(false);

        temperatureChart.setPrefHeight(250);
        //End of chart components
        
        // Search History
        Label historyHeader =
                new Label("Search History");

        ListView<String> historyList =
                new ListView<>();

        // Load previously saved search history
        historyList.setItems(
                com.weatherapp.util.HistoryManager
                        .loadHistory());

        historyList.setPrefHeight(120);

        //Favorite button logic
        favoriteButton.setOnAction(e -> {

            String city =
                    cityField.getText()
                            .trim();

            if (city.isEmpty()) {
                return;
            }

            FavoritesManager.addFavorite(
                    city,
                    favoritesBox.getItems());
        });
        
        //Auto search favorite city
        favoritesBox.setOnAction(e -> {

            String city =
                    favoritesBox.getValue();

            if (city == null) {
                return;
            }

            cityField.setText(city);

            searchButton.fire();
        });
        
        // Event handler for the "Get Weather" button.
        // Retrieves current weather and forecast data
        // from the OpenWeatherMap API.
        searchButton.setOnAction(e -> {

            try {

                String city =
                        cityField.getText().trim();

                // Ensures user enters a city name
                if (city.isEmpty()) {

                    conditionLabel.setText(
                            "Condition: Please enter a city");

                    return;
                }

                // Retrieve current weather information                               
                com.weatherapp.model.WeatherData weather =
                        com.weatherapp.service.WeatherService
                                .getWeather(city);
                
                String iconUrl =
                        "https://openweathermap.org/img/wn/"
                        + weather.getIconCode()
                        + "@2x.png";

                weatherIconView.setImage(
                        new Image(iconUrl));
                
                //Sunrise and sunset conversion
                java.time.Instant sunriseInstant =
                        java.time.Instant.ofEpochSecond(
                                weather.getSunrise());

                java.time.Instant sunsetInstant =
                        java.time.Instant.ofEpochSecond(
                                weather.getSunset());

                java.time.ZoneId zone =
                        java.time.ZoneId.systemDefault();

                String sunriseTime =
                        java.time.format.DateTimeFormatter
                                .ofPattern("hh:mm a")
                                .format(
                                        sunriseInstant
                                                .atZone(zone));

                String sunsetTime =
                        java.time.format.DateTimeFormatter
                                .ofPattern("hh:mm a")
                                .format(
                                        sunsetInstant
                                                .atZone(zone));
                //End of sunrise and sunset conversion
                
                
                // Retrieve forecast information
                com.weatherapp.model.ForecastData forecast =
                        com.weatherapp.service.WeatherService
                                .getForecast(city);
                
                List<com.weatherapp.model.ForecastEntry> entries =
                        com.weatherapp.service.WeatherService
                                .getForecastEntries(city); 
                
                //Populate chart
                temperatureChart.getData().clear();

                XYChart.Series<String, Number> series =
                        new XYChart.Series<>();

                int limit =
                        Math.min(
                                8,
                                entries.size());

                for (int i = 0;
                     i < limit;
                     i++) {

                    com.weatherapp.model.ForecastEntry entry =
                            entries.get(i);

                    String timeLabel =
                            entry.getTime();

                    if (timeLabel.length() >= 5) {

                        int hour =
                                Integer.parseInt(
                                        timeLabel.substring(0, 2));

                        String displayTime;

                        if (hour == 0) {
                            displayTime = "12am";
                        } else if (hour < 12) {
                            displayTime = hour + "am";
                        } else if (hour == 12) {
                            displayTime = "12pm";
                        } else {
                            displayTime =
                                    (hour - 12)
                                    + "pm";
                        }

                        series.getData().add(
                                new XYChart.Data<>(
                                        displayTime,
                                        entry.getTemperature()));
                    }
                }

                temperatureChart.getData()
                        .add(series);
                //End of populate chart
                
             // Next 24 Hours Summary
                ForecastStatistics stats =
                        new ForecastStatistics(
                                entries);

                highestTempLabel.setText(
                        "Highest Temperature: "
                        + String.format(
                                "%.1f°C",
                                stats.getHighestTemp())
                        + " at "
                        + stats.getHighestTempTime());

                lowestTempLabel.setText(
                        "Lowest Temperature: "
                        + String.format(
                                "%.1f°C",
                                stats.getLowestTemp())
                        + " at "
                        + stats.getLowestTempTime());

                averageTempLabel.setText(
                        "Average Temperature: "
                        + String.format(
                                "%.1f°C",
                                stats.getAverageTemp()));

                // Weather Alerts
                alertsList.getItems().setAll(
                        WeatherAlert
                                .generateAlerts(
                                        entries));
                
             // Remove old forecast cells
                forecastGrid.getChildren().removeIf(
                        node -> GridPane.getRowIndex(node) != null
                                && GridPane.getColumnIndex(node) != null
                                && GridPane.getRowIndex(node) > 0
                                && GridPane.getColumnIndex(node) > 0);
                               
               //Pre-fill Grid with Faded "--"
                String[] gridtimes = {
                        "12am",
                        "3am",
                        "6am",
                        "9am",
                        "12pm",
                        "3pm",
                        "6pm",
                        "9pm"
                };

                for (int col = 0; col <= 5; col++) {

                    for (int row = 1;
                         row <= gridtimes.length;
                         row++) {

                        Label empty =
                                new Label("--");

                        empty.setStyle(
                                "-fx-text-fill: rgba(0,0,0,0.35);");

                        forecastGrid.add(
                                empty,
                                col + 1,
                                row);
                    }
                }
                
                // Fill detailed forecast table
                for (com.weatherapp.model.ForecastEntry entry : entries) {

                    String date = entry.getDay();

                    java.time.LocalDate forecastDate =
                            java.time.LocalDate.parse(date);

                    long dayOffset =
                            java.time.temporal.ChronoUnit.DAYS.between(
                                    java.time.LocalDate.now(),
                                    forecastDate);

                    // Only display today and next 5 days
                    if (dayOffset < 0 || dayOffset > 5) {
                        continue;
                    }

                    String time = entry.getTime();

                    int row = -1;

                    if (time.startsWith("00")) row = 1;
                    else if (time.startsWith("03")) row = 2;
                    else if (time.startsWith("06")) row = 3;
                    else if (time.startsWith("09")) row = 4;
                    else if (time.startsWith("12")) row = 5;
                    else if (time.startsWith("15")) row = 6;
                    else if (time.startsWith("18")) row = 7;
                    else if (time.startsWith("21")) row = 8;

                    if (row == -1) {
                        continue;
                    }

                    int column = (int) dayOffset;
                                
                    String icon =
                            getForecastIcon(
                                    entry.getCondition());

                    Label cell =
                            new Label(
                                    icon + " "
                                    + String.format(
                                            "%.0f°C",
                                            entry.getTemperature()));
                                     
                    forecastGrid.add(
                            cell,
                            column + 1,
                            row);
                }
              
                             
                // Extract temperature, humidity,
                // wind speed and weather condition
                double temperature =
                        weather.getTemperature();
                currentTemperature = temperature;
                weatherLoaded = true;

                unitToggle.setSelected(false);
                unitToggle.setText("Show °F");

                windToggle.setSelected(false);
                windToggle.setText("Show km/h");
                
                int humidity =
                        weather.getHumidity();

                double windSpeed =
                        weather.getWindSpeed();

                currentWindSpeed = windSpeed;

                String condition =
                        weather.getCondition();
                
                // Extract forecast information.
                // Forecast data is provided every 3 hours.
                // Indexes 0, 8 and 16 approximately represent
                // today, tomorrow and the following day.
                String day1 = forecast.getDay1();
                String day2 = forecast.getDay2();
                String day3 = forecast.getDay3();
                String day4 = forecast.getDay4();
                String day5 = forecast.getDay5();
                
                double day1Temp = forecast.getDay1Temp();
                double day2Temp = forecast.getDay2Temp();
                double day3Temp = forecast.getDay3Temp();
                double day4Temp = forecast.getDay4Temp();
                double day5Temp = forecast.getDay5Temp();
                
                String day1Name =
                        forecast.getDay1Name();

                String day2Name =
                        forecast.getDay2Name();

                String day3Name =
                        forecast.getDay3Name();

                String day4Name =
                        forecast.getDay4Name();

                String day5Name =
                        forecast.getDay5Name();
                
                // Update forecast section
                day1Label.setText(
                        formatDayName(day1Name)
                        + "\n"
                        + getForecastIcon(day1)
                        + "\n"
                        + day1
                        + "\n"
                        + String.format("%.0f°C",
                                day1Temp));

                day2Label.setText(
                        formatDayName(day2Name)
                        + "\n"
                        + getForecastIcon(day2)
                        + "\n"
                        + day2
                        + "\n"
                        + String.format("%.0f°C",
                                day2Temp));

                day3Label.setText(
                        formatDayName(day3Name)
                        + "\n"
                        + getForecastIcon(day3)
                        + "\n"
                        + day3
                        + "\n"
                        + String.format("%.0f°C",
                                day3Temp));

                day4Label.setText(
                        formatDayName(day4Name)
                        + "\n"
                        + getForecastIcon(day4)
                        + "\n"
                        + day4
                        + "\n"
                        + String.format("%.0f°C",
                                day4Temp));

                day5Label.setText(
                        formatDayName(day5Name)
                        + "\n"
                        + getForecastIcon(day5)
                        + "\n"
                        + day5
                        + "\n"
                        + String.format("%.0f°C",
                                day5Temp));
               

                // Update GUI labels with current weather information
                temperatureLabel.setText(
                        "Temperature: " +
                        temperature +
                        " °C");

                humidityLabel.setText(
                        "Humidity: " +
                        humidity +
                        "%");

                windSpeedLabel.setText(
                        "Wind Speed: " +
                        windSpeed +
                        " m/s");
                
                
                feelsLikeLabel.setText(
                        "Feels Like: "
                                + String.format("%.2f",
                                        weather.getFeelsLike())
                                + " °C");

                pressureLabel.setText(
                        "Pressure: "
                                + weather.getPressure()
                                + " hPa");

                visibilityLabel.setText(
                        "Visibility: "
                                + (weather.getVisibility() / 1000)
                                + " km");

                cloudLabel.setText(
                        "Cloud Coverage: "
                                + weather.getCloudCoverage()
                                + "%");
                
                
                sunriseLabel.setText(
                        "Sunrise: "
                                + sunriseTime);

                sunsetLabel.setText(
                        "Sunset: "
                                + sunsetTime);

                //weatherIcon
                // Select an appropriate weather icon
                // based on the weather condition returned by the API.
                String weatherIcon = "";

                switch (condition) {

                    case "Clear":
                        weatherIcon = "☀";
                        break;

                    case "Clouds":
                        weatherIcon = "☁";
                        break;

                    case "Rain":
                        weatherIcon = "🌧";
                        break;

                    case "Thunderstorm":
                        weatherIcon = "⛈";
                        break;

                    case "Snow":
                        weatherIcon = "❄";
                        break;

                    case "Mist":
                    case "Fog":
                    case "Haze":
                        weatherIcon = "🌫";
                        break;

                    default:
                        weatherIcon = "🌍";
                        
                    case "Drizzle":
                        weatherIcon = "🌦";
                        break;

                    case "Smoke":
                        weatherIcon = "💨";
                        break;

                    case "Dust":
                    case "Sand":
                        weatherIcon = "🌪";
                        break;

                    case "Tornado":
                        weatherIcon = "🌪";
                        break;
                }

                conditionLabel.setText(
                        "Condition: " +
                        weatherIcon +
                        " " +
                        condition);
                //End of weatherIcon

                // Add searched city and time
                // to the search history list
             // Create date and time stamp
                String dateTime =
                        LocalDateTime.now()
                                .format(
                                        java.time.format.DateTimeFormatter
                                                .ofPattern(
                                                        "yyyy-MM-dd HH:mm:ss"));

                // Create detailed history record
                String historyEntry =
                        city
                        + " | "
                        + String.format("%.2f",
                                weather.getTemperature())
                        + "°C"
                        + " | "
                        + weather.getCondition()
                        + " | Humidity: "
                        + weather.getHumidity()
                        + "%"
                        + " | Wind: "
                        + String.format("%.2f",
                                weather.getWindSpeed())
                        + " m/s"
                        + " | "
                        + dateTime;

                // Display history in the ListView
                historyList.getItems().add(0, historyEntry);

                // Save history to file
                HistoryManager.saveHistory(
                        historyEntry);

             } catch (Exception ex) {

            	 ex.printStackTrace();
            	 
            	    Alert alert =
            	            new Alert(AlertType.ERROR);

            	    alert.setTitle("Weather App Error");

            	    if (ex instanceof UnknownHostException ||
            	        ex instanceof ConnectException) {

            	        alert.setHeaderText(
            	                "No Internet Connection");

            	        alert.setContentText(
            	                "Please check your internet connection and try again.");

            	        conditionLabel.setText(
            	                "Condition: No internet connection");

            	    } else {

            	        alert.setHeaderText(
            	                "City Not Found");

            	        alert.setContentText(
            	                "Please enter a valid city name.");

            	        conditionLabel.setText(
            	                "Condition: City not found");
            	    }

            	    alert.showAndWait();
            	}
        });
        //Press enter to search
        cityField.setOnAction(e ->
        searchButton.fire());
        
        //Celsius ↔ Fahrenheit Conversion code
        // Allows users to switch between
        // Celsius and Fahrenheit units
        unitToggle.setOnAction(e -> {

            if (!weatherLoaded) {

                temperatureLabel.setText(
                        "Temperature: Search for a city first");

                unitToggle.setSelected(false);

                return;
            }

            if(unitToggle.isSelected()) {

                double fahrenheit =
                        (currentTemperature * 9 / 5) + 32;

                temperatureLabel.setText(
                        String.format(
                                "Temperature: %.2f °F",
                                fahrenheit));

                unitToggle.setText("Show °C");

            } else {

                temperatureLabel.setText(
                        String.format(
                                "Temperature: %.2f °C",
                                currentTemperature));

                unitToggle.setText("Show °F");
            }
        });
        //End of Celsius and Fahrenheit Conversion code

        //Wind conversion
        windToggle.setOnAction(e -> {

            if (!weatherLoaded) {

                windSpeedLabel.setText(
                        "Wind Speed: Search for a city first");

                windToggle.setSelected(false);

                return;
            }

            if (windToggle.isSelected()) {

                double kmh =
                        currentWindSpeed * 3.6;

                windSpeedLabel.setText(
                        String.format(
                                "Wind Speed: %.2f km/h",
                                kmh));

                windToggle.setText("Show m/s");

            } else {

                windSpeedLabel.setText(
                        String.format(
                                "Wind Speed: %.2f m/s",
                                currentWindSpeed));

                windToggle.setText("Show km/h");
            }
        });
        //End of wind conversion
       
        //Forecast conversion from summary to grid
        forecastToggle.setOnAction(e -> {

            if (forecastToggle.isSelected()) {

                forecastBox.setVisible(false);
                forecastBox.setManaged(false);

                forecastGrid.setVisible(true);
                forecastGrid.setManaged(true);

                forecastToggle.setText(
                        "Show Summary Forecast");

            } else {

                forecastGrid.setVisible(false);
                forecastGrid.setManaged(false);

                forecastBox.setVisible(true);
                forecastBox.setManaged(true);

                forecastToggle.setText(
                        "Show Detailed Forecast");
            }
        });
        //End of forecast conversion
               
        // Main container that holds all GUI components
        // in a vertical arrangement
        VBox root = new VBox(10);

        root.setPadding(
                new Insets(15)
        );

        // Add all user interface components
        // to the main application window
        root.getChildren().addAll(
                titleLabel,
                
                new HBox(
                        10,
                        cityField,
                        searchButton),

                new HBox(
                        10,
                        favoritesBox,
                        favoriteButton),
                
                unitToggle,
                windToggle,
               
                weatherHeader,
                temperatureLabel,
                humidityLabel,
                windSpeedLabel,
                //weatherIconView,
                conditionLabel,
                
                feelsLikeLabel,
                pressureLabel,
                visibilityLabel,
                cloudLabel,
                sunriseLabel,
                sunsetLabel,

               
                forecastHeader,
                
                forecastToggle,
                
                forecastBox,
                forecastGrid,
                
                summary24Header,

                highestTempLabel,
                lowestTempLabel,
                averageTempLabel,
                
                chartHeader,
                temperatureChart,
                
                alertsHeader,
                alertsList,
                              
                historyHeader,
                historyList
        );
        
        //Background logic for dynamic background
        // Change application background colour
        // based on the current time of day
        LocalTime currentTime = LocalTime.now();

        if (currentTime.isAfter(LocalTime.of(6, 0))
                && currentTime.isBefore(LocalTime.of(12, 0))) {

            root.setStyle(
                    "-fx-background-color: lightblue;"
            );

        } else if (currentTime.isBefore(LocalTime.of(18, 0))) {

            root.setStyle(
                    "-fx-background-color: lightyellow;"
            );

        } else if (currentTime.isBefore(LocalTime.of(21, 0))) {

            root.setStyle(
                    "-fx-background-color: orange;"
            );

        } else {

            root.setStyle(
                    "-fx-background-color: darkgray;"
            );
        }
        //end of background logic
        
        // Create the application scene and
               
     // Creates a scrollable container for the app
        ScrollPane scrollPane =
                new ScrollPane(root);

        // Makes the scroll pane use full width
        scrollPane.setFitToWidth(true);
        
        // define the window dimensions
        Scene scene =
                new Scene(scrollPane, 500, 650);
        
        //App title
        stage.setTitle(
                "Weather App by Olalekan Olatunji"
        );
        
       stage.getIcons().add(
                new Image(
                        "file:assets/icons/app_icon.png"));

        stage.setScene(scene);
        
     // Get screen height
        double screenHeight =
                javafx.stage.Screen
                        .getPrimary()
                        .getVisualBounds()
                        .getHeight();

        // Fixed width, full screen height
        stage.setWidth(600);
        stage.setHeight(screenHeight);
        
        stage.show();
    }

    private String getForecastIcon(String condition) {

        switch (condition) {

            case "Clear":
                return "☀";

            case "Clouds":
                return "☁";

            case "Rain":
                return "🌧";

            case "Thunderstorm":
                return "⛈";

            case "Snow":
                return "❄";

            default:
                return "🌍";
        }
    }
    
    /*
     * Converts full weekday names
     * into short display names.
     */
    private String formatDayName(
            String dayName) {

        if (dayName == null) {
            return "---";
        }

        return dayName.substring(0, 1)
                + dayName.substring(1, 3)
                        .toLowerCase();
    }
    
    // Launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}