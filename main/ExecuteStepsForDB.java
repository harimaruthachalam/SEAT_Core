package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Properties;
import java.sql.*;

import models.Batch;
import models.BatchSpecificMandatedElectives;
import models.Course;
import models.CourseSpecificHighPriorityStudents;
import models.CourseSpecificInsideDepartmentStudents;
import models.DepartmentSpecificMaxCreditLimit;
import models.Slot;
import models.Student;
import services.CheckInputFormats;
import services.CommonAlgorithmUtilities;
import services.Constants;
import services.ExchangeUnstablePairs;
import services.FirstPreferenceAllotmentAlgorithm;
import services.GenerateCoursePreference;
import services.GetStatistics;
import services.InputSanitization;
import services.IterativeHRalgorithm;
import services.MakeInputListsUnmodifiable;
import services.OutputSanitization;
import services.ReasonsForNotAllottingPreferences;
import services.SlotBasedHRalgorithm;
import services.dataOutput.*;
import services.dataInput.*;
import services.dataInput.dataTransformFromCSV.*;

/*
* This is the function where the execution of the program starts
*/
public class ExecuteStepsForDB {

	public static boolean GUIpresentGlobal;

	public static void executeDBSteps(String slotsFile, String studentListFile, String courseListFile, String studentPreferenceListFile, String coursePreferenceListFile, boolean generateFreshCoursePreferences, int algorithm, String outputFolder, String insideDepartmentConfigFile, String highPriorityCoursePreferencesConfigFile, String departmentWiseMaxCreditLimitFile, String batchSpecificMandatedElectivesFile, String nameOfTheDatabase, boolean GUIpresent) throws IOException {

		String errorMsg;
		//Check slots file format
		errorMsg = CheckInputFormats.checkSlotsFileFormat(slotsFile);
		if (errorMsg!=null){
			printMessage("Exiting due to error in the format of the slots file. " + errorMsg);
			System.exit(1);
		}

		//Check student list file format
		errorMsg = CheckInputFormats.checkStudentListFileFormat(studentListFile);
		if (errorMsg!=null){
			printMessage("Exiting due to error in the format of the studentList file. " + errorMsg);
			System.exit(1);
		}

		//Checking course list file fomat
		errorMsg = CheckInputFormats.checkCourseListFileFormat(courseListFile);
		if (errorMsg!=null){
			printMessage("Exiting due to error in the format of the course list file. " + errorMsg);
			System.exit(1);
		}

		//Checking the student preference list file format
		errorMsg = CheckInputFormats.checkStudentPreferenceListFileFormat(studentPreferenceListFile);
		if (errorMsg!=null){
			printMessage("Exiting due to error in the format of the student preference list file. " + errorMsg);
			System.exit(1);
		}

		//Checking the course preference list file format if we are taking it as an input (and not generating a fresh list)
		if (generateFreshCoursePreferences==false){
			errorMsg = CheckInputFormats.checkCoursePreferenceListFileFormat(coursePreferenceListFile);
			if (errorMsg!=null){
				printMessage("Exiting due to error in the format of the course preference list file. " + errorMsg);
				System.exit(1);
			}
		}

		//Check insideDepartmentConfigFile file format
		errorMsg = CheckInputFormats.checkInsideDepartmentConfigFileFormat(insideDepartmentConfigFile);
		if (errorMsg!=null){
			printMessage("Exiting due to error in the format of the studentList file. " + errorMsg);
			System.exit(1);
		}

		//Check specialCoursePreferencesConfigFile file format
		errorMsg = CheckInputFormats.checkHighPriorityCoursePreferencesConfigFileFormat(highPriorityCoursePreferencesConfigFile);
		if (errorMsg!=null){
			printMessage("Exiting due to error in the format of the studentList file. " + errorMsg);
			System.exit(1);
		}

		//Check departmentWiseMaxCreditLimitFile file format
		errorMsg = CheckInputFormats.checkDepartmentWiseMaxCreditLimitFileFormat(departmentWiseMaxCreditLimitFile);
		if (errorMsg!=null){
			printMessage("Exiting due to error in the format of the studentList file. " + errorMsg);
			System.exit(1);
		}

		//Write to Database
		printProgressNotification("Writing Output to DB....");


		Date date = new Date();
		long time = date.getTime();

		Files.copy(new File("config/config.cfg").toPath(), new File("config/configTemp" + time + ".cfg").toPath());
		File file = new File("config/config.cfg");
		FileWriter fr = new FileWriter(file, true);
		fr.write("dbname = db_seat_" + nameOfTheDatabase + "_" + time);
		fr.close();
		printProgressNotification("Creating new DB....");

		Properties configFile = new java.util.Properties();
		try {
			FileInputStream stream = new FileInputStream(new File("config/config.cfg"));
			configFile.load(stream);


			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://" +
			configFile.getProperty("hostname") +
			":" +
			configFile.getProperty("port") +
			"/" +
			"?autoReconnect=true&useSSL=false",
			configFile.getProperty("username"), configFile.getProperty("password"));


			Statement statement = connection.createStatement();
			int Result=statement.executeUpdate("CREATE DATABASE IF NOT EXISTS `db_seat_" + nameOfTheDatabase + "_" + time + "` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci");
			statement.close();


			Files.copy(new File("sql/db_seat.sql").toPath(), new File("sql/db_seat" + time + ".sql").toPath());
			file = new File("sql/db_seat.sql");
			RandomAccessFile f = new RandomAccessFile(file, "rw");
			f.seek(0);
			f.write(("use `db_seat_" + nameOfTheDatabase + "_" + time + "`;").getBytes());
			f.close();
			InputStream inputstream = new FileInputStream("sql/db_seat.sql");
			executeScript(connection, inputstream);
			// Init DB code here

			ImportBatchSpecificMandatedElectives.execute(batchSpecificMandatedElectivesFile);
			ImportSlots.execute(slotsFile);
			ImportCourses.execute(courseListFile);
			ImportHighPriority.execute(highPriorityCoursePreferencesConfigFile);
			ImportInsideDepartmentSpec.execute(insideDepartmentConfigFile);
			ImportMaxCrediLimit.execute(departmentWiseMaxCreditLimitFile);
			ImportStudentList.execute(studentListFile);
			ImportCoursePreferenceList.execute(coursePreferenceListFile);
			ImportStudentPreferenceList.execute(studentPreferenceListFile);
			ImportExchangeUnstablePairs.execute(outputFolder + "/exchangeUnstablePairs.csv");
			ImportOutput.execute(outputFolder + "/output.csv");

			new File("config/config.cfg").delete();
			Files.copy(new File("config/configTemp" + time + ".cfg").toPath(), new File("config/config.cfg").toPath());
			new File("config/configTemp" + time + ".cfg").delete();
			new File("sql/db_seat.sql").delete();
			Files.copy(new File("sql/db_seat" + time + ".sql").toPath(), new File("sql/db_seat.sql").toPath());
			new File("sql/db_seat" + time + ".sql").delete();
		} catch (Exception eta) {
		printProgressNotification("Error occured while creating new DB....");
			eta.printStackTrace();
		}
	}

	//Just a function which sends the message to be printed to the correct output
	public static void printMessage(String s){
		if (GUIpresentGlobal == true){
			MainWithGUI.displayMessage(s);
		}
		else{
			MainWithoutGUI.displayMessage(s);
		}
	}

	//Just a function which sends the progress notification to be printed to the correct output
	//The progress notification just notifies how many steps of the allotment have been completed
	public static void printProgressNotification(String s){
		if (GUIpresentGlobal == true){
			MainWithGUI.printProgress(s);
		}
		else{
			MainWithoutGUI.printProgress(s);
		}
	}

	public static void executeScript(Connection conn, InputStream in)
	throws SQLException
	{
		Scanner s = new Scanner(in);
		s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

		Statement st = null;

		try
		{
			st = conn.createStatement();

			while (s.hasNext())
			{
				String line = s.next().trim();

				if (!line.isEmpty())
				st.execute(line);
			}
		}
		finally
		{
			if (st != null)
			st.close();
		}
	}

}
