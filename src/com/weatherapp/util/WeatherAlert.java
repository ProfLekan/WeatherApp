package com.weatherapp.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.weatherapp.model.ForecastEntry;

/*
 * Generates useful weather alerts
 * from forecast data.
 */
public class WeatherAlert {

    public static List<String> generateAlerts(
            List<ForecastEntry> entries) {

        List<String> alerts =
                new ArrayList<>();

        if (entries == null
                || entries.isEmpty()) {

            alerts.add(
                    "No forecast available");

            return alerts;
        }

        // Rain alert
        for (ForecastEntry entry : entries) {

            if ("Rain".equalsIgnoreCase(
                    entry.getCondition())) {

                alerts.add(
                        "⚠ Expect rain around "
                        + formatPeriod(entry));

                break;
            }
        }

        // Thunderstorm alert
        for (ForecastEntry entry : entries) {

            if ("Thunderstorm"
                    .equalsIgnoreCase(
                            entry.getCondition())) {

                alerts.add(
                        "⚠ Thunderstorm likely around "
                        + formatPeriod(entry));

                break;
            }
        }

        // High temperature alert
        for (ForecastEntry entry : entries) {

            if (entry.getTemperature()
                    >= 35) {

                alerts.add(
                        "⚠ High temperature ("
                        + Math.round(
                                entry.getTemperature())
                        + "°C) expected around "
                        + formatPeriod(entry));

                break;
            }
        }

        // Low temperature alert
        for (ForecastEntry entry : entries) {

            if (entry.getTemperature()
                    <= 10) {

                alerts.add(
                        "⚠ Low temperature ("
                        + Math.round(
                                entry.getTemperature())
                        + "°C) expected around "
                        + formatPeriod(entry));

                break;
            }
        }

        // No alerts found
        if (alerts.isEmpty()) {

            alerts.add(
                    "✓ No significant weather alerts");
        }

        return alerts;
    }

    /*
     * Converts forecast date/time into
     * a user-friendly format.
     *
     * Examples:
     * 6am today
     * 3pm tomorrow
     * Sat 12pm
     */
    private static String formatPeriod(
            ForecastEntry entry) {

        try {

            LocalDateTime dateTime =
                    LocalDateTime.parse(
                            entry.getDay()
                            + "T"
                            + entry.getTime());

            LocalDate today =
                    LocalDate.now();

            String dayText;

            if (dateTime.toLocalDate()
                    .equals(today)) {

                dayText = "today";

            } else if (dateTime.toLocalDate()
                    .equals(
                            today.plusDays(1))) {

                dayText = "tomorrow";

            } else {

                dayText =
                        dateTime
                                .getDayOfWeek()
                                .toString()
                                .substring(0, 1)
                        +
                        dateTime
                                .getDayOfWeek()
                                .toString()
                                .substring(1, 3)
                                .toLowerCase();
            }

            String timeText =
                    dateTime.toLocalTime()
                            .format(
                                    DateTimeFormatter
                                            .ofPattern(
                                                    "ha"))
                            .toLowerCase();

            return timeText
                    + " "
                    + dayText;

        } catch (Exception e) {

            return "later";
        }
    }
}