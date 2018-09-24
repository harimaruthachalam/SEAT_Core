package tests.unitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import models.Course;
import models.CourseSpecificHighPriorityStudents;
import models.CourseSpecificInsideDepartmentStudents;
import models.Slot;
import models.Student;
import models.StudentPreference;

import org.junit.Test;

import services.Constants;
import services.GenerateCoursePreference;

public class GenerateCoursePreferenceTests {
	
	@Test //Used to test the GenerateCoursePreferences.getStratifiedRandomList function
	public void getStratifiedRandomListTest() { 
		ArrayList<Student> studentList = new ArrayList<Student>();
		//Create 3 new student objects. We only care about the roll number of the course. Put random values for everything else
		Student s1 = new Student ("cs13b031",(float)4.5,1);
		Student s2 = new Student ("cs13b03",(float)4.55,1);
		Student s3 = new Student ("cs13b055",(float)6,1);
		Course c1 = new Course ("cs1100$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1100",false,null);

		
		//Add the course to the preference list of the students
		s1.studentPreferenceList.add(new StudentPreference(c1, 1, 3));
		s2.studentPreferenceList.add(new StudentPreference(c1, 1, 1));
		s3.studentPreferenceList.add(new StudentPreference(c1, 1, 2));
		
		//Add the students to the student list
		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);
		
		//Generate the stratified random list of the 
		ArrayList<Student> stratifiedRandomList = GenerateCoursePreference.getStratifiedRandomList(studentList,c1);
		
		//Check if the list returned has the students in the correct order
		assertEquals(s2,stratifiedRandomList.get(0));
		assertEquals(s3,stratifiedRandomList.get(1));
		assertEquals(s1,stratifiedRandomList.get(2));

	}
	
	
	@Test //Used to test the GenerateCoursePreferences.getStratifiedRandomList function
	public void createNewCoursePreferencesTest() { 
		//Create 3 new student objects. We only care about the roll number of the course. Put random values for everything else
		Student s1 = new Student ("cs13b031",(float) 4.5,1);
		Student s2 = new Student ("cs13b030",(float)4.55,1);
		Student s3 = new Student ("cs13b055",(float)6,1);
		
		Course c1 = new Course ("cs1100$outside","cs",1,Constants.stratifiedRandom,10,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",1,Constants.CGPA,10,new ArrayList<Slot>(),"cs1200",false,null);

		
		//Add the course to the preference list of the students
		s1.studentPreferenceList.add(new StudentPreference(c1, 1, 3));
		s2.studentPreferenceList.add(new StudentPreference(c1, 1, 1));
		s3.studentPreferenceList.add(new StudentPreference(c1, 1, 2));
		s1.studentPreferenceList.add(new StudentPreference(c2, 1, 1));
		s2.studentPreferenceList.add(new StudentPreference(c2, 1, 1));
		s3.studentPreferenceList.add(new StudentPreference(c2, 1, 1));
		
		//Add the students to the student list and courses to course list
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList.add(c1);
		courseList.add(c2);
		
		//Generate the course preference list for the 2 courses
		GenerateCoursePreference.createNewCoursePreferences(studentList, courseList, new ArrayList<CourseSpecificHighPriorityStudents>(), new ArrayList<CourseSpecificInsideDepartmentStudents>());

		//Check if students are in the order that they should be
		assertEquals(s2,c1.coursePreferenceList.get(0).getStudentObj());
		assertEquals(s3,c1.coursePreferenceList.get(1).getStudentObj());
		assertEquals(s1,c1.coursePreferenceList.get(2).getStudentObj());
		
		assertEquals(s3,c2.coursePreferenceList.get(0).getStudentObj());
		assertEquals(s2,c2.coursePreferenceList.get(1).getStudentObj());
		assertEquals(s1,c2.coursePreferenceList.get(2).getStudentObj());
	}
}
