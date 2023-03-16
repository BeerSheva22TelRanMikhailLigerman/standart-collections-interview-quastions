package telran.structure;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

//HW34
public class MultiCounter implements MultiCounters {
	private HashMap<Object, Integer> items = new HashMap<>();
	private TreeMap<Integer, HashSet<Object>> counters = new TreeMap<>();
	
	@Override
	public Integer addItem(Object item) {	
		return items.containsKey(item) ? add(item) : addFirst(item);		
	}

	private Integer add(Object item) {
		int prevCount = items.get(item);
		items.put(item, prevCount + 1);
		moveCounters(prevCount, item);
		return prevCount + 1;
	}

	private void moveCounters(int prevValue, Object item) {	//will good to move removeCountersItem and addCountersItem to separated method
		HashSet<Object> set = counters.get(prevValue);
		set.remove(item);
		if (set.isEmpty()) {
			counters.remove(prevValue);
			}
		if (counters.containsKey(prevValue + 1)) {
			set = counters.get(prevValue + 1);
			set.add(item);
		} else {
			set = new HashSet<>();
			set.add(item);
			counters.put(prevValue + 1, set);
		}			
	}

	private Integer addFirst(Object item) {
	items.put(item, 1);
	if (counters.containsKey(1)) {
		(counters.get(1)).add(item);
	} else {
		HashSet<Object> set = new HashSet<>();
		set.add(item);
		counters.put(1, set);
	}	
	return 1;
}

	@Override
	public Integer getValue(Object item) {		
		return items.get(item);
	}

	@Override
	public boolean remove(Object item) {
		boolean res = false;
		if (items.containsKey(item)) {
			//int count = items.get(item);
			//items.remove(item);
			int count = items.remove(item);		//method remove() return value
			HashSet<Object> set = counters.get(count);
			set.remove(item);
			if (set.isEmpty()) {
				counters.remove(count);
				}
			res = true;
		}		
		return res;
	}

	@Override
	public Set<Object> getMaxItems() {
		
		return counters.isEmpty() ? null : counters.lastEntry().getValue();
	}
	/**
	 * Entry<Integer, HashSet<Object>> lastEntry = counters.lastEntry();
		return lastEntry != null ? lastEntry.getValue() : Collections.EMPTY_SET;
		
		instead of "Entry<Integer, HashSet<Object>> lastEntry" possible to use "var"
		
		in case of collections never return null!
	 */
	
	
	
	

}
