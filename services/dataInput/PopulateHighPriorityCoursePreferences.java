package services.dataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.Course;
import models.CourseSpecificHighPriorityStudents;
import services.Constants;

public class PopulateHighPriorityCoursePreferences{
	/**
	 * This function reads the config file which specifies certain courses that consider some department
	 * students as high priority
	 * @param inpFile - name of the input file
	 * @param courseList - courseList that has already been populated
	 * @param errorMsgList - holder for any error message
	 * @return The list of CourseSpecificHighPriorityStudents which contain information about courses that consider other department
	 * students as high priority
	 */
	public static ArrayList<CourseSpecificHighPriorityStudents> execute(String inpFile, ArrayList<Course> courseList,String[] errorMsgList) {
		//Some declarations
		String line;
		String [] inputLine;
		String splitBy = ",";
		ArrayList<CourseSpecificHighPriorityStudents> courseSpecificHighPriorityInfo = new ArrayList<CourseSpecificHighPriorityStudents>();
		Course tempCourseInsideDept,tempCourseOutsideDept;
		//reading input line by line and adding a new student for every line.
		try{
			BufferedReader br = new BufferedReader(new FileReader(inpFile));
			//Skip the first line since it will be the header row
			br.readLine();
			//read line by line
			while ((line = br.readLine()) != null) {  
				line.replaceAll("\\s+",""); //Remove all whitespace
				inputLine = line.split(splitBy);
				
				tempCourseInsideDept = Course.getCourseBycourseNumber(inputLine[0]+Constants.insideDepartmentSuffix,courseList);  //The first string has the course Id with which we can get the course object it corresponds to
				if (tempCourseInsideDept==null){ //The course does not exist in the course list : return error message				
					br.close();
					errorMsgList[0] =  "Course : " + inputLine[0] + " does not exist, but it has an entry in the inside department config file";
					return null;
				}
				
				tempCourseOutsideDept = Course.getCourseBycourseNumber(inputLine[0]+Constants.outsideDepartmentSuffix,courseList);  //The first string has the course Id with which we can get the course object it corresponds to
				if (tempCourseOutsideDept==null){ //The course does not exist in the course list : return error message				
					br.close();
					errorMsgList[0] =  "Course : " + inputLine[0] + " does not exist, but it has an entry in the inside department config file";
					return null;
				}
			            
				for(int i=1;i<inputLine.length;i++){  //loop through the departments and add 2 CourseSpecificHighPriorityStudents objects for each of them (1 for the inside department version, and 1 for the outside department version)
					String departmentAndYear = inputLine[i];
					String department = departmentAndYear.substring(0, 2);
					int year = Integer.parseInt("20" + departmentAndYear.substring(2,4));
					CourseSpecificHighPriorityStudents tempHighPriorityInfo1 = new CourseSpecificHighPriorityStudents(tempCourseInsideDept,department,year);
					CourseSpecificHighPriorityStudents tempHighPriorityInfo2 = new CourseSpecificHighPriorityStudents(tempCourseOutsideDept,department,year);
					courseSpecificHighPriorityInfo.add(tempHighPriorityInfo1);
					courseSpecificHighPriorityInfo.add(tempHighPriorityInfo2);
				}    
			}
			br.close();//closing file pointer
		//just some exception handling
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return courseSpecificHighPriorityInfo;
	}
}