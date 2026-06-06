package com.weatherapp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * Handles saving and loading search history.
 * History entries are stored in assets/history.txt.
 */
public class HistoryManager {

    // Path to history file
    private static final String HISTORY_FILE =
            "assets/history.txt";

    // Maximum history records
    private static final int MAX_HISTORY = 100;

    /*
     * Saves a history entry.
     */
    public static void saveHistory(
            String entry) {

        ObservableList<String> history =
                loadHistory();

        // Add newest entry at top
        history.add(0, entry);

        // Trim excess records
        while (history.size()
                > MAX_HISTORY) {

            history.remove(
                    history.size() - 1);
        }

        saveAllHistory(history);
    }

    /*
     * Saves entire history list.
     */
    private static void saveAllHistory(
            ObservableList<String> history) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    HISTORY_FILE));

            for (String item : history) {

                writer.write(item);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Unable to save history.");
        }
    }

    /*
     * Loads history records.
     */
    public static ObservableList<String>
            loadHistory() {

        ObservableList<String> history =
                FXCollections.observableArrayList();

        try {

            File file =
                    new File(HISTORY_FILE);

            if (!file.exists()) {
                return history;
            }

            java.util.Scanner scanner =
                    new java.util.Scanner(file);

            while (scanner.hasNextLine()) {

                history.add(
                        scanner.nextLine());
            }

            scanner.close();

        } catch (Exception e) {

            System.out.println(
                    "Unable to load history.");
        }

        return history;
    }

    /*
     * Clears all history.
     */
    public static void clearHistory() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    HISTORY_FILE));

            writer.write("");

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Unable to clear history.");
        }
    }
}