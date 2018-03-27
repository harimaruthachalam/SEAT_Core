package models;

/**
 * Represents a batch of students (like CS16). The batch is represented as a string.
 *
 */
public class Batch{
	private String batch;
	public int numOfStudentsOfThisBatchTotal;
	public int numOfStudentsOfThisBatchWithNonEmptyPreferenceList;
	public int totalCreditsAllottedToThisBatch;
	public float creditsPerStudent;

	public Batch(String inp_batch){
		batch = inp_batch;
	}

	public String getBatch(){
		return batch;
	}
}