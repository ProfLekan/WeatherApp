package com.weatherapp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads application settings from config.properties.
 *
 * This utility class loads configuration values such as
 * API keys and other application settings.
 */
public class ConfigReader {

    private static final Properties properties =
            new Properties();

    static {

        try {

            properties.load(
                    new FileInputStream(
                            "config.properties"));

        } catch (IOException e) {

            System.err.println(
                    "Could not load config.properties");

            e.printStackTrace();
        }
    }

    /**
     * Retrieves a property value.
     *
     * @param key Property name
     * @return Property value
     */
    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}