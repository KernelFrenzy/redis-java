package com.kernelfrenzy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Starting random redis app");
        System.out.println("You will now have the option to save 3 key/value combinations to redis...");

        List<String> keys = new ArrayList<>();
        System.out.println();

        int counter = 0;
        while (counter < 3) {
            System.out.println("Please enter key " + counter + ": ");
            String key = scanner.nextLine();
            System.out.println("Please enter value " + counter + ": ");
            String value = scanner.nextLine();

            System.out.println();

            System.out.println("Storing key {" + key + "} and value {" + value + "} to redis");
            JedisHelper.getInstance().storeString(key, value);
            keys.add(key);
            counter++;
            System.out.println("Combination stored.");
            if (counter < 3) {
                System.out.println("Moving to next iteration.");
            }

            System.out.println();

        }

        System.out.println("Storing is complete...");
        System.out.println("Retrieving data now...");

        System.out.println();

        for (String key : keys) {
            String value = JedisHelper.getInstance().getString(key);
            System.out.println("Found key : " + key + " with value : " + value);
            System.out.println("Updating expiry for key to 5 seconds");
            System.out.println();
            JedisHelper.getInstance().expireKeyInSeconds(key, 5);
        }

        System.out.println();

        System.out.println("Waiting 5 seconds");
        Thread.sleep(5000);
        System.out.println("Attempting to find stored data");

        System.out.println();

        for (String key : keys) {
            String value = JedisHelper.getInstance().getString(key);
            if (value == null) {
                System.out.println("No value found for key: " + key);
                System.out.println();
            } else {
                System.out.println("Found key : " + key + " with value : " + value);
            }

        }
    }

    private static void printEmptyLines() {
        System.out.println();
    }
}