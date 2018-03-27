package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Course;
import models.Slot;
import models.Student;
import models.StudentPreference;

import org.junit.Test;

import services.GetStatistics;

public class GetStatisticsClassTests {

	@Test  //Used to test the services.GetStatistics.getMean method
	public void getMeanTest() {
		//Create an array and add some numbers
		ArrayList<Double> arr = new ArrayList<Double>();
		arr.add(3.0);
		arr.add(3.5);
		arr.add(3.0);
		arr.add(3.5);
		arr.add(5.0);
		arr.add(1.5);
		
		//Make a call to the function and check if the expected value is returned
		assertEquals(3.25,GetStatistics.getMean(arr),0.001);
	}

	@Test  //Used to test the services.GetStatistics.getStandardDeviation method
	public void getStandardDeviationTest() {
		//Create an array and add some numbers
		ArrayList<Double> arr = new ArrayList<Double>();
		arr.add(3.0); 
		arr.add(3.5); 
		arr.add(3.0); 
		arr.add(3.5); 
		arr.add(5.0); 
		arr.add(1.5); 
		
		//Make a call to the function and check if the expected value is returned
		assertEquals(1.030776406,GetStatistics.getStandardDeviation(arr),0.001);
	}
	
	@Test  //Used to test the services.GetStatistics.getLowest10Percentile method
	public void getLowest10PercentileTest() {
		//Create an array and add some numbers
		ArrayList<Double> arr = new ArrayList<Double>();
		arr.add(10.0); 
		arr.add(20.0); 
		arr.add(30.0); 
		arr.add(40.0); 
		arr.add(50.0); 
		arr.add(60.0); 
		arr.add(70.0); 
		arr.add(80.0); 
		arr.add(90.0); 
		
		//Make a call to the function and check if the expected value is returned
		assertEquals(10.0,GetStatistics.getLowest10Percentile(arr),0.001);
		
		//Add an additional element and see if the answer changes
		arr.add(100.0);
		assertEquals(20.0,GetStatistics.getLowest10Percentile(arr),0.001);
	}
	
	@Test  //Used to test the services.GetStatistics.getHighest0Percentile method
	public void getHighest10PercentileTest() {
		//Create an array and add some numbers
		ArrayList<Double> arr = new ArrayList<Double>();
		arr.add(10.0); 
		arr.add(20.0); 
		arr.add(30.0); 
		arr.add(40.0); 
		arr.add(50.0); 
		arr.add(60.0); 
		arr.add(70.0); 
		arr.add(80.0); 
		arr.add(90.0); 
		
		//Make a call to the function and check if the expected value is returned
		assertEquals(90.0,GetStatistics.getHighest10Percentile(arr),0.001);
		
		//Add an additional element and see if the answer changes
		arr.add(100.0);
		assertEquals(100.0,GetStatistics.getHighest10Percentile(arr),0.001);
	}
	
	@Test  //Used to test the services.GetStatistics.computePerStudentStatistics method
	public void computePerStudentStatisticsTest() {
		//Create some students and courses (fill any random values for the constructor). We do not care
		Student s1 = new Student("cs13b031",10,60);
		Student s2 = new Student("cs13b032",10,2);
		
		//outside department courses
		Course c1out = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2out = new Course ("cs1200$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3out = new Course ("cs1300$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1300",false,null);
		Course c4out = new Course ("cs1400$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1400",false,null);
		Course c5out = new Course ("cs1500$outside","cs",0,1,59,new ArrayList<Slot>(),"cs1500",false,null);
		Course c6out = new Course ("hs1600$outside","hs",1,1,1,new ArrayList<Slot>(),"hs1600",false,null);
		Course c7out = new Course ("cs1700$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1700",false,null);
		
		//inside department courses
		Course c1in = new Course ("cs1100$inside","cs",1,1,1,new ArrayList<Slot>(),"cs1100",true,c1out);
		Course c2in = new Course ("cs1200$inside","cs",1,1,1,new ArrayList<Slot>(),"cs1200",true,c2out);
		Course c3in = new Course ("cs1300$inside","cs",1,1,1,new ArrayList<Slot>(),"cs1300",true,c3out);
		Course c4in = new Course ("cs1400$inside","cs",1,1,1,new ArrayList<Slot>(),"cs1400",true,c4out);
		Course c5in = new Course ("cs1500$inside","cs",1,1,59,new ArrayList<Slot>(),"cs1500",true,c5out);
		//Course c6in = new Course ("hs1600$inside","hs",0,1,1,new ArrayList<Slot>(),"hs1600",true,c6out);
		Course c7in = new Course ("cs1700$inside","cs",1,1,1,new ArrayList<Slot>(),"cs1700",true,c7out);
		
		//Add the students to the studentList
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(s1);
		studentList.add(s2);
		
		//Create some StudentPreference objects. We will add them to the preference lists later on
		StudentPreference cp1in = new StudentPreference(c1in,2,1);
		StudentPreference cp2in = new StudentPreference(c2in,2,3);
		StudentPreference cp3in = new StudentPreference(c3in,2,5);
		StudentPreference cp4in = new StudentPreference(c4in,3,7);
		StudentPreference cp5in = new StudentPreference(c5in,4,9);
		StudentPreference cpStudent2in = new StudentPreference(c1in,1,1);
		
		StudentPreference cp1out = new StudentPreference(c1out,2,2);
		StudentPreference cp2out = new StudentPreference(c2out,2,4);
		StudentPreference cp3out = new StudentPreference(c3out,2,6);
		StudentPreference cp4out = new StudentPreference(c4out,3,8);
		StudentPreference cp5out = new StudentPreference(c5out,4,10);
		StudentPreference cp6out = new StudentPreference(c6out,5,11);
		StudentPreference cpStudent2out = new StudentPreference(c1out,1,2);
		
		//Add the created preferences to the student lists
		s1.studentPreferenceList.add(cp1in);
		s1.studentPreferenceList.add(cp1out);
		s1.studentPreferenceList.add(cp2in);
		s1.studentPreferenceList.add(cp2out);
		s1.studentPreferenceList.add(cp3in);
		s1.studentPreferenceList.add(cp3out);
		s1.studentPreferenceList.add(cp4in);
		s1.studentPreferenceList.add(cp4out);
		s1.studentPreferenceList.add(cp5in);
		s1.studentPreferenceList.add(cp5out);
		s1.studentPreferenceList.add(cp6out);
		s2.studentPreferenceList.add(cpStudent2in);
		s2.studentPreferenceList.add(cpStudent2out);
		
		//Add a core course for s1  so that we know that we can get the correct answer even in the presence of core courses
		s1.coreCourses.add(c7in);
		
		//Calculate the maximum elective credits for each student
		s1.calculateMaxElectiveCredits();
		s2.calculateMaxElectiveCredits();
				
		//Add some courses to the courses allotted list for each student
		s1.orderedListOfcoursesAllotted.add(cp2in);
		s1.orderedListOfcoursesAllotted.add(cp4in);
		s1.orderedListOfcoursesAllotted.add(cp6out);
		s2.orderedListOfcoursesAllotted.add(cpStudent2in);
		
		//Make the call to the GetStatistics.computePerStudentStatistics that we are testing
		GetStatistics.computePerStudentStatistics(studentList);
		
		//Check if the statistics were correctly computed
		assertEquals(1.0, s1.effectiveAverageRank,0.001);
		assertEquals(1.0, s2.effectiveAverageRank,0.001);
		assertEquals(0.05, s1.creditSatisfactionRatio,0.001);
		assertEquals(0.5, s2.creditSatisfactionRatio,0.001);
	}
}
