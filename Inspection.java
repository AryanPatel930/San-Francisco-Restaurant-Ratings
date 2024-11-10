//
//public class Inspection {
//	/*The Inspection class is used to represent the particular inspection of a restaurant. It should store the date of the inspection, the
//assigned score, the violation description and the risk category. The class should provide a four parameter constructor
//public Inspection (Date date, int score, String violation, String risk)
//The constructor should throw an instance of IllegalArgumentException if
//• it is called with null value for date;
//• it is called with the score outside of the valid range of 0 to 100 (inclusive).
//The two string parameters are allowed to be null or empty (this will happen when there is no violation description and/or risk 
//assesments for the given inspection, i.e., the corresponding column in the data set is empty).
//*/
//
//	private Date date;
//	private int score;
//	private String risk;
//	private String violation;
//	
//	public Inspection(Date date, int score, String violation, String risk) {
//		if(date == null) { throw new IllegalArgumentException("There must be a date"); }
//		
//		if(score < 0 || score > 100) { throw new IllegalArgumentException("Score must be from 0 to 100"); }
//		
//		this.date = date;
//		this.score = score;
//
//		if (violation != null) {
//		    this.violation = violation;
//		} else {
//		    this.violation = "";
//		}
//
//		if (risk != null) {
//		    this.risk = risk;
//		} else {
//		    this.risk = "";
//		}
//
//	}
//}

package project2;

public class Inspection {
    private Date date;
    private int score;
    private String violationDescription;
    private String riskCategory;

    public Inspection(Date date, int score, String violation, String risk) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be in the range of 0 to 100 (inclusive).");
        }

        this.date = date;
        this.score = score;
        this.violationDescription = (violation != null && !violation.isEmpty()) ? violation : null;
        this.riskCategory = (risk != null && !risk.isEmpty()) ? risk : null;
    }

    public Date getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    public String getViolationDescription() {
        return violationDescription;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    @Override
    public String toString() {
        String result = "Date: " + date + ", Score: " + score;
        if (violationDescription != null) {
            result += ", Violation Description: " + violationDescription;
        } else {
            result += ", Violation Description: N/A";
        }
        if (riskCategory != null) {
            result += ", Risk Category: " + riskCategory;
        } else {
            result += ", Risk Category: N/A";
        }
        return result;
    }

}
