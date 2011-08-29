package uk.co.ignignokt.markov.irc;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IrcList {
	private Map<String, Irc> words;
	
	public IrcList(){
		words = new HashMap<String, Irc>();
	}
	
	public void addWord(int i, int length, String word, String next){
		Irc entry = words.get(word);
		Irc to = words.get(next);
		
		if(entry == null){
			entry = new Irc(word);
			words.put(word, entry);
		}

		if(to == null){
			to = new Irc(next);
			words.put(next, to);
		}
		
		if(i == 0) entry.setStart(true);
		entry.addWord(to);
	}
	
	public Collection<Irc> getStructure(){
		return words.values();
	}
	
	public Irc getRandom(){
		Random generator = new Random();
		int randomInt = generator.nextInt(words.size());

		Object[] randoms = words.values().toArray();

		return (Irc) randoms[randomInt];
	}
}
