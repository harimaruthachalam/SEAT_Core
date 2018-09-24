package tests.testCaseGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * 
 * @author akshay
 *
 *This file is a stand alone program.
 *
 *This class is used for creating a fresh test case.
 *It will generate the following
 *a) List of students with random GPAs
 *b) List of courses with random ranking criteria, course capacity and 
 *c) A preference for students which is non-skewed. i.e. the student's preferences are purely random.
 *
 *UPDATES by Abhishek:
 *a) Added list pf prerequisite course grades for students.
 *b) Added prerequisite course number for courses and prerequisite ranking criteria value '4'.
 *c) Fixed the sanity check according to my understanding.
 *d) Added max_credit random value for student.
 *e) Added randomCreditValue function and randomSlot for Course credits.
 *
 *HOW TO USE
 *1. Just run the program and enter whatever bounds or names the program asks for. All limits are inclusive.
 */
public class createFreshTestCaseSkewed {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		/* Taking the various inputs from the user */
		System.out.println("Enter the number of students");
		int noOfStudents = sc.nextInt();
		
		System.out.println("Enter the number of courses");
		int noOfCourses = sc.nextInt();
		sc.nextLine();//This is necessary because the sc.nextInt() doesn't consume the '\n' and this is read by the sc.nextLine() appearing after this. Hence this is just meant to consume the '\n' character
		
		System.out.println("Please type the name of the output file to write the students' list to. No spaces allowed");
		String studentListFile = sc.nextLine();
		
		System.out.println("Please type the name of the output file to write the courses' list to. No spaces allowed");
		String courseListFile = sc.nextLine();
		
		System.out.println("Please type the name of the output file to write the student prefferences to. No spaces allowed");
		String studentPreferencesFile = sc.nextLine();
		
		System.out.println("Please enter the upper limit for the credits of a student");
		int studentCreditUpperLimit = sc.nextInt();
		
		System.out.println("Please enter the lower limit for the credits of a student");
		int studentCreditLowerLimit = sc.nextInt();
		
		System.out.println("Please enter the upper limit for the course capacity of a course");
		int courseSizeUpperLimit = sc.nextInt();
		
		System.out.println("Please enter the lower limit for the course capacity of a course");
		int courseSizeLowerLimit = sc.nextInt();
		
		System.out.println("Please enter the upper limit to the size of a student's preference list");
		int preferenceListUpperLimit = sc.nextInt();
		
		System.out.println("Please enter the lower limit to the size of a student's preference list");
		int preferenceListLowerLimit = sc.nextInt();
		
		//Given this ratio, we can calculate the number of equivalence groups for a student. Say it is 'n'. Then we pick a random number
		//from 0 to n-1 (both included), and assign that as the equivalence group number for the course. So the number of group numbers actually
		//used would typically be lesser than 'n'. Also note that this ratio can be arbitrarily large (it need not be lesser than 1). Keeping it 
		//greater than 1 means that it will have a large choice of equivalence group numbers to pick from
		System.out.println("Please enter the ratio of the number of equivalence groups to the size of the preference list of a student");
		double equivalenceGroupRatio = sc.nextDouble();
		
		System.out.println();
		//Taking an input of the skewdness in student preferences. 
		System.out.println("Please enter the skewedness in student Preferences that you want. The format for entering it is this.");
		System.out.println("On each line enter 2 space separated numbers. The first is the number of courses that you want in a group. The second is the probability that a student picks a course from this group");
		System.out.println("Note that the sum of the number of courses in the groups should add up to the number of courses you entered above. The probabilities should also add up to 1");
		
		//Some declarations for taking in the total skewedness
		ArrayList<CourseGroup> courseGroupsList = new ArrayList<CourseGroup>();
		int totalCourses = 0;
		double totalProbability = 0;
		int nextNoOfCourses = 0;
		double nextProbabilityForGroup = 0;
		
		while (totalCourses!=noOfCourses){
			nextNoOfCourses = sc.nextInt();
			nextProbabilityForGroup = sc.nextDouble();
			courseGroupsList.add(new CourseGroup(totalCourses, totalCourses + nextNoOfCourses -1, totalProbability, totalProbability + nextProbabilityForGroup));
			totalCourses += nextNoOfCourses;
			totalProbability += nextProbabilityForGroup;
			if (totalCourses>noOfCourses || totalProbability>1){
				System.out.println("SUM OF COURSES IN EACH GROUP EXCEEDS TOTAL NUMBER OF COURSES, OR SUM OF PROBABILITIES EXCEEDS 1");
			}
		}
		
