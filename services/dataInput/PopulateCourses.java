package services.dataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import models.Course;
import models.Slot;
import services.Constants;

public class PopulateCourses{
	/**
	 * This function reads the data from the file and creates the course objects.
	 * It takes the input file as an argument
	 * @param slots 
	 * @param errorMsgList 
	 */
	public static ArrayList<Course> execute(String inputFile, ArrayList<Slot> slots, String[] errorMsgList){		
		//Some declarations
		String line;
		String [] inputLine;
		String splitBy = ",";
		ArrayList<Course> courseList = new ArrayList<Course>();
		Course insideDeptCourse;
		Course outsideDeptCourse;
		Slot tempSlot;
		
		//reading input line by line and adding a new course for every line.
		try {
			//open input file and start reading
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			//Skip the first line since it will be the header row
			br.readLine();
			//read input file line by line
			while ((line = br.readLine()) != null) {			
				line.replaceAll("\\s+",""); //Remove all whitespace
				inputLine = line.split(splitBy);
				
				//Add the slots for the course
				ArrayList<Slot> tempSlotList = new ArrayList<Slot>();
				for (int i=6;i<inputLine.length;i++){
					tempSlot = Slot.getSlotByName(inputLine[i],slots);
					if (tempSlot==null){ //If we could not find the slot : Course is in an invalid slot. Throw error
						errorMsgList[0] = "Course " + inputLine[0] + " is in the slot " + inputLine[i] + " which is not mentioned in the slot timings input.";
						br.close();
						return null;
					}
					tempSlotList.add(tempSlot);
				}
				
				
				//Compute all the parameters required to invoke the 'new Course()' constructor
				String courseNumber = inputLine[0];
				String courseNumberInsideDepartment = courseNumber + Constants.insideDepartmentSuffix;
				String courseNumberOutsideDepartment = courseNumber + Constants.outsideDepartmentSuffix;
				
				String department = inputLine[1];
				int totalCapacity = Integer.parseInt(inputLine[2]);
				int outsideDepartmentCapacity = Integer.parseInt(inputLine[3]);
				int insideDepartmentCapacity = totalCapacity - outsideDepartmentCapacity;
				int rankingCriteria = Integer.parseInt(inputLine[4]);
				int credits = Integer.parseInt(inputLine[5]);
				
				//create the 2 new course object - 1 for inside department, and the other for outside department. Add them to the course list
				outsideDeptCourse = new Course(courseNumberOutsideDepartment,department,outsideDepartmentCapacity,rankingCriteria,credits,tempSlotList,courseNumber,false,null);
				insideDeptCourse = new Course(courseNumberInsideDepartment,department,insideDepartmentCapacity,rankingCriteria,credits,tempSlotList,courseNumber,true,outsideDeptCourse);
				courseList.add(insideDeptCourse);
				courseList.add(outsideDeptCourse);
			}
			br.close(); //closing file pointer
		//just some exception handling.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return courseList; 
	}

}