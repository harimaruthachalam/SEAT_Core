package services.dataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.BatchSpecificMandatedElectives;

public class PopulateBatchSpecificMandatedElectiveType{
	/**
	 * This function reads the config file which specifies certain batches for which few elective types are recommended
	 * @param inpFile - name of the input file
	 * @param errorMsgList - holder for any error message
	 * @return The list of BatchSpecificMandatedElectives which contain information about batches and the mandated electives
	 */
	public static ArrayList<BatchSpecificMandatedElectives> execute(String inpFile) {
		//Some declarations
		String line;
		String [] inputLine;
		String splitBy = ",";
		ArrayList<BatchSpecificMandatedElectives> batchSpecificMandatedElectiveTypeInfo = new ArrayList<BatchSpecificMandatedElectives>();

		//reading input line by line and adding a new student for every line.
		try{
			BufferedReader br = new BufferedReader(new FileReader(inpFile));
			//Skip the first line since it will be the header row
			br.readLine();
			//read line by line
			while ((line = br.readLine()) != null) {  
				line.replaceAll("\\s+",""); //Remove all whitespace
				inputLine = line.split(splitBy);

				String batch = inputLine[0];
				for(int i=1;i<inputLine.length;i++){  //loop through the elective types and add BatchSpecificMandatedElectiveType object.
					String electiveType = inputLine[i];
					BatchSpecificMandatedElectives tempMandatedElectiveTypeInfo = new BatchSpecificMandatedElectives(batch,electiveType);
					batchSpecificMandatedElectiveTypeInfo.add(tempMandatedElectiveTypeInfo);
				}    
			}
			br.close();//closing file pointer
		//just some exception handling
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return batchSpecificMandatedElectiveTypeInfo;
	}
}