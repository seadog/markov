package uk.co.ignignokt.markov;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class WordList {
	List<Word> words;
	
	public WordList(){
		words = new ArrayList<Word>();
	}
	
	public void addWord(String word, String next){
		Word entry = null;
		Word to = null;
		
		for(Word x : words){
			if(x.getText().equals(word)){
				entry = x;
			}
			if(x.getText().equals(next)){
				to = x;
			}
		}
		
		if(entry == null){
			entry = new Word(word);
			words.add(entry);
		}
		if(to == null){
			to = new Word(next);
			words.add(to);
		}

		entry.addWord(to);
	}
	
	public Word getRandom(){
		Random generator = new Random();
		int randomInt = generator.nextInt(words.size());
		
		return words.get(randomInt);
	}
}
