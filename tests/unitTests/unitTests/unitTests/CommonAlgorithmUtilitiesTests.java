package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import models.ClassTiming;
import models.Course;
import models.Slot;
import models.Student;
import models.StudentPreference;

import org.junit.Test;

import services.CommonAlgorithmUtilities;

public class CommonAlgorithmUtilitiesTests {
	@Test //Used to test the service.CommonAlgorithmUtilities.removeStudentsWithNoCourseLeftToApplyTo class
	public void removeStudentsWithNoCourseLeftToApplyToTest() {
		//Create some student and course objects
		Student s1 = new Student("cs13b031",10,50);
		Student s2 = new Student("cs13b032",10,50);
		Course c1 = new Course ("cs1100$outside","cs",0,1,1,new ArrayList<Slot>(),"cs1100",false,null);
				
		//Create a student preference object for student2
		StudentPreference cp1 = new StudentPreference(c1,2,1);
		s1.studentPreferenceList.add(cp1);
		s1.coursesUnallotted.add(cp1);
		
		//Add the students to the list
		ArrayList<Student> sList = new ArrayList<Student>();
		sList.add(s1);
		sList.add(s2);
		
		//Run the function that we are testing
		CommonAlgorithmUtilities.removeStudentsWithNoCourseLeftToApplyTo(sList);
		
		//Check if the correct student was removed
		if (sList.contains(s2) || !(sList.contains(s1))){
			fail();
		}
	}
	
	
	@Test //Used to test the service.CommonAlgorithmUtilities.removeStudentPreferencesWithCreditRequirementExceedingCreditLimit class
	public void removeStudentPreferencesWithCreditRequirementExceedingCreditLimitTest() {
		//Create some student and course objects
		Student s1 = new Student("cs13b031",10,50);
		Student s2 = new Student("cs13b032",10,9);
		Course c1 = new Course ("cs1100$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1100",false,null);
				
		//Calculate the elective credits left for both students
		s1.calculateMaxElectiveCredits();
		s1.electiveCreditsLeft  = s1.getMaxElectiveCreditsInSem();
		s2.calculateMaxElectiveCredits();
		s2.electiveCreditsLeft = s2.getMaxElectiveCreditsInSem();
				
		//Create a student preference object of c1 for s1
		StudentPreference cp1 = new StudentPreference(c1,2,1);
		s1.studentPreferenceList.add(cp1);
		s1.coursesUnallotted.add(cp1);
		
		//Create a student preference object of c1 for s2
		StudentPreference cp2 = new StudentPreference(c1,2,1);
		s2.studentPreferenceList.add(cp2);
		s2.coursesUnallotted.add(cp2);
		
		//Add the students to the list
		ArrayList<Student> sList = new ArrayList<Student>();
		sList.add(s1);
		sList.add(s2);
		
		//Run the function that we are testing
		CommonAlgorithmUtilities.removeStudentPreferencesWithCreditRequirementExceedingCreditLimit(sList);
		
		//Check if the correct student preference was removed
		if (s2.coursesUnallotted.contains(cp2) || !(s1.coursesUnallotted.contains(cp1))){
			fail();
		}
	}

	@Test //Used to test the service.CommonAlgorithmUtilities.removeStudentPreferencesWithSlotConflict class
	public void removeStudentPreferencesWithSlotConflictTest() {
		
		//Create 2 slots
		ClassTiming ct1 = new ClassTiming(14,17,0,0,"TUE");
		ClassTiming ct2 = new ClassTiming(15,16,0,0,"WED");
		Slot slot1 = new Slot ("A",new ArrayList<ClassTiming>(Arrays.asList(ct1)));
		Slot slot2 = new Slot ("B",new ArrayList<ClassTiming>(Arrays.asList(ct2)));
		
		//Create some student and course objects
		Student s1 = new Student("cs13b031",10,50);
		Course c1 = new Course ("cs1100$outside","cs",0,1,10,new ArrayList<Slot>(Arrays.asList(slot1)),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,10,new ArrayList<Slot>(Arrays.asList(slot2)),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,10,new ArrayList<Slot>(Arrays.asList(slot1)),"cs1300",false,null);
		
		//Calculate the maximum elective credits for the student
		s1.calculateMaxElectiveCredits();
		s1.electiveCreditsLeft = s1.getMaxElectiveCreditsInSem();
		
		//Create a student preference objects for each course
		StudentPreference cp1 = new StudentPreference(c1,1,1);
		StudentPreference cp2 = new StudentPreference(c2,2,2);
		StudentPreference cp3 = new StudentPreference(c3,3,3);
		
		//Add the student preference objects to the preference list
		s1.studentPreferenceList.add(cp1);
		s1.studentPreferenceList.add(cp2);
		s1.studentPreferenceList.add(cp3);
		
		//Allot cp1 in current iteration and keep cp2 and cp3 in unallotted list. We want the function to remove cp3 but not cp2 because of slot conflict
		s1.courseAllottedInCurrentIteration = cp1;
		s1.coursesUnallotted.add(cp2);
		s1.coursesUnallotted.add(cp3);		
		
		//Add the students to the list
		ArrayList<Student> sList = new ArrayList<Student>();
		sList.add(s1);
		
		//Run the function that we are testing
		CommonAlgorithmUtilities.removeStudentPreferencesWithSlotConflict(sList);
		
		//Check if the correct student preferences were removed 
		if (s1.coursesUnallotted.contains(cp3) || !(s1.coursesUnallotted.contains(cp2))){
			fail();
		}
	}
	
