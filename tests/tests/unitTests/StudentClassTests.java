package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Course;
import models.DepartmentSpecificMaxCreditLimit;
import models.Slot;
import models.Student;
import models.StudentPreference;

import org.junit.Test;

import services.Constants;

public class StudentClassTests {

	@Test //Used to test the models.Student.getStudentByRollNo method
	public void getStudentByRollNoTest() { 
		ArrayList<Student> studentList = new ArrayList<Student>();
		//Create 3 new student objects. We only care about the roll number of the course. Put random values for everything else
		Student s1 = new Student ("cs13b031",1,1);
		Student s2 = new Student ("cs13b03",1,1);
		Student s3 = new Student ("cs13b055",1,1);
		
		//Add the 3 students to the studentlist
		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);
		
		//Make some queries
		Student result1 = Student.getStudentByRollNo("cs13b031", studentList);
		Student result2 = Student.getStudentByRollNo("cs", studentList);
		Student result3 = Student.getStudentByRollNo("cs13b0311", studentList);
		
		//Check if the queries give the expected output
		assertEquals(s1, result1);
		assertEquals(null, result2);
		assertEquals(null, result3);
	}
	
	@Test //Used to test the  models.Student.hasCourseInPreferences method
	public void hasCourseInPreferencesTest() { 
		//Create 1 student 3 new course objects. We only care about the name of the course/student. Put random values for everything else
		Student s1 = new Student ("cs13b031",1,1);
		Course c1 = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1300",false,null);
		
		//Add some of the courses to the preference list of s1
		s1.studentPreferenceList.add(new StudentPreference(c1, 1, 1));
		s1.studentPreferenceList.add(new StudentPreference(c2, 1, 1));
		
		//Make some queries and see if they give the correct answer
		assertTrue(s1.hasCourseInPreferences(c1));
		assertTrue(s1.hasCourseInPreferences(c2));
		assertFalse(s1.hasCourseInPreferences(c3));
	}
	
	@Test //Used to test the models.Student.calculateMaxElectiveCredits method
	public void calculateMaxElectiveCreditsTest() { 
		//Create 2 student 3 new course objects. We only care about the name of the course/student. Put random values for everything else
		Student s1 = new Student ("cs13b031",1,30);
		Student s2 = new Student ("cs13b032",1,32);
		Course c1 = new Course ("cs1100$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,9,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,4,new ArrayList<Slot>(),"cs1300",false,null);
		
		//Add some of the courses to the preference list of s1 and s2
		s1.studentPreferenceList.add(new StudentPreference(c1, 1, 1));
		s1.coreCourses.add(c2);
		s1.coreCourses.add(c3);
		s2.studentPreferenceList.add(new StudentPreference(c1, 1, 1));
		
		//Calculate the max elective credits for s1 and s2
		s1.calculateMaxElectiveCredits();
		s2.calculateMaxElectiveCredits();
		
		//Check if the function calculated them correctly
		assertEquals(s1.getMaxElectiveCreditsInSem(), 17);
		assertEquals(s2.getMaxElectiveCreditsInSem(), 32);
	}

	
	@Test //Used to test the  models.Student.getDepartment & models.Student.getYear methods
	public void getDepartmentAndGetYearTest() { 
		Student s1 = new Student ("cs13b031",1,30);
		Student s2 = new Student ("ed12b031",1,30);
		
		//Check if the department and years are correctly computed
		assertEquals(2013,s1.getYear());
		assertEquals(2012,s2.getYear());
		assertTrue(s1.getDepartment().equalsIgnoreCase("CS"));
		assertTrue(s2.getDepartment().equalsIgnoreCase("ed"));
	}
	
	
	@Test //Used to test the models.Student.maxCreditLimitForStudent method
	public void maxCreditLimitForStudentTest() { 
		//Create some student objects
		Student s1 = new Student ("cs13b031",1,62);
		Student s2 = new Student ("ed12b031",1,30);
		
		//Create a DepartmentSpecificMaxCreditLimit and add to the list
		ArrayList<DepartmentSpecificMaxCreditLimit> deptSpecificMaxCreditLimitInfo = new ArrayList<DepartmentSpecificMaxCreditLimit>();
		deptSpecificMaxCreditLimitInfo.add(new DepartmentSpecificMaxCreditLimit("cs", 2013, 63));
		
		//Check the max Credit limit for both these students
		assertEquals(63,s1.maxCreditLimitForStudent(deptSpecificMaxCreditLimitInfo));
		assertEquals(Constants.maxCreditDefaultLimit,s2.maxCreditLimitForStudent(deptSpecificMaxCreditLimitInfo));
	}
}
