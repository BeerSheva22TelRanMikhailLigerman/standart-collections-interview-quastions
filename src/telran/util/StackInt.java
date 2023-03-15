package telran.util;

import java.util.LinkedList;

public class StackInt {
	//HW33
	//Write the following methods, with standard collections (linkedList)
	//All methods should have complexity O[1]
	LinkedList<Integer> list = new LinkedList<>();
	LinkedList<Integer> maxValues = new LinkedList<>();
	
	public void push(int num) {
		//adds num into top of stack
		list.addLast(num);
		if (maxValues.isEmpty() || maxValues.getLast() <= num) {	//the second condition not test, if the first condition is true
			maxValues.addLast(num);			
		}
	}
	public int pop() {
		//returns a number from top of stack or throws NoSuchElementException
		//if the stack is empty
		int res = list.getLast();
		list.removeLast();
		if (res == maxValues.getLast()) {
			maxValues.removeLast();
		}
		return res;
	}
	public boolean isEmpty () {
		//returns true if the stack is empty, otherwise false
		return list.isEmpty();
	}
	public int getMax() {
		//returns maximal value of the stack or throws NoSuchElementException
		//if the stack is empty
		return maxValues.getLast();
	}
}