	@Test //Used to test the service.CommonAlgorithmUtilities.removeStudentPreferencesWithColourConflict class
	public void removeStudentPreferencesWithColourConflictTest() {
		
		//Create some student and course objects
		Student s1 = new Student("cs13b031",10,50);
		Course c1 = new Course ("cs1100$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1300",false,null);
		
		//Calculate the maximum elective credits for the student
		s1.calculateMaxElectiveCredits();
		s1.electiveCreditsLeft = s1.getMaxElectiveCreditsInSem();
		
		//Create a student preference objects for each course
		StudentPreference cp1 = new StudentPreference(c1,1,1);
		StudentPreference cp2 = new StudentPreference(c2,2,2);
		StudentPreference cp3 = new StudentPreference(c3,1,3);
		
		//Add the student preference objects to the preference list
		s1.studentPreferenceList.add(cp1);
		s1.studentPreferenceList.add(cp2);
		s1.studentPreferenceList.add(cp3);
		
		//Allot cp1 in current iteration and keep cp2 and cp3 in unallotted list. We want the function to remove cp3 but not cp2 because of slot conflict
		s1.courseAllottedInCurrentIteration = cp1;
		s1.coursesUnallotted.add(cp2);
		s1.coursesUnallotted.add(cp3);		
		
		//Add the students to the list
		ArrayList<Student> sList = new ArrayList<Student>();
		sList.add(s1);
		
		//Run the function that we are testing
		CommonAlgorithmUtilities.removeStudentPreferencesWithColourConflict(sList);
		
		//Check if the correct student preferences were removed 
		if (s1.coursesUnallotted.contains(cp3) || !(s1.coursesUnallotted.contains(cp2))){
			fail();
		}
	}

	@Test //Used to test the service.CommonAlgorithmUtilities.freezeAllotmentOfCurrentIteration class
	public void freezeAllotmentOfCurrentIterationTest() {
		
		//Create some student and course objects
		Student s1 = new Student("cs13b031",10,50);
		Course c1 = new Course ("cs1100$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1300",false,null);
		
		//Calculate the maximum elective credits for the student
		s1.calculateMaxElectiveCredits();
		s1.electiveCreditsLeft = s1.getMaxElectiveCreditsInSem();
		
		//Create a student preference objects for each course
		StudentPreference cp1 = new StudentPreference(c1,1,1);
		StudentPreference cp2 = new StudentPreference(c2,2,2);
		StudentPreference cp3 = new StudentPreference(c3,1,3);
		
		//Add the student preference objects to the preference list
		s1.studentPreferenceList.add(cp1);
		s1.studentPreferenceList.add(cp2);
		s1.studentPreferenceList.add(cp3);
		
		//Allot cp1 in current iteration and keep cp2 and cp3 in unallotted list. We want the function to remove cp3 but not cp2 because of slot conflict
		s1.courseAllottedInCurrentIteration = cp1;
		s1.coursesUnallotted.add(cp2);
		s1.coursesUnallotted.add(cp3);		
		
		//Add the students to the list
		ArrayList<Student> sList = new ArrayList<Student>();
		sList.add(s1);
		
		//Run the function that we are testing
		CommonAlgorithmUtilities.freezeAllotmentOfCurrentIteration(sList);
		
		//Check if the correct student preference was added to the coursesAllotted list 
		if (s1.orderedListOfcoursesAllotted.contains(cp2) || s1.orderedListOfcoursesAllotted.contains(cp3) || !(s1.orderedListOfcoursesAllotted.contains(cp1))){
			fail();
		}
	}
	
	@Test //Used to test the service.CommonAlgorithmUtilities.recalculateStudentsLeftoverCredits class
	public void recalculateStudentsLeftoverCreditsTest() {
		
		//Create some student and course objects
		Student s1 = new Student("cs13b031",10,50);
		Course c1 = new Course ("cs1100$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1100",false,null);
		Course c2 = new Course ("cs1200$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1200",false,null);
		Course c3 = new Course ("cs1300$outside","cs",0,1,10,new ArrayList<Slot>(),"cs1300",false,null);
		
		//Calculate the maximum elective credits for the student
		s1.calculateMaxElectiveCredits();
		s1.electiveCreditsLeft = s1.getMaxElectiveCreditsInSem();
		
		//Create a student preference objects for each course
		StudentPreference cp1 = new StudentPreference(c1,1,1);
		StudentPreference cp2 = new StudentPreference(c2,2,2);
		StudentPreference cp3 = new StudentPreference(c3,1,3);
		
		//Add the student preference objects to the preference list
		s1.studentPreferenceList.add(cp1);
		s1.studentPreferenceList.add(cp2);
		s1.studentPreferenceList.add(cp3);
		
		//Allot cp1 in current iteration and keep cp2 and cp3 in unallotted list. We want the function to remove cp3 but not cp2 because of slot conflict
		s1.courseAllottedInCurrentIteration = cp1;
		s1.coursesUnallotted.add(cp2);
		s1.coursesUnallotted.add(cp3);		
		
		//Add the students to the list
		ArrayList<Student> sList = new ArrayList<Student>();
		sList.add(s1);
		
		//Run the function that we are testing
		CommonAlgorithmUtilities.recalculateStudentsLeftoverCredits(sList);
		
		//Check if the correct function correctly calculates the left over credits
		assertEquals(40, s1.electiveCreditsLeft);
	}
}
