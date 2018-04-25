package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Course;
import models.CoursePreference;
import models.Slot;
import models.Student;

import org.junit.Test;

public class CoursePreferenceClassTest {

	@Test //Used to test the models.CoursePreference.getCoursePreferenceByRollNo method
	public void getCoursePreferenceByRollNoTest() { 
		//Create 3 student 1 course objects. We only care about the name of the course/student. Put random values for everything else
		Student s1 = new Student ("cs13b031",1,30);
		Student s2 = new Student ("cs13b032",1,30);
		Student s3 = new Student ("cs13b030",1,30);
		Course c1 = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
				
		//Create student preference objects corresponding to these courses. We only care about the course object. Put any random value for everything else
		CoursePreference cp1 = new CoursePreference(1,s1);
		CoursePreference cp2 = new CoursePreference(2,s2);
		CoursePreference cp3 = new CoursePreference(3,s3);
		
		//Add the student preference objects to the preference list of s1
		c1.coursePreferenceList.add(cp1);
		c1.coursePreferenceList.add(cp2);
		c1.coursePreferenceList.add(cp3);
		
		//Making some queries
		CoursePreference result1 = CoursePreference.getCoursePreferenceByRollNo(c1.coursePreferenceList, "cs13b031");
		CoursePreference result2 = CoursePreference.getCoursePreferenceByRollNo(c1.coursePreferenceList, "cs13b032");
		CoursePreference result3 = CoursePreference.getCoursePreferenceByRollNo(c1.coursePreferenceList, "cs13b033");
		
		
		//Checking if we get the correct results for the queries
		assertEquals(cp1,result1);
		assertEquals(cp2,result2);
		assertEquals(null,result3);
		
	}

}
