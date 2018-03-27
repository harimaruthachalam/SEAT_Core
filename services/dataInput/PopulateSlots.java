package services.dataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.ClassTiming;
import models.Slot;

public class PopulateSlots{
	/**
	 * This function populates the slots by reading slot timings from the input
	 * file (whose name is passed as an argument).
	*/
	public static ArrayList<Slot> execute(String inpFile) {
		//declarations
		String line;
		String [] inputLine;
		ArrayList<Slot> slots = new ArrayList<Slot>();
		ArrayList<ClassTiming> tempClassTimings;

		try{
			BufferedReader br = new BufferedReader(new FileReader(inpFile));
			//Skip the first line since it will be the header row
			br.readLine();
			while ((line = br.readLine()) != null) {  //read line by line
				line.replaceAll("\\s+",""); //Remove all white spaces
				inputLine = line.split(","); //Split line on commas
	            String slotName = inputLine[0]; //First column of csv file is the slot name
	            
	            tempClassTimings = new ArrayList<ClassTiming>(); //Create a list of class timings for this slot
	            
		        for(int i=1;i<inputLine.length;i++){  //loop through the class timings and add them to the list
			        String [] timing = inputLine[i].split("="); //Split line on '='
			        String dayOfTheWeek = timing[0]; //First part is the day of the week
			        String[] duration = timing[1].split("-"); //Remaining part is the duration and is split again at '-'
			        String startTime = duration[0]; //The first part corresponds to the start time of the slot
			        String endTime = duration[1]; //The second part corresponds to the end time of the slot
			        int startHour = Integer.parseInt(startTime.split(":")[0]); //Get the hour part of the start time
			        int startMinute = Integer.parseInt(startTime.split(":")[1]); //Get the minute part of the start time
			        int endHour = Integer.parseInt(endTime.split(":")[0]); //Get the hour part of the end time
			        int endMinute = Integer.parseInt(endTime.split(":")[1]); //Get the minute part of the end time
			        tempClassTimings.add(new ClassTiming(startHour,endHour,startMinute,endMinute,dayOfTheWeek));
		        }
		        slots.add(new Slot(slotName,tempClassTimings)); //Add the new slot
            }
			br.close();//closing file pointer
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return slots;
	}

}