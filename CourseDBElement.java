
/**
 * This class defines a CourseDBElement with 5 attributes, and provides some utility methods.
 * @author Daniel Xu
 * @version 4/04/2023
 */

public class CourseDBElement implements Comparable<CourseDBElement> {

	private String courseID;
	private int crn;
	private int numberOfCredits;
	private String roomNumber;
	private String instructorName;
    

	/**
	 * Constructor
	 * @param id - course ID
	 * @param crn - Course number 
	 * @param credits - number of credits for the course
	 * @param roomNumber -  classroom for the course
	 * @param instructor - name of instructor for the course
	 */
	public CourseDBElement(String id, int crn, int credits, String roomNumber, String instructor) {
		this.courseID = id;
		this.crn = crn;
		this.numberOfCredits = credits;
		this.roomNumber = roomNumber;
		this.instructorName = instructor;
	}
	
	
	@Override
	public int hashCode() {
	    return String.valueOf(crn).hashCode();
	}
	
    public String getID() {
        return courseID;
    }

    public int getCRN() {
        return this.crn;
    }

    public int getCredits() {
        return numberOfCredits;
    }

    public String getRoomNum() {
        return roomNumber;
    }

    public String getInstructorName() {
        return instructorName;
    }
    
    @Override
    public int compareTo(CourseDBElement other) {
        return this.crn - other.getCRN();
        		
    }
    
    @Override
    public String toString() {
    	return ("\nCourse:" + courseID + " CRN:" + crn + " Credits:" + numberOfCredits 
    			+ " Instructor:" + instructorName + " Room:" + roomNumber);
    }
	
}