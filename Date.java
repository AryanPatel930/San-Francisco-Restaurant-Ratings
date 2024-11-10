//package project2;
//
//public class Date implements Comparable<Date>{
//    private int day;
//    private int month;
//    private int year;
//
//    public Date(String date) throws IllegalArgumentException {
//        if (date == null) {
//            throw new IllegalArgumentException("Date passed is null");
//        }
//
//        try {
//            String[] dateSplit = date.split("/");
//            if (dateSplit.length != 3) {
//                throw new IllegalArgumentException("Bad date format");
//            }
//            
//            setMonth(Integer.parseInt(dateSplit[0]));
//            setDay(Integer.parseInt(dateSplit[1]));
//            setYear(Integer.parseInt(dateSplit[2]));
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("Month, day, and year are not integers or bad format");
//        }
//    }
//
//    public Date(int month, int day, int year) throws IllegalArgumentException {
//        if (!isValidDate(month, day)) {
//            throw new IllegalArgumentException("Invalid date");
//        }
//
//        this.setMonth(month);
//        this.setDay(day);
//        this.setYear(year);
//    }
//    
//    private boolean isLeapYear(int year) {
//    	if(year % 4 == 0 && year % 100 == 0) { return (year % 400 == 0);}
//    	if (year % 100 != 0) {return true;}
//    	else {
//    		return false;
//    	}
//    	
//    }
//
//    
//    @Override
//    public int compareTo(Date otherDate) {
//        // Compare the dates and return the result
//    	if(this.year < otherDate.year) { return -1; }
//    	else if(this.year > otherDate.year) { return 1;}
//    	
//    	else {
//    		if(this.month < otherDate.month) { return -1; }
//        	else if(this.month > otherDate.month) { return 1;}
//        	else {
//        		if(this.day < otherDate.day) { return -1; }
//            	else if(this.day > otherDate.day) { return 1;}
//            	else { return 0;}
//        	}
//    	}
//    }
//
//
//    @Override
//    public String toString() {
//        // Format and return the date as a string
//    	String dateStr = "";
//        if (month < 10) {
//            dateStr += "0";
//        }
//        dateStr += month + "/";
//        if (day < 10) {
//            dateStr += "0";
//        }
//        dateStr += day + "/";
//        if (year < 1000) {
//            dateStr += "0";
//        }
//        if (year < 100) {
//            dateStr += "0";
//        }
//        if (year < 10) {
//            dateStr += "0";
//        }
//        dateStr += year;
//        return dateStr;
//    }
//    
//    // Helper method to check if a date is valid
//    private boolean isValidDate(int month, int day) {
//        if (month < 1 || month > 12) {
//            return false;
//        }
//        if (day < 1 || day > 31) {
//            return false;
//        }
//        if (month == 2 && day > 29) {
//            return false;
//        }
//        if (month == 2 && day == 29 && !isLeapYear(year)) {
//            return false;
//        }
//        return true;
//    }
//
//	public int getDay() {
//		return day;
//	}
//
//	public void setDay(int day) {
//		this.day = day;
//	}
//
//	public int getMonth() {
//		return month;
//	}
//
//	public void setMonth(int month) {
//		this.month = month;
//	}
//
//	public int getYear() {
//		return year;
//	}
//
//	public void setYear(int year) {
//		this.year = year;
//	}
//}


package project2;

public class Date implements Comparable<Date> {
    private int month;
    private int day;
    private int year;

    public Date(String date) {
        if (date == null || !date.matches("\\d{2}/\\d{2}/\\d{2,4}")) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }

        String[] parts = date.split("/");
        int mm = Integer.parseInt(parts[0]);
        int dd = Integer.parseInt(parts[1]);
        int yy = Integer.parseInt(parts[2]);

        if (mm < 1 || mm > 12) {
            throw new IllegalArgumentException("Invalid month: " + mm);
        }
        if (dd < 1 || dd > getMaxDayInMonth(mm, yy)) {
            throw new IllegalArgumentException("Invalid day: " + dd);
        }
        if (yy < 2000 || yy > 2025) {
            throw new IllegalArgumentException("Invalid year: " + yy);
        }

        this.month = mm;
        this.day = dd;
        this.year = (yy < 100) ? 2000 + yy : yy;
    }

    

    public Date(int month, int day, int year) {
        if (month < 1 || month > 12 || day < 1 || day > getMaxDayInMonth(month, year) || year < 2000 || year > 2025) {
            throw new IllegalArgumentException("Invalid date values: " + month + "/" + day + "/" + year);
        }

        this.month = month;
        this.day = day;
        this.year = year;
    }

    private int getMaxDayInMonth(int month, int year) {
        int maxDays;
        if (month == 2) {
            maxDays = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        } else {
            maxDays = 31;
        }
        return maxDays;
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        } else if (this.month != other.month) {
            return this.month - other.month;
        } else {
            return this.day - other.day;
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }
}
