package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.structure.*;

class MulticountersTest {
   MultiCounters multiCount;
	@BeforeEach
	
	void setUp() throws Exception {
		
		multiCount = new MultiCounter();
		
		multiCount.addItem(10);
		multiCount.addItem("abc");
		multiCount.addItem(10);
		multiCount.addItem(10);
		multiCount.addItem("abc");
		
		multiCount.addItem("lmn");
		multiCount.addItem("lmn");	
	}
	
	
	
	

	@Test
	
	void getMaxItemsTest() {
		runTest(Arrays.asList(10));
	}

	
	
	@Test
	
	void getValueTest() {
		assertEquals(3, multiCount.getValue(10));
		assertNull(multiCount.getValue("kuku"));
	}
	@Test
	
	void addItemTest() {
		Object[] items = {10, "abc"};
		assertEquals(3,multiCount.addItem("abc"));
		runTest(Arrays.asList(items));

	}
	@Test
	
	void removeItemTest() {
		Object[] items = {"abc", "lmn"};
		assertTrue(multiCount.remove(10));
		runTest(Arrays.asList(items));
		assertFalse(multiCount.remove(10));
	}
	
	private void runTest(List<Object> list) {
		var set = multiCount.getMaxItems();
		list.forEach((item)-> assertTrue(set.contains(item)));
	}


}