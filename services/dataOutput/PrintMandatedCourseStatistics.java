package services.dataOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.BatchSpecificMandatedElectives;

public class PrintMandatedCourseStatistics{
	/**
	 * This file prints the statistics for students who have a mandated elective
	 * (usually HS or MA).
	 * It counts the number of students in the class, and the number of students
	 * who got allotted the mandated elective.
	 * @param studentList
	 * @param batchSpecificMandatedElectiveList
	 * @param outputFile
	 */
	public static void execute(ArrayList<BatchSpecificMandatedElectives> batchSpecificMandatedElectiveList, String outputFile){
		//Open output file for writing
		try{
			File outfile = new File(outputFile);
			if (!outfile.exists()) {
				outfile.createNewFile();
			}
			FileWriter fw = new FileWriter(outfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			//Header line for file
			bw.write("Batch,Elective Type,Total Strength,Number of students who didn't give a preference for this elective type,Total Allotted\n");

			//Loop through all the batches that had a mandated elective
			for(BatchSpecificMandatedElectives m : batchSpecificMandatedElectiveList){
				//Just print the statstics computed for this batch
				bw.write(m.batch);
				bw.write(",");
				bw.write(m.electiveType);
				bw.write(",");
				bw.write(String.valueOf(m.numOfStudentsOfThisBatchTotal));
				bw.write(",");
				bw.write(String.valueOf(m.numOfStudentsOfThisBatchNotGivenPreferencesForThisElectiveType));
				bw.write(",");
				bw.write(String.valueOf(m.numOfStudentsOfThisBatchAllottedThisElectiveType));
				bw.write("\n");
			}
			bw.close();

		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}