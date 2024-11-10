//package project2;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//
//public class RestaurantList extends ArrayList<Restaurant> {
//	private Object[] list;
//	
//	private int size;
//	private int capacity;
//	
//	private final int INITIAL_CAPACITY = 10; 
//	
//	public void MyArrayList() {
//		list = new Object [ INITIAL_CAPACITY];
//		size = 0;
//		capacity = INITIAL_CAPACITY;
//	}
//	
//    // Default constructor to create an empty list
//	ArrayList<RestaurantList> studentSchedule = new ArrayList<RestaurantList>();
//    // retrieves restaurants with names containing a keyword
//	
//    public RestaurantList getMatchingRestaurants(String keyword) {
//        // Implement search logic and return a filtered list
//    	RestaurantList matchingRestaurants = new RestaurantList();
//    	
//    	for (int i = 0; i < this.size(); i++) {
//    	    Restaurant restaurant = this.get(i);
//    	    if (restaurant.getName().toLowerCase().contains(keyword.toLowerCase())) {
//    	        matchingRestaurants.add(restaurant);
//    	    }
//    	}
//    }
//
//    // retrieves restaurants in a specific zip code
//    public RestaurantList getMatchingZip(String zipCode) {
//        // Implement search logic and return a filtered list
//    	RestaurantList matchingRestaurants = new RestaurantList();
//    	
//    }
//
//    // Override toString to display a list of restaurant names
//    @Override
//    public String toString() {
//    	return RestaurantList.toString(RestaurantList.indexOfRange(list, 0, size) );// Format and return a list of restaurant names
//    }
//
//    // You may implement other methods if needed
//}

//package project2;
//
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@SuppressWarnings("serial")
//public class RestaurantList extends ArrayList<Restaurant> {
//    public RestaurantList() {
//        super();
//    }
//
//    public RestaurantList getMatchingRestaurants(String keyword) {
//        return this.stream()
//                .filter(restaurant -> restaurant.getName().toLowerCase().contains(keyword.toLowerCase()))
//                .sorted()
//                .collect(Collectors.toCollection(RestaurantList::new));
//    }
//
//    public RestaurantList getMatchingZip(String keyword) {
//        return this.stream()
//                .filter(restaurant -> keyword.equals(restaurant.getZipCode()))
//                .sorted()
//                .collect(Collectors.toCollection(RestaurantList::new));
//    }
//
//    @Override
//    public String toString() {
//        return this.stream()
//                .map(Restaurant::getName)
//                .collect(Collectors.joining("; "));
//    }
//}


package project2;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class RestaurantList extends ArrayList<Restaurant> {
    public RestaurantList() {
        super();
    }

    public RestaurantList getMatchingRestaurants(String keyword) {
        RestaurantList matchingRestaurants = new RestaurantList();
        if (keyword == null || keyword.isEmpty()) {
            System.out.println("No keyword provided for restaurant name search.");
            return matchingRestaurants;
        }

        for (Restaurant restaurant : this) {
            if (restaurant.getName().equalsIgnoreCase(keyword)) {
                matchingRestaurants.add(restaurant);
            }
        }

        if (matchingRestaurants.isEmpty()) {
            System.out.println("No matching restaurants found.");
            return null;
        }

        matchingRestaurants.sort(null);
        return matchingRestaurants;
    }

    public RestaurantList getMatchingZip(String keyword) {
        RestaurantList matchingRestaurants = new RestaurantList();
        if (keyword == null || keyword.isEmpty()) {
            System.out.println("No keyword provided for zip code search.");
            return matchingRestaurants;
        }

        for (Restaurant restaurant : this) {
            if (restaurant.getZipCode().equalsIgnoreCase(keyword)) {
                matchingRestaurants.add(restaurant);
            }
        }

        if (matchingRestaurants.isEmpty()) {
            System.out.println("No matching restaurants found.");
            return null;
        }

        matchingRestaurants.sort(null);
        return matchingRestaurants;
    }




    public void inspectionsSort() {
    	for(Restaurant restaurant : this) {
    		restaurant.inspectionsSort();
    	}
    }
    

    
    @Override
    public String toString() {
        return this.stream().map(Restaurant::getName).toList().toString();
    }

}
