package uk.co.ignignokt.markov.word;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** TODO: must fix the crappy init/end stuff */
public class Word {
	private String text;
	private List<Word> next;
	
	private boolean initialWord = false;
	private boolean endWord = false;

	public Word(String text){
		next = new ArrayList<Word>();
		
		char initialCharacter = text.charAt(0);
		
		initialWord = Character.isUpperCase(initialCharacter);
		if(text.charAt(text.length()-1) == '.' && !initialWord){
			endWord = true;
		}
		
		this.text = text;
	}
	
	public boolean getInitWord(){
		return initialWord;
	}
	
	public boolean getEndWord(){
		return endWord;
	}
	
	public int getSize(){
		return next.size();
	}
	
	public String getText(){
		return this.text;
	}

	public void addWord(Word word){
		next.add(word);
	}

	public Word getNext(){
		Random generator = new Random();
		if(next.size() == 0) return null; // hack for end of paragraph, non-.'ed ends.

		int randomInt = generator.nextInt(next.size());
		
		return next.get(randomInt);
	}
}
