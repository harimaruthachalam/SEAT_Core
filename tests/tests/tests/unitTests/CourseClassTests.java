package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Course;
import models.CourseSpecificHighPriorityStudents;
import models.CourseSpecificInsideDepartmentStudents;
import models.DepartmentSpecificMaxCreditLimit;
import models.Slot;
import models.Student;

import org.junit.Test;

import services.DataInput;

public class CourseClassTests {

	@Test
	public void getCourseBycourseNumberTest() { //Used to test the models.Course.getCourseBycourseNumber method
		ArrayList<Course> courseList = new ArrayList<Course>();
		//Create 3 new course objects. We only care about the name of the course. Put random values for everything else
		//outside department courses
		Course c1out = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2out = new Course ("cs1200$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3out = new Course ("cs1300$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1300",false,null);
			
		//inside department courses
		Course c1in = new Course ("cs1100$inside","cs",1,1,1,new ArrayList<Slot>(),"cs1100",true,c1out);
		Course c2in = new Course ("cs1200$inside","cs",1,1,1,new ArrayList<Slot>(),"cs1200",true,c2out);
		Course c3in = new Course ("cs1300$inside","cs",1,1,1,new ArrayList<Slot>(),"cs1300",true,c3out);
			
		
		//Add the courses to the course list
		courseList.add(c1in);
		courseList.add(c2in);
		courseList.add(c3in);
		courseList.add(c1out);
		courseList.add(c2out);
		courseList.add(c3out);
		
		//Make some queries
		Course result1 = Course.getCourseBycourseNumber("cs1100$inside", courseList);
		Course result2 = Course.getCourseBycourseNumber("cs1100$outside", courseList);
		Course result3 = Course.getCourseBycourseNumber("cs1100", courseList);
		
		//Check if the queries give the expected output
		assertEquals(c1in, result1);
		assertEquals(c1out, result2);
		assertEquals(null, result3);
	}
	
	@Test //Used to test the models.Course.slotClashWithCourse method
	public void slotClashWithCourseTest() { 
		//Read the slot timings from the slot config file.
		//This function expects the presence of a slot config file named 'slot_config.csv'
		//This test may need to be changed if the timetable changes
		ArrayList<Slot> slots = DataInput.populateSlots("new_slot_config.csv");
			
		//Get some of the slots
		Slot pSlot = Slot.getSlotByName("P", slots);
		Slot hSlot = Slot.getSlotByName("H", slots);
		Slot aSlot = Slot.getSlotByName("A", slots);
				
		//Create 3 new course objects. Apart from creating an arraylist for slots, put random values for the constructor. We don't care what they are here.
		Course c1 = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1300",false,null);
		Course c4 = new Course ("cs1300$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1300",false,null);

		//Add some slots for each of the course objects
		c1.slots.add(pSlot);
		c2.slots.add(hSlot);
		c3.slots.add(hSlot);
		c3.slots.add(aSlot);
		c4.slots.add(aSlot);
		
		//Check if the queries give the expected output
		assertTrue(c1.slotClashWithCourse(c2));
		assertTrue(c1.slotClashWithCourse(c3));
		assertTrue(c3.slotClashWithCourse(c4));
		assertFalse(c1.slotClashWithCourse(c4));
		assertFalse(c2.slotClashWithCourse(c4));
	}
	
	
	@Test //Used to test the models.Course.slotClashWithCourse method
	public void studentIsInsideDepartmentTest() { 
		//Create some student and course objects
		Student s1 = new Student ("cs13b031",1,62);
		Student s2 = new Student ("ed15b031",1,30);
		Student s3 = new Student ("ed13b031",1,30);
		Student s4 = new Student ("ep12b031",1,30);
		Course c1 = new Course ("cs4000$inside","cs",0,0,1,null,"cs4000", false,null);
				
		//Create a DepartmentSpecificMaxCreditLimit and add to the list
		ArrayList<CourseSpecificInsideDepartmentStudents> insideDeptList = new ArrayList<CourseSpecificInsideDepartmentStudents>();
		insideDeptList.add(new CourseSpecificInsideDepartmentStudents(c1,"ed", 2015));
		
		//Check if the function correctly computes if the student is inside department
		assertTrue(c1.studentIsInsideDepartment(s1, insideDeptList));
		assertTrue(c1.studentIsInsideDepartment(s2, insideDeptList));
		assertFalse(c1.studentIsInsideDepartment(s3, insideDeptList));
		assertFalse(c1.studentIsInsideDepartment(s4, insideDeptList));
	}
	
	
	@Test //Used to test the models.Course.studentIsHighPriority method
	public void studentIsHighPriorityTest() { 
		//Create some student and course objects
		Student s1 = new Student ("cs13b031",1,62);
		Student s2 = new Student ("ed15b031",1,30);
		Student s3 = new Student ("ed13b031",1,30);
		Student s4 = new Student ("ep12b031",1,30);
		Course c1 = new Course ("cs4000$inside","cs",0,0,1,null,"cs4000", false,null);
				
		//Create a CourseSpecificHighPriorityStudents object and add to the list
		ArrayList<CourseSpecificHighPriorityStudents> highPriorityList = new ArrayList<CourseSpecificHighPriorityStudents>();
		highPriorityList.add(new CourseSpecificHighPriorityStudents(c1,"ed", 2015));
		
		//Check if the function correctly computes if the student is inside department
		assertFalse(c1.studentIsHighPriority(s1, highPriorityList));
		assertTrue(c1.studentIsHighPriority(s2, highPriorityList));
		assertFalse(c1.studentIsHighPriority(s3, highPriorityList));
		assertFalse(c1.studentIsHighPriority(s4, highPriorityList));
	}
}
