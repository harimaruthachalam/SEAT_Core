package services.dataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import models.Course;
import models.CourseSpecificInsideDepartmentStudents;
import models.Student;
import models.StudentPreference;
import services.Constants;


public class PopulateStudentPreferenceList{
	/**
	 * This function populates the studentPreferenceList field of all students by reading the input preference list
	 * file (whose name is passed as an argument).
	 * 
	 * It also ensures that every student and course on the preference lists also exists in the student and course list respectively 
	 * @param courseSpecificInsideDeptInfo 
	 */
	public static String execute(ArrayList<Student> studentList,ArrayList<Course> courseList,String inputFile, ArrayList<CourseSpecificInsideDepartmentStudents> courseSpecificInsideDeptInfo){
		//Some declarations
		String line;
		String [] inputLine;
		String splitBy = ",";
		Student tempStudent;
		Course tempCourseInsideDept;
		Course tempCourseOutsideDept;
		int newColourNumber = 1000;
		
		//reading input line by line and adding a new student for every line.
		try {
			//open input file and start reading
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			//Skip the first line since it will be the header row
			br.readLine();
			//read input file line by line
			while ((line = br.readLine()) != null) {			
				line.replaceAll("\\s+",""); //Remove all whitespace
				inputLine = line.split(splitBy);
				
				//Get the various columns that are got after split the line
				String studentRollNumber = inputLine[0];
				String originalcourseNumber = inputLine[1];
				
				//Set colour to -1 if the input file says NULL. Else read the value.
				int colour; 
				if (inputLine[2].equalsIgnoreCase("NULL")){
					colour = -1;
				}
				else{
					colour = Integer.parseInt(inputLine[2]);
				}
				
				//Now if the colour = 'no colour' option, give it a new, unused colour number
				if (colour == Constants.noColourOption){
					colour = newColourNumber++;
				}
				
				String type = inputLine[3];		

				//Set colour to -1 if the input file says NULL. Else read the value.
				int preferenceNumber; 
				if (inputLine[4].equalsIgnoreCase("NULL")){
					preferenceNumber = -1;
				}
				else{
					preferenceNumber = Integer.parseInt(inputLine[4]);
				}
				
				//Get the student object corresponding to this preference
				tempStudent = Student.getStudentByRollNo(studentRollNumber,studentList);
				if (tempStudent == null){ //If the student does not exist in the student list - throw an error
		        	br.close();
		        	return "Student : " + studentRollNumber + " does not exist but has an entry in the student preference list";
		        }

				//Get the inside department course object corresponding to this preference
				tempCourseInsideDept = Course.getCourseBycourseNumber(originalcourseNumber + Constants.insideDepartmentSuffix, courseList);
				if (tempCourseInsideDept==null){ //The course does not exist in the course list - error
					br.close();
					return "Course: " + originalcourseNumber + " does not exist, but the student " + tempStudent.getRollNo() + " has given a preference for it";
				}
				
				//Get the outside department course object corresponding to this preference
				tempCourseOutsideDept = Course.getCourseBycourseNumber(originalcourseNumber + Constants.outsideDepartmentSuffix, courseList);
				if (tempCourseOutsideDept==null){ //The course does not exist in the course list - error
					br.close();
					return "Course: " + originalcourseNumber + " does not exist, but the student " + tempStudent.getRollNo() + " has given a preference for it";
				}
				
				//Add to the list of core courses or elective courses depending on what is given
				if (type.equalsIgnoreCase(Constants.coreCourse) || type.equalsIgnoreCase(Constants.backlogCourse)){ //Is a core course
					tempStudent.coreCourses.add(tempCourseInsideDept);
				}
				else{ //else if the course is an elective
					if (tempCourseInsideDept.studentIsInsideDepartment(tempStudent,courseSpecificInsideDeptInfo)){ //If the course considers the student as inside department
						//Add both the inside and outside department versions of the course (in that respective order)
						//The reason for using the multiplication by 2 when we add the preference number, is that - if a student has 2 courses on his preference list (c1,c2) which consider him as inside department, each of those will have an inside and outside version. So his preference list should actually look like - (c1Inside,c1Outside,c2Inside,c2Outside). Hence we use the multiplication by 2 to achieve this expanded preference list. The possible gaps that this may introduce is fixed in the latter part of this function 
						tempStudent.studentPreferenceList.add(new StudentPreference(tempCourseInsideDept,colour,preferenceNumber*2)); 
						tempStudent.studentPreferenceList.add(new StudentPreference(tempCourseOutsideDept,colour,preferenceNumber*2 + 1)); 
					}
					else{ //else the course considers the student as outside department
						//Add only the outside department version of the course 
						tempStudent.studentPreferenceList.add(new StudentPreference(tempCourseOutsideDept,colour,preferenceNumber*2 + 1));
					}
					
				}
				
				
				
			}
			br.close(); //closing file pointer
		//just some exception handling.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Now that we have read the student preference list, loop through the set of students.
		for (Student st : studentList){
			//Sort the preference list based on the preference numbers provided
			Collections.sort(st.studentPreferenceList);
			//Now relabel the preference numbers : This may be necessary if the original preference numbers had gaps, and also because we could have introduced gaps when we created the preference object
			for (int i=0;i<st.studentPreferenceList.size();i++){
				st.studentPreferenceList.get(i).setPreferenceNumberToPositionInPreferenceList(st.studentPreferenceList);
			}
		}
		return null;
	}
}