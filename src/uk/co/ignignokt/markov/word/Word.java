package uk.co.ignignokt.markov.word;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Word {
	private String text;
	private List<Word> next;
	
	boolean start = false;

	public Word(String text){
		next = new ArrayList<Word>();
		
		this.text = text;

		if(Character.isUpperCase(text.charAt(0))){
			start = true;
		}
	}

	public List<Word> getChildren(){
		return next;
	}

	public boolean isStart(){
		return start;
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
		int randomInt = generator.nextInt(next.size());
		
		return next.get(randomInt);
	}
}
