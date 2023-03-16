package telran.util;

import java.util.HashMap;

public class MyArrayInt {		//need to build new HashMap only when set value by index 
	int value;
	int size;
	HashMap<Integer, Integer> array;
		public MyArrayInt(int size, int value) {
			this.size = size;
			this.value = value;
		}
		public void set(int index, int value) {
			//sets a given value at a given index
			//throws exception IndexOutOfBoundsException
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
			if (array == null) {
				array = new HashMap<>();
			}
			array.put(index, value);
		}
		public int get(int index) {
			//returns a value at a given index
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
			int res = value;
			if (array != null) {
				res = array.getOrDefault(index, value);
			} 
			return res;
		}
		public void setAll(int value) {
			this.value = value;
			array = null;
		}
	}	

	/* My solution
	HashMap<Integer, Integer> hashMap = new HashMap<>();	
	int size;
	boolean setAll;
	
	public MyArrayInt(int size, int value) {
		hashMap.put(size, value);
		this.size = size;
		setAll = true;
	}
	public void set(int index, int value) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		hashMap.put(index, value);
		setAll = false;
	}
	
	public int get(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		if (setAll || hashMap.get(index) == null) {return hashMap.get(size);}
		else return hashMap.get(index);
	}
	
	public  void setAll (int value) {
		hashMap.put(size, value);
		setAll = true;
	}
}
*/