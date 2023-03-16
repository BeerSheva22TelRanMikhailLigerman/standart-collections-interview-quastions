package telran.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void SubListtest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,70,-20));
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);

		System.out.println(listSub);
		listSub.add(1, -2);
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);

	}
	@Test
	@Disabled
	void displayOccurrencesCount() {
		String [] strings = {"lmn", "abc", "abc", "lmn", "a", "lmn"};
		Arrays.stream(strings)
				.collect( Collectors.groupingBy (s -> s,Collectors.counting() ) )
				.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));


	}
	//HW33
	@Test
	@Disabled
	void displayDigitStatistics() {
		//int[] array = getRandomArray(1000000);
		new Random().ints(1_000_000, 1, Integer.MAX_VALUE)
			.flatMap(x -> Integer.toString(x).chars())		//get stream of char
			.mapToObj(x -> x)								//stream of primitives to stream of objects //or .boxed()
			.collect(Collectors.groupingBy(x -> x, Collectors.counting()))
			.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
			.forEach(e -> System.out.printf("%c: %s\n", e.getKey(), e.getValue()));		//%c - use ASCII code
		
		
		
		//Generate 1000000 random numbers [1-Integer.MAX_VALUE)
		//Display digits and counts of their occurrences in descending order of the counts
		//consider using flatMap for getting many from one
		

		
	}

	private int[] getRandomArray(int size) {
		int[] res = new int[size];
		for (int i = 0; i < res.length; i++) {
			res[i] = getRandomValue(1, Integer.MAX_VALUE - 1);
		}
		return res;
	}

	private int getRandomValue(int min, int max) {
		int res = (int)(min + Math.random() * (max - min + 1));
		return res;
	}
	
	
	@Test
	@Disabled
	void stackIntTest() {
		StackInt stackInt = new StackInt();
		stackInt.push(1);
		stackInt.push(2);
		stackInt.push(3);
		stackInt.push(1);
		
		assertEquals(3, stackInt.getMax());
		assertEquals(1, stackInt.pop());
		assertEquals(3, stackInt.pop());
		assertEquals(2, stackInt.getMax());
	}
	//HW34 task 1 of 2
	@Test
	@Disabled
	void maxNumberWithNegativeImageTest() {
		int ar[] = {10000000, 3, -2, -200, 200, -3, 2};
		int ar1[] = {1000000, -1000000000, 3, -4};
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));
	}
	
	private Integer maxNumberWithNegativeImage(int[] ar) {
		int res = -1;
		Set<Integer> items = new HashSet<>(ar[0]);
		for (int i = 1; i < ar.length; i++) {
			if (items.contains(-ar[i])) {
				res = compare(res, Math.abs(ar[i]));
			} else {
				items.add(ar[i]);
			}
		}
		return res;		
	}

	private int compare(int res, int i) {		
		return res > i ? res : i;
	}
	
	/* O[n^2] ?
	private Integer maxNumberWithNegativeImage(int[] ar) {
		int res = -1;
		Set<Integer> items = new HashSet<>();
		Arrays.stream(ar).filter(x -> x < 0).forEach(x -> items.add(x));
		
		for (int i = 0; i < ar.length; i++)
			if (ar[i] > 0 && items.contains(-ar[i])) {
				if (ar[i] > res) {
					res = ar[i];
				}
			}
		return res;
	}	*/
	
	//HW34 task 2 of 2
	@Test
	void treeIteratingTest() {
		Integer array[] = {1, 11, 111, 32, 9, 1234, 99, 992};
		createAndIterateTreeInOrder(array);
	}
	private void createAndIterateTreeInOrder(Integer[] array) {
		// TODO 
		//create tree, add in tree numbers from a given array
		//and iterate in the order of array defined in 69
		TreeSet<Integer> treeSet = new TreeSet<Integer>((x1, x2) -> Integer.compare(sumDigits(x1), sumDigits(x2)));
		Arrays.stream(array).forEach(x -> treeSet.add(x));
		assertArrayEquals(array, treeSet.toArray());
		System.out.println(treeSet);
		

	}

	private int sumDigits(Integer x) { 
		return x.toString().chars().map(digit -> digit - '0').sum();
	}


}