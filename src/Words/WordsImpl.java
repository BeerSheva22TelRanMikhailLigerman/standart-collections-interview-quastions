package Words;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class WordsImpl implements Words {
	TreeSet<String> stringsTree = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

	@Override
	public boolean addWord(String word) {		
		return stringsTree.add(word);
	}

	@Override
	public List<String> getWordsByPrefix(String prefix) {
				return new ArrayList<String>(stringsTree.subSet(prefix, nextPrefix(prefix)));
	}

	private String nextPrefix(String prefix) {
		char last = prefix.charAt(prefix.length() - 1);
		
		return prefix.substring(0, prefix.length() - 1) + (char) (last + 1);
	}
	

}
