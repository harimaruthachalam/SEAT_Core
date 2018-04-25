package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import models.Course;
import models.Slot;
import models.Student;
import models.StudentPreference;

import org.junit.Test;

public class StudentPreferenceClassTests {

	@Test //Used to test the models.StudentPreference.setPreferenceNumberToPositionInPreferenceList method
	public void setPreferenceNumberToPositionInPreferenceListTest() { 
		//Create 1 student 3 new course objects. We only care about the name of the course/student. Put random values for everything else
		Student s1 = new Student ("cs13b031",1,30);
		Course c1 = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1300",false,null);
		
		/*
		 * Create student preference objects corresponding to these courses 
		 * (setting their preference number to 0 initially. 
				The function being tested needs to calculate it correctly)
		 */
		StudentPreference sp1 = new StudentPreference(c1, 1, 0);
		StudentPreference sp2 = new StudentPreference(c2, 1, 0);
		StudentPreference sp3 = new StudentPreference(c3, 1, 0);
		
		//Add the student preference objects to the preference list of s1 and s2
		s1.studentPreferenceList.add(sp1);
		s1.studentPreferenceList.add(sp2);
		s1.studentPreferenceList.add(sp3);
		
		//Calculate the correct preference numbers for sp1,sp2 and sp3
		sp1.setPreferenceNumberToPositionInPreferenceList(s1.studentPreferenceList);
		sp3.setPreferenceNumberToPositionInPreferenceList(s1.studentPreferenceList);
		sp2.setPreferenceNumberToPositionInPreferenceList(s1.studentPreferenceList);
		
		//Check if the function calculated them correctly
		assertEquals(1,sp1.getPreferenceNo());
		assertEquals(2,sp2.getPreferenceNo());
		assertEquals(3,sp3.getPreferenceNo());
	}
	
	
	@Test //Used to test the models.StudentPreference.getStudentPreferenceBycourseNumber method
	public void getStudentPreferenceBycourseNumberTest() { 
		//Create 1 student 3 new course objects. We only care about the name of the course/student. Put random values for everything else
		Student s1 = new Student ("cs13b031",1,30);
		Course c1 = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1300",false,null);
		
		//Create student preference objects corresponding to these courses. We only care about the course object. Put any random value for everything else
		StudentPreference sp1 = new StudentPreference(c1, 1, 0);
		StudentPreference sp2 = new StudentPreference(c2, 1, 0);
		StudentPreference sp3 = new StudentPreference(c3, 1, 0);
		
		//Add the student preference objects to the preference list of s1
		s1.studentPreferenceList.add(sp1);
		s1.studentPreferenceList.add(sp2);
		s1.studentPreferenceList.add(sp3);
		
		//Making some queries
		StudentPreference result1 = StudentPreference.getStudentPreferenceBycourseNumber(s1.studentPreferenceList, "cs1100$outside");
		StudentPreference result2 = StudentPreference.getStudentPreferenceBycourseNumber(s1.studentPreferenceList, "cs110");
		StudentPreference result3 = StudentPreference.getStudentPreferenceBycourseNumber(s1.studentPreferenceList, "cs1100$inside");
		
		//Checking if we get the correct results for the queries
		assertEquals(sp1,result1);
		assertEquals(null,result2);
		assertEquals(null,result3);
		
	}


	@Test //Used to test the models.StudentPreference.compareTo method
	public void compareToTest() { 
		//Create 1 student 3 new course objects. We only care about the name of the course/student. Put random values for everything else
		Student s1 = new Student ("cs13b031",1,30);
		Course c1 = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1300",false,null);
		
		/*
		 * Create student preference objects corresponding to these courses.
		 * Giving them some preference numbers (3rd argument is preference number)
		 */
		StudentPreference sp1 = new StudentPreference(c1, 1, 4);
		StudentPreference sp2 = new StudentPreference(c2, 1, 1);
		StudentPreference sp3 = new StudentPreference(c3, 1, 2);
		
		//Add the student preference objects to the preference list of s1 and s2
		s1.studentPreferenceList.add(sp1);
		s1.studentPreferenceList.add(sp2);
		s1.studentPreferenceList.add(sp3);
		
		//Sort the student preference list (compareTo function will dictate how to sort)
		Collections.sort(s1.studentPreferenceList);
		
		//Check if the sorting was done correctly
		assertEquals(sp2,s1.studentPreferenceList.get(0));
		assertEquals(sp3,s1.studentPreferenceList.get(1));
		assertEquals(sp1,s1.studentPreferenceList.get(2));
	}
}
