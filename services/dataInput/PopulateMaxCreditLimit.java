package services.dataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.DepartmentSpecificMaxCreditLimit;

public class PopulateMaxCreditLimit{
	/**
	 * This function reads the input of custom maxCredits limit (other than the default 60) that some departments  
	 * have specified
	 * @param inpFile
	 * @return - The list of DepartmentSpecificMaxCreditLimit which contain information about departments that
	 * have different maxCredits limits for their students (other than the default 60)
	 */
	public static ArrayList<DepartmentSpecificMaxCreditLimit> execute(String inpFile) {
		//Some declarations
		String line;
		String [] inputLine;
		String splitBy = ",";
		ArrayList<DepartmentSpecificMaxCreditLimit> deptSpecificMaxCreditLimitInfo = new ArrayList<DepartmentSpecificMaxCreditLimit>();
		
		//reading input line by line and adding a new student for every line.
		try{
			BufferedReader br = new BufferedReader(new FileReader(inpFile));
			//Skip the first line since it will be the header row
			br.readLine();
			//read line by line
			while ((line = br.readLine()) != null) {  
				line.replaceAll("\\s+",""); //Remove all whitespace
				inputLine = line.split(splitBy);
				String departmentAndYear = inputLine[0];
				String department = departmentAndYear.substring(0, 2);;
				int year = Integer.parseInt("20" + departmentAndYear.substring(2,4));
				int maxCreditsLimit = Integer.parseInt(inputLine[1]);
				DepartmentSpecificMaxCreditLimit tempLimit = new DepartmentSpecificMaxCreditLimit(department,year,maxCreditsLimit);
				deptSpecificMaxCreditLimitInfo.add(tempLimit);   
			}
			br.close();//closing file pointer
		//just some exception handling
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deptSpecificMaxCreditLimitInfo;
	}

}