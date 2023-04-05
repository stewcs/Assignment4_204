

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * @author Daniel Xu
 * version 4/03/2023
 * 
 */
public class CourseDBManagerStudentTest {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}
	
	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("MATH182",	30604,4,"SC360","Paul E. Duty");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("MATH182",	30604,4,"SC360","Paul E. Duty");
		dataMgr.add("MATH182",	30620,4,"SC260","Franklin Gavilanez");
		dataMgr.add("MATH181",	30608,4,"SC460","What is your name");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:MATH182 CRN:30604 Credits:4 Instructor:Paul E. Duty Room:SC360");
	 	assertEquals(list.get(1),"\nCourse:MATH182 CRN:30608 Credits:4 Instructor:What is your name Room:SC460");
		assertEquals(list.get(2),"\nCourse:MATH181 CRN:30608 Credits:4 Instructor:What is your name Room:SC460");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("MATH182 30504 4 SC360 Paul E. Duty");
			inFile.print("MATH182 30503 4 SC460 What is your name");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			assertEquals("MATH182",dataMgr.get(30504).getID());
			assertEquals("MATH182",dataMgr.get(30503).getID());
			assertEquals("SC360",dataMgr.get(30504).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}

