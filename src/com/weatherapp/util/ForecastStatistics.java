package com.weatherapp.util;

import java.util.List;

import com.weatherapp.model.ForecastEntry;

/*
 * Calculates statistics for the
 * next 24 hours.
 */
public class ForecastStatistics {

    private double highestTemp;
    private double lowestTemp;
    private double averageTemp;

    private String highestTempTime;
    private String lowestTempTime;

    public ForecastStatistics(
            List<ForecastEntry> entries) {

        calculate(entries);
    }

    private void calculate(
            List<ForecastEntry> entries) {

        if (entries == null
                || entries.isEmpty()) {

            return;
        }

        int limit =
                Math.min(
                        8,
                        entries.size());

        highestTemp =
                entries.get(0)
                        .getTemperature();

        lowestTemp =
                entries.get(0)
                        .getTemperature();

        highestTempTime =
                formatTime(
                        entries.get(0)
                                .getTime());

        lowestTempTime =
                formatTime(
                        entries.get(0)
                                .getTime());

        double totalTemp = 0;

        for (int i = 0;
                i < limit;
                i++) {

            ForecastEntry entry =
                    entries.get(i);

            double temp =
                    entry.getTemperature();

            totalTemp += temp;

            if (temp > highestTemp) {

                highestTemp = temp;

                highestTempTime =
                        formatTime(
                                entry.getTime());
            }

            if (temp < lowestTemp) {

                lowestTemp = temp;

                lowestTempTime =
                        formatTime(
                                entry.getTime());
            }
        }

        averageTemp =
                totalTemp / limit;
    }

    /*
     * Converts 24-hour time
     * into am/pm format.
     *
     * Examples:
     * 00:00:00 -> 12am
     * 03:00:00 -> 3am
     * 12:00:00 -> 12pm
     * 21:00:00 -> 9pm
     */
    private String formatTime(
            String time) {

        try {

            int hour =
                    Integer.parseInt(
                            time.substring(0, 2));

            if (hour == 0) {
                return "12am";
            }

            if (hour < 12) {
                return hour + "am";
            }

            if (hour == 12) {
                return "12pm";
            }

            return (hour - 12)
                    + "pm";

        } catch (Exception e) {

            return "--";
        }
    }

    public double getHighestTemp() {
        return highestTemp;
    }

    public double getLowestTemp() {
        return lowestTemp;
    }

    public double getAverageTemp() {
        return averageTemp;
    }

    public String getHighestTempTime() {
        return highestTempTime;
    }

    public String getLowestTempTime() {
        return lowestTempTime;
    }
}