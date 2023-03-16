package telran.structure;

import java.util.HashSet;
import java.util.Set;

//HW34
public class MultiCounter implements MultiCounters {
//HashMap<Object, Integer> hashMap = new HashMap<>();
//TreeMap<Integer, HashSet<Object>> counters = new TreeMap<>();
	@Override
	public Integer addItem(Object item) {	
		return items.containsKey(item) ? add(item) : addFirst(item);
		
		
	}

	private Integer add(Object item) {
		int prevValue = items.get(item);		
		items.put(item, ++prevValue);
		moveCounters(prevValue, item);
		return prevValue++;
	}

	private void moveCounters(int prevValue, Object item) {
		counters.get(prevValue).remove(item);
		if (counters.containsKey(++prevValue)) {
			(counters.get(++prevValue)).add(item);
		} else {
			HashSet<Object> set = new HashSet<>();
			set.add(item);
			counters.put(++prevValue, set);
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
		boolean res = items.containsKey(item);
		items.remove(item);
		return res;
	}

	@Override
	public Set<Object> getMaxItems() {				
		return counters.isEmpty() ? null : counters.lastEntry().getValue();
	}

}
