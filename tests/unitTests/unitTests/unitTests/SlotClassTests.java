package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import models.ClassTiming;
import models.Slot;

import org.junit.Test;

import services.DataInput;

public class SlotClassTests {
	@Test
	public void getSlotByNameTest() {
		ArrayList<Slot> slotList = new ArrayList<Slot>();
		//Create 3 new slot objects. We only care about the name of the slot. Put random values for everything else
		Slot s1 = new Slot ("A",null);
		Slot s2 = new Slot ("B",null);
		Slot s3 = new Slot ("A1",null);
		
		//Add the 3 slots to the slotlist
		slotList.add(s1);
		slotList.add(s2);
		slotList.add(s3);
		
		//Make some queries
		Slot result1 = Slot.getSlotByName("A", slotList);
		Slot result2 = Slot.getSlotByName("", slotList);
		Slot result3 = Slot.getSlotByName("A2", slotList);
		
		//Check if the queries give the expected output
		assertEquals(s1, result1);
		assertEquals(null, result2);
		assertEquals(null, result3);
	}

	@Test
	public void clashesWithSlotTest() {
		//Create some class timings
		ClassTiming ct1 = new ClassTiming(8,9,0,0,"MON");
		ClassTiming ct2 = new ClassTiming(8,9,0,0,"MON");
		ClassTiming ct3 = new ClassTiming(9,10,0,0,"MON");
		ClassTiming ct4 = new ClassTiming(8,9,0,0,"TUE");
		ClassTiming ct5 = new ClassTiming(8,9,30,30,"TUE");
		ClassTiming ct6 = new ClassTiming(14,17,0,0,"TUE");
		ClassTiming ct7 = new ClassTiming(15,16,0,0,"TUE");
		
		//Add these class timings to some slot objects
		Slot s1 = new Slot ("A",new ArrayList<ClassTiming>(Arrays.asList(ct1)));
		Slot s2 = new Slot ("A",new ArrayList<ClassTiming>(Arrays.asList(ct2)));
		Slot s3 = new Slot ("B",new ArrayList<ClassTiming>(Arrays.asList(ct3,ct4)));
		Slot s5 = new Slot ("A1",new ArrayList<ClassTiming>(Arrays.asList(ct5)));
		Slot s6 = new Slot ("A1",new ArrayList<ClassTiming>(Arrays.asList(ct6)));
		Slot s7 = new Slot ("A1",new ArrayList<ClassTiming>(Arrays.asList(ct7)));
		
		//Check if the queries give the expected output
		assertTrue(s1.clashesWithSlot(s2));
		assertFalse(s1.clashesWithSlot(s3));
		assertTrue(s3.clashesWithSlot(s5));
		assertTrue(s6.clashesWithSlot(s7));
	}
	
	
	@Test
	public void clashesWithSlotTest2() {
		//Read the slot timings from the slot config file.
		//This function expects the presence of a slot config file named 'slot_config.csv'
		//This test may need to be changed if the timetable changes
		ArrayList<Slot> slots = DataInput.populateSlots("slot_config.csv");
			
		Slot gSlot = Slot.getSlotByName("G", slots);
		Slot pSlot = Slot.getSlotByName("P", slots);
		Slot hSlot = Slot.getSlotByName("H", slots);
		Slot qSlot = Slot.getSlotByName("Q", slots);
				
		//Check if the queries give the expected output
		assertTrue(pSlot.clashesWithSlot(hSlot));
		assertFalse(pSlot.clashesWithSlot(gSlot));
		assertFalse(pSlot.clashesWithSlot(qSlot));
	}

}
