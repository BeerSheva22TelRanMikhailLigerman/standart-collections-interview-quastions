package telran.test;

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
	
	


}