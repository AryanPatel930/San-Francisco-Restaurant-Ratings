//package project2;
//
//import java.util.ArrayList;
//
//public class Restaurant implements Comparable<Restaurant> {
//    private String name;
//    private String address;
//    private String phone;
//    private String zip;
//    private ArrayList<Inspection> inspections;
//
//    public Restaurant(String name, String zip) {
//        // Constructor with name and zip
//        // Initialize the fields
//    	this.name = name;
//    	this.zip = zip;
//    	this.address = "";
//    	this.phone = "";
//    	this.inspections = new ArrayList<Inspection>();
//    }
//
//    public Restaurant(String name, String zip, String address, String phone) {
//        // Constructor with all details
//        // Initialize the fields
//    	this.name = name;
//    	this.zip = zip;
//    	this.address = address;
//    	this.phone = phone;
//    	this.inspections = new ArrayList<Inspection>();
//    }
//
//    public void addInspection(Inspection inspect) {
//        // Add an inspection to the list
//        // Check for null and add to the list
//    	if(! inspect.equals(null)) {
//    		Inspection.add(inspect);
//    	}
//    }
//
//    @Override
//    public int compareTo(Restaurant otherRestaurant) {
//        // Compare Restaurant objects based on name and zip
//        // Implement compareTo for sorting
////    	int nameComparison = this.name.compareToIgnoreCase(otherRestaurant.name);
////    	if (nameComparison != 0) {
////            // If the names are different, return the result of the name comparison
////            return nameComparison;
////        } else {
////            // If names are the same, compare by zip
////            return this.zip.compareTo(otherRestaurant.zip);
////        }
//    	if(this.name < otherRestaurant.name) { return -1; }
//    	else if(this.name > otherRestaurant.name) { return 1;}
//    	else {
//    		if(this.zip < otherRestaurant.zip) { return -1; }
//        	else if(this.zip > otherRestaurant.zip) { return 1;}
//            else { return 0;}
//        	}
//    	}
//    
//
//    @Override
//    public boolean equals(Object obj) {
//        // Compare Restaurant objects based on name and zip
//        // Implement equals for object comparison
//    	if(this.equals(obj)) {return true;}
//    	if(obj.equals(null) || getClass() != this.getClass()) {return false;}
//    	
//    	Restaurant other = (Restaurant) obj;
//    	return name.equalsIgnoreCase(other.name) && zip.equals(other.zip);
//    }
//
//    @Override
//    public String toString() {
//        // Return a string representation of the Restaurant
//        // Include details about the two most recent inspections
////    	for (int i = 0; i < Inspection.length(); i++) {
////    		if(Inspection[i])
////    	}
////    	String restaurantStr = "";
////        if (month < 10) {
////            dateStr += "0";
////        }
////        dateStr += month + "/";
////        if (day < 10) {
////            dateStr += "0";
////        }
////        dateStr += day + "/";
////        if (year < 1000) {
////            dateStr += "0";
////        }
////        if (year < 100) {
////            dateStr += "0";
////        }
////        if (year < 10) {
////            dateStr += "0";
////        }
////        dateStr += year;
////        return dateStr;
//    }
//}

package project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant implements Comparable<Restaurant> {
    private String name;
    private String address;
    private String phoneNumber;
    private String zipCode;
    private List<Inspection> inspections;

    public Restaurant(String name, String zip) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty.");
        }
        if (zip == null || zip.length() != 5) {
            throw new IllegalArgumentException("Zip code must be a string containing exactly five digits.");
        }

        this.name = name;
        this.zipCode = zip;
        this.inspections = new ArrayList<>();
    }

    public Restaurant(String name, String zip, String address, String phone) {
        this(name, zip);

        this.address = (address != null && !address.isEmpty()) ? address : null;
        this.phoneNumber = (phone != null && !phone.isEmpty()) ? phone : null;
    }

    public void addInspection(Inspection inspect) {
        if (inspect == null) {
            throw new IllegalArgumentException("Inspection cannot be null.");
        }
        inspections.add(inspect);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    @Override
    public int compareTo(Restaurant other) {
        int nameComparison = this.name.compareToIgnoreCase(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.zipCode.compareTo(other.zipCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant restaurant = (Restaurant) o;

        if (name != null ? !name.equalsIgnoreCase(restaurant.name) : restaurant.name != null) return false;
        return zipCode != null ? zipCode.equals(restaurant.zipCode) : restaurant.zipCode == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.toLowerCase().hashCode() : 0;
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        Collections.sort(inspections, Collections.reverseOrder());

        String result = "Name: " + name + ", Address: " + (address != null ? address : "") + ", Phone: " + (phoneNumber != null ? phoneNumber : "") + ", Zip: " + zipCode + ", Recent Inspections: ";

        int count = 0;
        for (Inspection inspection : inspections) {
            if (inspection.getScore() >= 0) {
                result += inspection;
                count++;
                if (count >= 2) {
                    break;
                }
            }
        }

        return result;
    }

    public static Restaurant fromString(String line) {
        String[] parts = line.split("; "); 

        if (parts.length < 2) {
            return null;
        }

        String name = parts[0];
        String zip = parts[1];

        String address = null;
        String phone = null;

        if (parts.length > 2) {
            address = parts[2];
        }

        if (parts.length > 3) {
            phone = parts[3];
        }

        return new Restaurant(name, zip, address, phone);
    }

	public void inspectionsSort() {
		Collections.sort(inspections, Collections.reverseOrder());
	}
}

