package com.weatherapp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * Handles saving and loading favorite cities.
 */
public class FavoritesManager {

    private static final String FAVORITES_FILE =
            "assets/favorites.txt";

    private static final int MAX_FAVORITES = 50;

    /*
     * Loads favorite cities.
     */
    public static ObservableList<String>
            loadFavorites() {

        ObservableList<String> favorites =
                FXCollections.observableArrayList();

        try {

            File file =
                    new File(FAVORITES_FILE);

            if (!file.exists()) {
                return favorites;
            }

            java.util.Scanner scanner =
                    new java.util.Scanner(file);

            while (scanner.hasNextLine()) {

                String city =
                        scanner.nextLine()
                                .trim();

                if (!city.isEmpty()) {

                    favorites.add(city);
                }
            }

            scanner.close();

        } catch (Exception e) {

            System.out.println(
                    "Unable to load favorites.");
        }

        return favorites;
    }

    /*
     * Saves favorite cities.
     */
    public static void saveFavorites(
            ObservableList<String> favorites) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    FAVORITES_FILE));

            int count = 0;

            for (String city : favorites) {

                if (count >= MAX_FAVORITES) {
                    break;
                }

                writer.write(city);
                writer.newLine();

                count++;
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Unable to save favorites.");
        }
    }

    /*
     * Adds a city to favorites.
     */
    public static void addFavorite(
            String city,
            ObservableList<String> favorites) {

        city = city.trim();

        if (city.isEmpty()) {
            return;
        }

        if (!favorites.contains(city)) {

            favorites.add(city);

            saveFavorites(favorites);
        }
    }

    /*
     * Removes a city from favorites.
     */
    public static void removeFavorite(
            String city,
            ObservableList<String> favorites) {

        favorites.remove(city);

        saveFavorites(favorites);
    }
}