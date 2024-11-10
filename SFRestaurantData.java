//package project2;
//
//import java.util.Scanner;
//
//public class SFRestaurantData {
//    public static void main(String[] args) {
//        // Implement the main program logic
//    }
//
//    // Implement methods to handle file I/O, user queries, and program control
//}

package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SFRestaurantData {
    public static void main(String[] args) throws FileNotFoundException {
        RestaurantList restaurantList = new RestaurantList();

        try (Scanner scanner = new Scanner(System.in)) {
			String filename;

			// Ask the user for the CSV file input
			System.out.print("Enter the CSV file name: ");
			filename = scanner.nextLine();

			try {
			    loadRestaurantData(filename, restaurantList);

			    boolean exit = false;

			    while (!exit) {
			        System.out.println("Welcome to SF Restaurant Data!");
			        System.out.println("To search for matching restaurant names, enter 'name KEYWORD'");
			        System.out.println("To search for restaurants in a zip code, enter 'zip KEYWORD'");
			        System.out.println("To finish the program, enter 'quit'");
			        System.out.print("Enter your search query: ");

			        String query = scanner.nextLine();
			        String[] parts = query.split(" ");

			        if (parts.length > 0) {
			            String command = parts[0].toLowerCase();
			            String keyword = parts.length > 1 ? parts[1] : "";

			            switch (command) {
			                case "name":
			                    searchByName(restaurantList, keyword);
			                    break;
			                case "zip":
			                    searchByZip(restaurantList, keyword);
			                    break;
			                case "quit":
			                    exit = true;
			                    break;
			                default:
			                    System.out.println("Invalid command. Please follow the format 'name KEYWORD', 'zip KEYWORD', or 'quit'.");
			            }
			        }
			    }
			} catch (IOException e) {
			    System.err.println("An error occurred while reading the restaurant data: " + e.getMessage());
			} catch (IllegalArgumentException e) {
			    System.err.println("Invalid data format in the restaurant data file: " + e.getMessage());
			}
		}
    }

    private static void loadRestaurantData(String filename, RestaurantList restaurantList) throws IOException, FileNotFoundException {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Restaurant restaurant = Restaurant.fromString(line);
                if (restaurant != null) {
                    restaurantList.add(restaurant);
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    private static void searchByName(RestaurantList restaurantList, String keyword) {
        RestaurantList matchingRestaurants = restaurantList.getMatchingRestaurants(keyword);
        if (matchingRestaurants == null) {
            System.out.println("No matching restaurants found for keyword: " + keyword);
        } else {
            System.out.println("Matching Restaurants:");
            System.out.println(matchingRestaurants);
        }
    }

    private static void searchByZip(RestaurantList restaurantList, String keyword) {
        RestaurantList matchingRestaurants = restaurantList.getMatchingZip(keyword);
        if (matchingRestaurants == null) {
            System.out.println("No restaurants found for the given zip code: " + keyword);
        } else {
            System.out.println("Restaurants in Zip Code " + keyword + ":");
            System.out.println(matchingRestaurants);
        }
    }
}

