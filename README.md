# WeatherApp 🌤️

A modern desktop weather application built with JavaFX and the OpenWeather API. WeatherApp provides real-time weather information, detailed forecasts, weather alerts, temperature trends, favorite cities management, and search history tracking in a user-friendly interface.

## Features

### Current Weather

* Current temperature
* Weather condition with OpenWeather icons
* Humidity
* Wind speed
* Feels-like temperature
* Atmospheric pressure
* Visibility
* Cloud coverage
* Sunrise and sunset times

### Forecasting

* 5-Day Weather Summary Forecast
* Detailed Forecast Grid
* Toggle between summary and detailed forecast views
* Weather condition icons and temperatures for each forecast period

### Weather Analytics

* Next 24 Hours Statistics

  * Highest Temperature
  * Lowest Temperature
  * Average Temperature
* Temperature Trend Chart
* Weather Alerts

  * Rain notifications
  * Temperature warnings

### User Convenience

* Favorite Cities
* Search History
* Press Enter to Search
* Temperature Unit Conversion (°C ↔ °F)
* Wind Speed Conversion (m/s ↔ km/h)

## Technologies Used

* Java 25
* JavaFX 26
* OpenWeather API
* JSON (org.json)
* Eclipse IDE

## Screenshots
<img width="883" height="1842" alt="Screenshot 2026-06-06 154723" src="https://github.com/user-attachments/assets/dfca8997-be1b-4eaa-a124-374a878b45c1" />

## Project Structure

```text
WeatherApp
│
├── src
│   └── com.weatherapp
│
├── assets
│   ├── history.txt
│   └── icons
│
├── screenshots
│
├── module-info.java
├── README.md
└── LICENSE
```

## Installation

### Prerequisites

* Java JDK 25 or later
* JavaFX SDK 26.0.1 or later
* Eclipse IDE (recommended)

### Running the Application

1. Clone the repository:

```bash
git clone https://github.com/ProfLekan/WeatherApp.git
```

2. Import the project into Eclipse.

3. Configure JavaFX SDK in the project's build path.

4. Run:

```text
com.weatherapp.Main
```

## API

This project uses the OpenWeather API.

You will need an API key from:

https://openweathermap.org/api

Configure your API key in the application before running.

## Version History

### Version 1.0

* Current Weather Information
* 5-Day Forecast
* Detailed Forecast Grid
* Temperature Trend Chart
* Weather Alerts
* Favorite Cities
* Search History
* Unit Conversions
* OpenWeather Icons

## Planned Features for Version 2.0

* Windows Installer (.exe)
* Improved User Interface Design
* Removable Favorite Cities
* Enhanced Weather Alerts
* Dark Mode
* Weather Maps
* Automatic Location Detection
* Export Weather Reports

## Author

**Olalekan Olatunji**

Built as a JavaFX desktop application project for learning, portfolio development, and practical weather data visualization.

## License

This project is licensed under the MIT License.
