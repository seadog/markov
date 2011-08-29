package uk.co.ignignokt.markov.word;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WordList {
	private Map<String, Word> words;
	
	public WordList(){
		words = new HashMap<String, Word>();
	}
	
	public void addWord(int i, String word, String next){
		Word entry = words.get(word);
		Word to = words.get(next);
		
		if(entry == null){
			if(i == 0)
				entry = new Word(word, true);
			else
				entry = new Word(word);
			words.put(word, entry);
		}

		if(to == null){
			to = new Word(next);
			words.put(next, to);
		}

		entry.addWord(to);
	}
	
	public Word getRandom(){
		Random generator = new Random();
		int randomInt = generator.nextInt(words.size());

		Object[] randoms = words.values().toArray();

		return (Word) randoms[randomInt];
	}
}
