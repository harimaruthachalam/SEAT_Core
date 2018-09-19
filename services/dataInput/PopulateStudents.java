package services.dataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;

import models.Student;

public class PopulateStudents{
	/**
	 * This function reads the data from the file and creates the student objects.
	 * It takes the input file as an argument
	 * @param deptSpecificMaxCreditLimitInfo
	 */
	public static ArrayList<Student> execute(String inputFile){
		//Some declarations
		String line;
		String [] inputLine;
		String splitBy = ",";
		ArrayList<Student> studentList = new ArrayList<Student>();
		Student newStudent;

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
				//create the new student object and add to student list
				newStudent = new Student(inputLine[0],Float.parseFloat(inputLine[1]),Integer.parseInt(inputLine[2]));
				studentList.add(newStudent);
			}
			br.close(); //closing file pointer
		//just some exception handling.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		// try{
		// 	Class.forName("com.mysql.jdbc.Driver");
		// 	//read line by line
		// 										Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false","root","password");
		// 										Statement st=con.createStatement();
		// 										ResultSet rs=st.executeQuery("select * from "+tablename);
		// 										if(rs == null){
		// 											System.out.println("Oops");
		// 										}
		// 	while (rs.next()) {
		// System.out.println(rs.getString(1));
		// 		inputLine = rs.getString(1).split(splitBy);
		// 					tempCourse = Course.getCourseBycourseNumber(rs.getString(0),courseList);  //The first string has the course Id with which we can get the course object it corresponds to
		// 					if (tempCourse==null){ //The course does not exist in the course list : return error message
		// 			con.close();
		// 			return "Course : " + rs.getString(0) + " does not exist, but it has an entry in the preference list";
		// 		}
		//
		// 				for(int i=0;i<inputLine.length;i++){  //loop through the students and add them to the course's preference list
		// 					tempStudent = Student.getStudentByRollNo(inputLine[i],studentList);
		// 					if (tempStudent == null){ //The student does not exist in the student list : return error message
		// 						con.close();
		// 						return "Student : " + inputLine[i] + " does not exist but was on the preference list of course " + inputLine[0];
		// 					}
		// 					tempCourse.coursePreferenceList.add(new CoursePreference(i+1,tempStudent));
		// 				}
		// 				}
		// 	con.close();//closing file pointer
		// //just some exception handling
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }


		return studentList;
	}
}
