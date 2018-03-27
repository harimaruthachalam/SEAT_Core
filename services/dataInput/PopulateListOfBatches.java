package services.dataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import models.Batch;
import models.Student;

public class PopulateListOfBatches{
	/**
	 * This function reads the studentList, which is an array of all students and makes a exhaustive list of possible batches.
	 * @param studentList - The array of students
	 * @return The list of Batches
	 */
	public static ArrayList<Batch> execute(ArrayList<Student> studentList){
		ArrayList<Batch> listOfBatches = new ArrayList<Batch>();

		HashMap<String,String> batches = new HashMap<String,String>();
		for(Student s : studentList){
			String rollNo = s.getRollNo();
			String batch = rollNo.substring(0,4);

			// If we have not yet added the batch add the batch
			if(!batches.containsKey(batch)){
				batches.put(batch,batch);
			}
		}

		for(String b : batches.keySet()){
			Batch batchToAdd = new Batch(b);
			listOfBatches.add(batchToAdd);
		}

		return listOfBatches;
	}

}