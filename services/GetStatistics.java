package services;

import java.util.ArrayList;
import java.util.Collections;

import models.Batch;
import models.BatchSpecificMandatedElectives;
import models.Course;
import models.Student;
import models.StudentPreference;

/**
 *This file contains all statistics related functions. It is mainly used for computing statistics
 *pertaining to an allotment this is completed
 */
public class GetStatistics{
   
	//Self explanatory
	public static double getMean(ArrayList<Double> array) {
		double total=0;
		double size = (double) array.size();
		for (double element : array){
			total += element;
		}
		return (total/size);
	}

	//Self explanatory
	public static double getStandardDeviation(ArrayList<Double> array) {
		double varianceTotal = 0;
		double size = (double) array.size();
		double mean = getMean(array);
		double variance;
		
		for (double element : array){
			varianceTotal += (element-mean)*(element-mean);
		}
		variance = varianceTotal/size;
		return Math.sqrt(variance);
	}

	//Self explanatory
	public static double getLowest10Percentile(ArrayList<Double> array) {
		//Copy the array first because you do not want to change the original array
		ArrayList<Double> arrayCopy = copyArrayList(array);
		//Sort it
		Collections.sort(arrayCopy);
		//Compute lowest 10 percentile
		double size = arrayCopy.size();
		int tenPercentileIndex = (int) (0.1*size);
		return arrayCopy.get(tenPercentileIndex);
	}

	//Self explanatory
	public static double getHighest10Percentile(ArrayList<Double> array) {
		//Copy the array first because you do not want to change the original array
		ArrayList<Double> arrayCopy = copyArrayList(array);
		//Sort it
		Collections.sort(arrayCopy);
		//Compute lowest 10 percentile
		double size = arrayCopy.size();
		int tenPercentileIndex = (int) (0.9*size);
		return arrayCopy.get(tenPercentileIndex);
	}
	
	/**
	 * Computes some per student statistics based on the allotted courses and stores it in the student object itself
	 * @param studentList
	 */
	public static void computePerStudentStatistics(ArrayList<Student> studentList){
		computeEffectiveAverageRanks(studentList);
		computeCreditSatisfactionRatios(studentList);
	}
	
