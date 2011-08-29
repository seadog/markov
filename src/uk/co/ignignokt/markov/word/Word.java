package uk.co.ignignokt.markov.word;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Word {
	private String text;
	private List<Word> next;
	
	boolean start = false;
	boolean end = false;

	public Word(String text){
		next = new ArrayList<Word>();
		
		this.text = text;
		
		if(Character.isUpperCase(text.charAt(0))){
			start = true;
		}
		
		if(text.charAt(text.length()-1) == '.'){
			end = true;
		}
	}
	
	public boolean isStart(){
		return start;
	}
	
	public boolean isEnd(){
		return end;
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
