package services.dataOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Batch;

public class PrintBatchWiseAllottmentStatistics{
	public static void execute(ArrayList<Batch> listOfbatches,String outputFile) throws IOException {
		//Open output file for writing
		File outfile = new File(outputFile);
		if(!outfile.exists()){
			outfile.createNewFile();
		}
		FileWriter fw = new FileWriter(outfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		//Header line for file
		bw.write("Batch,Total Strength,Number of students with non empty preference list,Number of credits allotted,Ratio,\n");
		for(Batch b : listOfbatches){
			bw.write(b.getBatch());
			bw.write(",");
			bw.write(String.valueOf(b.numOfStudentsOfThisBatchTotal));
			bw.write(",");
			bw.write(String.valueOf(b.numOfStudentsOfThisBatchWithNonEmptyPreferenceList));
			bw.write(",");
			bw.write(String.valueOf(b.totalCreditsAllottedToThisBatch));
			bw.write(",");
			bw.write(String.valueOf(b.creditsPerStudent));
			bw.write("\n");
		}
		bw.close();
	}

}