	/**
	 * Computes the credit satisfaction ratio for each student, which is (Allotted Credits)/(Max Elective Credits)
	 * Read code documentation for more details
	 * @param studentList
	 */
    private static void computeCreditSatisfactionRatios(ArrayList<Student> studentList) {
		for (Student s : studentList){
			if (s.getMaxElectiveCreditsInSem()==0 || s.studentPreferenceList.size()==0){ //If the student had no capacity. Avoids a divide by 0 in the else part
    			s.creditSatisfactionRatio = 1;
    		}
			else{
				int totalAllottedCredits = 0;
				for (StudentPreference sp : s.orderedListOfcoursesAllotted){
					totalAllottedCredits += sp.getCourseObj().getCredits();
				}
				s.creditSatisfactionRatio = (double)(totalAllottedCredits) / (double)(s.getMaxElectiveCreditsInSem());
			}
		}
	}

    
    /**
     * Computes the 'Effective Average Rank' statistic for every student
     * Effective Average Rank = (Sum of Reduced Ranks)/(No of allotted courses)^2
     * 
     * Sum of reduced ranks - each course's rank is its own rank minus the number of courses above it in the preference
     * list that were removed due to some kind of conflict.
     * 
     * Read code documentation for more details
     * @param studentList
     */
	private static void computeEffectiveAverageRanks(ArrayList<Student> studentList){
    	for (Student s : studentList){
    		if (s.orderedListOfcoursesAllotted.size()==0){ //If nothing was allotted. Avoids a divide by 0 in the else part
    			s.effectiveAverageRank = 0;
    		}
    		else{
    			//Just a copy of the original preference list since we do not want to modify it
        		ArrayList<StudentPreference> preferenceList = copyArrayList(s.studentPreferenceList);
        		//A set of courses that were allotted upto some rank i. We need to use it to remove clashing courses further down
        		ArrayList<StudentPreference> allottedCoursesSoFar = new ArrayList<StudentPreference>();
        		
        		double totalRank = 0; //Is the sum of the ranks of courses allotted to a student
        		int index = 1; //This is the the effective rank of a preference. We will account for the inside-outside department courses as 1. Also we will not count the conflicting courses
        		
        		//Looping through the student's preference list
        		for (int i=0;i<preferenceList.size();i++){
        			if (preferenceList.get(i).getCourseObj().isAnInsideDeptCourse()==true){ //If this is an inside department course
        				if (s.orderedListOfcoursesAllotted.contains(preferenceList.get(i))){ //If the student was allotted that preference
            				totalRank += index; //compute the new totalRank
            				index++; //Increment the index for the next course on the preference list to be counted
            				allottedCoursesSoFar.add(preferenceList.get(i));
            				i++; //Increment the value of i since we want to skip the outside department version of the course, because the student already got allotted to the inside department version of it
            			}
        				//else if the student was not allotted to this course, skip it
        			}
        			else{ //If the course is an outside department course
        				if (s.orderedListOfcoursesAllotted.contains(preferenceList.get(i))){ //If the student was allotted that preference
            				totalRank += index; //compute the new totalRank
            				index++; //Increment the index for the next course on the preference list to be counted
            				allottedCoursesSoFar.add(preferenceList.get(i));
        				}
        				else{//If the student was not allotted this preference
        					if (clashes(preferenceList.get(i),allottedCoursesSoFar,s.getMaxElectiveCreditsInSem())){ //Check if the preference clashes with a higher preference for some reason like colour,slot or credits getting over
            					;//Do nothing. Index should not get incremented in this case
            				}
        					else{
        						index++;
        					}
        				}
        			}
        		}
        		s.effectiveAverageRank = totalRank/ (double)(s.orderedListOfcoursesAllotted.size()*s.orderedListOfcoursesAllotted.size()); //self explanatory formula
    		}
    	}
    }
	
	
	/**
	 * Computes the per batch statistics like the number of students in the batch, 
	 * the number of students with an empty preference list, and the average credits
	 * allotted per student. It helps us get an idea of how well the allotment has
	 * been done for each batch. Bad statistics are indicative of possible issues
	 * in the input data 
	 * @param studentList
	 * @param listOfbatches
	 */
    public static void computeBatchWiseAllotmentStatistics(ArrayList<Student> studentList, ArrayList<Batch> listOfbatches) {
    	for(Batch b : listOfbatches){
			String batch = b.getBatch();

			b.numOfStudentsOfThisBatchTotal = 0;
			b.numOfStudentsOfThisBatchWithNonEmptyPreferenceList = 0;
			b.totalCreditsAllottedToThisBatch = 0;
			b.creditsPerStudent = (float)0.0;
			
			// Now for this batch we need to know the number of electives for all students.
			for(Student s : studentList){
				String rollNo = s.getRollNo();
				String thisStudentBatch = rollNo.substring(0,4);

				if(thisStudentBatch.equals(batch)){
					// Increment the number of students of this batch by 1
					b.numOfStudentsOfThisBatchTotal = b.numOfStudentsOfThisBatchTotal + 1;

					if(s.studentPreferenceList.size()!=0){
						// The student gave a non-empty preference list.
						b.numOfStudentsOfThisBatchWithNonEmptyPreferenceList = b.numOfStudentsOfThisBatchWithNonEmptyPreferenceList + 1;

						for(StudentPreference sp : s.orderedListOfcoursesAllotted){
							// For all the allotted courses add the number of credits
							b.totalCreditsAllottedToThisBatch = b.totalCreditsAllottedToThisBatch + sp.getCourseObj().getCredits();
						}	
					}
					
				}
			}

			if(b.numOfStudentsOfThisBatchWithNonEmptyPreferenceList!=0){
				b.creditsPerStudent = (float)b.totalCreditsAllottedToThisBatch/(float)b.numOfStudentsOfThisBatchWithNonEmptyPreferenceList;
			}
		}
    }
    
    
	/**
	 * Computes statistics for each batch that has a mandated elective (usually HS or MA)
	 * It computes the number of students in the batch, and the number of students
	 * who were allotted an elective of this type, and the number of students
	 * who did not even give preferences for a course of this type. Bad statistics
	 * are indicative of errors in input data
	 * @param studentList
	 * @param batchSpecificMandatedElectiveList
	 */
    public static void computeMandatedStudentStatistics(ArrayList<Student> studentList,ArrayList<BatchSpecificMandatedElectives> batchSpecificMandatedElectiveList) {
    	//Loop through all the batches that had a mandated elective
		for(BatchSpecificMandatedElectives m : batchSpecificMandatedElectiveList){
			String batch = m.getBatch();
			String electiveType = m.getElectiveType();

			m.numOfStudentsOfThisBatchTotal = 0;
			m.numOfStudentsOfThisBatchAllottedThisElectiveType = 0;
			m.numOfStudentsOfThisBatchNotGivenPreferencesForThisElectiveType = 0;

			for(Student s : studentList){
				String rollNo = s.getRollNo();
				String thisStudentBatch = rollNo.substring(0,4);
				
				//For the counting, we only want students of this 'batch'
				if(thisStudentBatch.equals(batch)){
					// Increment the number of students of this batch by 1
					m.numOfStudentsOfThisBatchTotal = m.numOfStudentsOfThisBatchTotal + 1;

					for(StudentPreference sp : s.orderedListOfcoursesAllotted){
						// Get the type of elective
						String thisCourseNumber = sp.getCourseObj().getcourseNumber();
						String thisElectiveType = thisCourseNumber.substring(0,2);

						if(thisElectiveType.equals(electiveType)){
							// Increment the number of students of this batch who have got this elective type allotted by 1
							m.numOfStudentsOfThisBatchAllottedThisElectiveType = m.numOfStudentsOfThisBatchAllottedThisElectiveType + 1;
							break; // Important to break from the loop because otherwise it might count multiple times.
						}
					}

					//Now we additionally want to count how many students even gave a preference for this type of elective
					boolean didStudentGiveAPreferenceForThisElectiveType = false;
					for(StudentPreference sp : s.studentPreferenceList){ //Note that we are not looking at the invalidPreferences here because we do not count those as valid preferences
						// Get the course number
						String thisCourseNumber = sp.getCourseObj().getcourseNumber();
						String thisElectiveType = thisCourseNumber.substring(0,2);

						if(thisElectiveType.equals(electiveType)){
							// Reached here implies that there exists a course in the student preference which is of this elective type.
							didStudentGiveAPreferenceForThisElectiveType = true; // mark the flag as true
							break;
						}
					}

					// check for the flag.
					if(didStudentGiveAPreferenceForThisElectiveType==false){
						// Reached here implies that this student has not given any preference for this elective type.
						m.numOfStudentsOfThisBatchNotGivenPreferencesForThisElectiveType = m.numOfStudentsOfThisBatchNotGivenPreferencesForThisElectiveType + 1;
					}
				}
			}
		}
	}
    