		System.out.println("Generating Test Case .......");

		sc.close(); //closing the scanner
		
		//Just a sanity check, The lower limit should not be more than noOfCourses.
		if (preferenceListLowerLimit>noOfCourses){
			System.out.println("preferenceListLowerLimit>noOfCourses. Please restart and ensure this doesn't happen again");
			System.exit(1);
		}
		if (preferenceListUpperLimit>noOfCourses){
			preferenceListUpperLimit=noOfCourses;
			System.out.println("preferenceListUpperLimit set to noOfCourses.");
		}

		/* create the student List file */
		createStudentList(noOfStudents,studentListFile,studentCreditLowerLimit,studentCreditUpperLimit);
		
		/* create the course List file */
		createCourseList(noOfCourses,courseListFile,courseSizeUpperLimit,courseSizeLowerLimit);
		
		/* create the student preference Lists file */
		createPreferenceLists(noOfStudents,noOfCourses,studentPreferencesFile,preferenceListUpperLimit,preferenceListLowerLimit,courseGroupsList,equivalenceGroupRatio);
		
		System.out.println("TEST CASE SUCCESSFULLY CREATED.");
	}
	
	/**
	 * The function creates the file with the student list
	 * @param noOfStudents
	 * @param studentListFile
	 * @param studentCreditUpperLimit 
	 * @param studentCreditLowerLimit 
	 * @throws IOException 
	 */
	public static void createStudentList(int noOfStudents,String studentListFile, int studentCreditLowerLimit, int studentCreditUpperLimit) throws IOException{
		
		//Open output file for writing
		File outfile = new File(studentListFile);

		if (!outfile.exists()) {
			outfile.createNewFile();
		}
		FileWriter fw = new FileWriter(outfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		//looping over the number of students
		for (int i=0;i<noOfStudents;i++){
			bw.write("student"+ i + ",");  //write student roll no
			bw.write(String.valueOf(randomGPA(5,10) + ",")); //write a gpa for him
			bw.write(String.valueOf(randomInteger(studentCreditLowerLimit,studentCreditUpperLimit) + ",")); //random value for max_credit by student
			bw.write("\n");
		}
		
		bw.close();
	}

	/**
	 * The function creates the file with the course list
	 * @param noOfCourses
	 * @param courseListFile
	 * @param courseSizeUpperLimit
	 * @param courseSizeLowerLimit
	 * @throws IOException 
	 */
	public static void createCourseList(int noOfCourses,String courseListFile,int courseSizeUpperLimit,int courseSizeLowerLimit) throws IOException{
		
		//Open output file for writing
		File outfile = new File(courseListFile);
		if (!outfile.exists()) {
			outfile.createNewFile();
		}
		FileWriter fw = new FileWriter(outfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i=0;i<noOfCourses;i++){
			bw.write("course"+ i +",");  //write course number
			bw.write(String.valueOf(randomInteger(courseSizeLowerLimit,courseSizeUpperLimit) + "," )); //write course capacity (size)
			bw.write(String.valueOf(randomInteger(1,3) + ",")); //write ranking criteria
			bw.write(String.valueOf(randomCreditValue()) + ","); //write random credit value for course.
			bw.write(randomSlot() + ","); //write random slot for course.
			bw.write("\n");
		}
		
		bw.close();
	}
	
	/**
	 * The function creates the file with the students' preference lists
	 * @param noOfStudents
	 * @param noOfCourses
	 * @param studentPreferencesFile
	 * @param preferenceListUpperLimit - upper limit on the size of any student's preference list
	 * @param preferenceListLowerLimit - lower limit on the size of any student's preference list
	 * @param courseGroupsList 
	 * @param equivalenceGroupRatio 
	 * @throws IOException 
	 */
	public static void createPreferenceLists(int noOfStudents,int noOfCourses,String studentPreferencesFile,int preferenceListUpperLimit,int preferenceListLowerLimit, ArrayList<CourseGroup> courseGroupsList, double equivalenceGroupRatio) throws IOException{
		
		//Just some declarations
		int preferenceListSize;
		ArrayList<Boolean> courseAdded;
		int nextCourse;
		
		//Open output file for writing
		File outfile = new File(studentPreferencesFile);
		if (!outfile.exists()) {
			outfile.createNewFile();
		}
		FileWriter fw = new FileWriter(outfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		//looping over the number of students
		for (int i=0;i<noOfStudents;i++){
			
			//set the size of this student's preference list
			preferenceListSize = randomInteger(preferenceListLowerLimit,preferenceListUpperLimit);
			//This keeps track of which courses have already been added to the preference list
			courseAdded = new ArrayList<Boolean>(noOfCourses); 
			for (int k=0;k<noOfCourses;k++){
				courseAdded.add(false); //initially setting that no courses have been added
			}
			
			//run the loop 'preferenceListSize' number of times and each time add a course to the student's preference list which has not yet been added.
			for (int j=0;j<preferenceListSize;j++){
				bw.write("student"+ i);  //write student roll no
				
				//keep checking courses randomly until you come across one which hasn't already been added to the preferenceList
				while (true){
					nextCourse = getNextCourseBasedOnGroupSkewedness(courseGroupsList,noOfCourses);
					if (courseAdded.get(nextCourse)==false){
						courseAdded.set(nextCourse,true);
						break;
					}
				}
				
				int numberOfDistinctColors = (int)(equivalenceGroupRatio*(double)(preferenceListSize));
				int courseColorNumber = randomInteger(0,numberOfDistinctColors);
				//add the 'nextCourse' to the preferenceList of the student
				bw.write(",course"+ nextCourse + "," + courseColorNumber + ",ELECTIVE," + (j+1) + "\n");  
			}
		}
		
		bw.close();
	}
	
	//SET OF HELPER FUNCTIONS
	
	/**
	 * 
	 * @param courseGroupsList
	 * @param noOfCourses
	 * @return Selected a group based on the probability distribution given as input, and then return 1 course randomly from that group
	 */
	private static int getNextCourseBasedOnGroupSkewedness(ArrayList<CourseGroup> courseGroupsList, int noOfCourses) {
		Random randomGen = new Random();
		double groupSelecter = randomGen.nextDouble();
		CourseGroup selectedCourseGroup = null;
		for (CourseGroup group : courseGroupsList){
			if (groupSelecter >= group.choiceValueStart && groupSelecter<= group.choiceValueEnd){
				selectedCourseGroup = group;
				break;
			}
		}
		int selectedCourseNo = randomInteger(selectedCourseGroup.courseNumberStart, selectedCourseGroup.courseNumberEnd);
		return selectedCourseNo;
	}

	/**
	 * 
	 * @param lowerLimit
	 * @param upperLimit
	 * @return =  returns a random float between the lower and upper limits
	 */
	public static float randomGPA(int lowerLimit,int upperLimit){
		// create random object
		Random randomno = new Random();
		int temp;
		float returnValue;
		
		//compute the random number in the range
		returnValue=  randomno.nextFloat()*(upperLimit-lowerLimit) + lowerLimit;
		
		//take only first 2 digits after decimal (since it is a GPA)
		temp = (int)(returnValue*100);
		returnValue = ((float)temp)/100;
		
		// check next float value  
		return returnValue;
	}
	
	
	/**
	 * 
	 * @param lowerLimit
	 * @param upperLimit
	 * @return =  returns a random integer between the lower(included) and upper limits (included)
	 */
	public static int randomInteger(int lowerLimit,int upperLimit){
		// create random object
		Random randomno = new Random();
		int returnValue = randomno.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
		return returnValue;
	}
	
	/**
	*
	*@return a random integer from 6, 9, 12, 15 and 18 denoting the course credit value.
	*/
	public static int randomCreditValue(){
		Random multiplier = new Random();
		int creditValue = 3*(multiplier.nextInt(5) + 2);
		return creditValue;
	}

	/**
	*
	*@return a random character for slot from list defined inside.
	*/
	public static char randomSlot(){
		Random random = new Random();
		List<Character> slots = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T');
		char randomSlotValue = slots.get(random.nextInt(slots.size()));
		return randomSlotValue;
	}
}


class CourseGroup{
	int courseNumberStart;
	int courseNumberEnd;
	double choiceValueStart;
	double choiceValueEnd;
	
	public CourseGroup (int a,int b,double c, double d){
		courseNumberStart = a;
		courseNumberEnd = b;
		choiceValueStart = c;
		choiceValueEnd = d;
	}
}