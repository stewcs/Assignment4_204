import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class defines CourseDBManager. This class provides methods to read courses from a file.
 * @author Daniel Xu
 * @version 4/04/2023
 */

public class CourseDBManager implements CourseDBManagerInterface {

	private CourseDBStructure cds;

	public CourseDBManager() {
		cds = new CourseDBStructure();
	}


	/**
	 * Adds a course (CourseDBElement) with the given information to CourseDBStructure.
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement cdetest = new CourseDBElement(id, crn, credits, roomNum, instructor);
		cds.add(cdetest);
	}


	/**
	 * finds CourseDBElement based on the crn key
	 * @param crn
	 * @return CourseDBElement
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return cds.get(crn);
		} catch (java.io.IOException e) {

			e.printStackTrace();
		}
		return null;
	}


	/**
	 * Reads the information of courses from a test file and adds them to the CourseDBStructure data structure
	 * @param input
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {

		Scanner scanner = new Scanner(input);

		//Loop through the file
		while(scanner.hasNextLine()) {

			// Extract data from string.
			// Order: CourseID, CRN, Credits, Instructor, Room.
			String courses = scanner.nextLine(); // Store next line
			String[] courseArray = courses.split("\\s+", 5); // split the line into 5 substrings
			int CRN = Integer.parseInt(courseArray[1]); // parse int to CRN
			int credits = Integer.parseInt(courseArray[2]); // parse int to credits
			CourseDBElement cde = new CourseDBElement(courseArray[0], CRN, credits, courseArray[3], courseArray[4]); // put in element
			cds.add(cde); // add to structure
		}

		scanner.close();
	}


	/**
	 * an array list of string representation of each course in the data structure separated by a new line.
	 * @return ArrayList
	 */
	@Override
	public ArrayList<String> showAll() {
		return cds.showAll();
	}

}
