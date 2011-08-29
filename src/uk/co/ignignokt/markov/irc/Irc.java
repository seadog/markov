package uk.co.ignignokt.markov.irc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Irc {
	private String text;
	private List<Irc> next;
	
	boolean start = false;
	boolean end = false;

	public Irc(String text){
		next = new ArrayList<Irc>();
		
		this.text = text;
	}
	
	public List<Irc> getChildren(){
		return next;
	}
	
	public void setStart(boolean value){
		this.start = value;
	}
	
	public void setEnd(boolean value){
		this.end = value;
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

	public void addWord(Irc word){
		next.add(word);
	}

	public Irc getNext(){
		Random generator = new Random();
		int randomInt = generator.nextInt(next.size());
		
		return next.get(randomInt);
	}
}
