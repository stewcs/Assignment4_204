import java.util.ArrayList;
import java.util.LinkedList;
import java.io.IOException; 

/**
 * This class defines CourseDBStructure, a class that implements a hash table with buckets to hold CourseDBElements.
 * @author Daniel Xu
 * @version 4/04/2023
 */

public class CourseDBStructure implements CourseDBStructureInterface{

	protected int capacity; // max capacity
	protected ArrayList <LinkedList<CourseDBElement>> table;
	protected final double LOAD_FACTOR = 1.5; // load factor

	
	// Default constructor
	public CourseDBStructure() {
		this.capacity = 99;
		table = new ArrayList <> (99);
		
		// Fill the hash table with empty linked list buckets
		for(int i = 0; i < capacity; i ++)
			table.add(new LinkedList<CourseDBElement>());
	}
	
	// Create a hash table with a calculated size
	public CourseDBStructure(int numOfCourses) {
		this.capacity = fourKPlus3((numOfCourses));
		table = new ArrayList<>(); 
		
		// Fill the hash table with empty linked list buckets
		for(int i = 0; i < capacity; i++) {
			table.add(new LinkedList<CourseDBElement>());
		}
	}

	// testing constructor
	public CourseDBStructure(String testing,int capacity) {
		this.capacity = capacity;
		table = new ArrayList<>();
		
		// Fill the hash table with empty linked list buckets
		for(int i = 0; i < capacity; i++) {
			table.add(new LinkedList<CourseDBElement>());
		}
	}

	
	/**
	 * Adds a new element to the hash table
	 * @param element
	 */
	@Override
	public void add(CourseDBElement element) {	
		int index = (element.hashCode() % capacity);
		
		LinkedList<CourseDBElement> temp = table.get(index);

		for(int i = 0; i < temp.size(); i ++) {
			if(temp.get(i).compareTo(element) == 0) {
				temp.set(i, element);
				return;
			}
		}
	   
	    temp.add(element);
	}
	
    /**
     * Gets an element from the hash table using a CRN
     * @param crn
     * @return element
     */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		String key = Integer.toString(crn); // Use the CRN as the key
		int index = (key.hashCode() % capacity); // Convert key to int index.
		
		
		LinkedList<CourseDBElement> list = table.get(index);
		if (list != null) {
			for (CourseDBElement element : list) {
				if (element.getCRN() == crn) {
					return element;
				}
			}
		}
			throw new IOException();
	}

	
	/**
	 * an array list of string representation of each course in the data structure separated by a new line. 
	 * @return courses | array list of strings.
	 */
	@Override
	public ArrayList<String> showAll() {
		// Create an array list of String's to hold the result
		ArrayList<String> courses = new ArrayList<String>();

		// Loop through the hash table and add each element's data to the array list.
		for (int i = 0; i < capacity; i++) {
				for (int j = 0; j < table.get(i).size(); j++) {
					// Add the element data to array list
					courses.add(table.get(i).get(j).toString());
				}
		}
		return courses;
	}

	/**
	 * Returns table capacity
	 * @return capacity of table
	 */
	@Override
	public int getTableSize() {
		return capacity;
	}


	/**
	 * method to get the next 4k+3 prime, taken from class powerpoint
	 * @param n | the result of dividing number of courses by load factor
	 * @return var | 4k+3 prime
	 */
public int fourKPlus3(int n){  
		
		boolean fkp3 = false;
		boolean aPrime = false;
		int prime, highDivisor, d;
		
		prime = (int)(n/LOAD_FACTOR);
		
		if(prime % 2 == 0) // if even make odd
			prime = prime +1;

		while(fkp3 == false) // not a 4k+3 prime
		{  
			while(aPrime == false) // not a prime
			{  
				highDivisor = (int)(Math.sqrt(prime) + 0.5);
				
				for(d = highDivisor; d > 1; d--)
					if(prime % d == 0)
						break; // not a prime
				
				if(d != 1) // prime not found
					prime = prime + 2;
				else
					aPrime = true;
				
			} // end of the prime search loop
			
			if((prime - 3) % 4 == 0)
				fkp3 = true;
			else
			{  
				prime = prime + 2;
				aPrime = false;
			}
			
		} // end of 4k+3 prime search loop
		return prime;
	}

}