	/* PREVIOUS VERSION OF THE FUNCTION
	 * private static void computeEffectiveAverageRanks(ArrayList<Student> studentList){
    	for (Student s : studentList){
    		if (s.coursesAllotted.size()==0){ //If nothing was allotted. Avoids a divide by 0 in the else part
    			s.effectiveAverageRank = 0;
    		}
    		else{
    			//Just a copy of the original preference list since we do not want to modify it
        		ArrayList<StudentPreference> preferenceList = copyArrayList(s.studentPreferenceList);
        		//A set of courses that were allotted upto some rank i. We need to use it to remove clashing courses further down
        		ArrayList<StudentPreference> allottedCoursesSoFar = new ArrayList<StudentPreference>();
        		
        		double totalRank = 0; //Is the sum of the ranks of courses allotted to a student
        		int removedCourses = 0; //This is the number of courses removed up to that point because of a clash with a higher preference course that was allotted. We do not want to add these removed courses when calculating the totalRank
        		
        		//Looping through the student's preference list
        		for (int i=0;i<preferenceList.size();i++){
        			if (s.coursesAllotted.contains(preferenceList.get(i))){ //If the student was allotted that preference
        				totalRank += i + 1 - removedCourses; //compute the new totalRank
        				allottedCoursesSoFar.add(preferenceList.get(i));
        			}
        			else{ //If the student was not allotted this preference
        				if (clashes(preferenceList.get(i),allottedCoursesSoFar,s.getMaxElectiveCreditsInSem())){ //Check if the preference clashes with a higher preference for some reason like colour,slot or credits getting over
        					removedCourses += 1; //Increment the count of the number of courses removed because of the condition in the 'if' part
        				}
        			}
        		}
        		s.effectiveAverageRank = totalRank/ (double)(s.coursesAllotted.size()*s.coursesAllotted.size()); //self explanatory formula
    		}
    	}
    }
	 */
	
	
	/* HELPER FUNCTIONS */
	
	/*
	 * Tells you if a given studentPreference clashes with any of the allotted courses in terms of
	 * a) slot clash
	 * b) colour clash
	 * c) insufficient credits left due to the set of allotted courses
	 */
	private static boolean clashes(StudentPreference preference,ArrayList<StudentPreference> allottedCoursesSoFar, int maxElectiveCreditsInSem) {
		//Check for slot and colour clashes
		for (StudentPreference sp : allottedCoursesSoFar){
			if (sp.getCourseObj().slotClashWithCourse(preference.getCourseObj()) || sp.getColour()==preference.getColour()){
				return true;
			}
		}
		
		//Check if total number of credits will overshoot if we allot the preference
		int allottedCredits = 0;
		for (StudentPreference sp : allottedCoursesSoFar){
			allottedCredits += sp.getCourseObj().getCredits();
		}
		if (preference.getCourseObj().getCredits() > maxElectiveCreditsInSem - allottedCredits){
			return true;
		}
		
		//No clashes found. Return false
		return false;
	}

	//Just a helper function used to do a deep copy
	private static <T> ArrayList<T> copyArrayList (ArrayList<T> inputList){
		ArrayList<T> copiedList = new ArrayList<T>();
		
		for (int i=0;i<inputList.size();i++){
			copiedList.add(inputList.get(i));
		}
		return copiedList;		
	}

	
}