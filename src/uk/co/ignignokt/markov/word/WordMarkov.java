package uk.co.ignignokt.markov.word;

import uk.co.ignignokt.markov.Markov;
import uk.co.ignignokt.markov.MarkovParser;

public class WordMarkov implements Markov {
	WordList master = new WordList();

	private void parsewords(String text){
		String[] words = text.split(" +");
		
		for(int i = 0; i < words.length -2; i++){
			if(words[i].equals("") || words[i+1].equals("")) continue;
			master.addWord(words[i], words[i+1]);
		}
	}

	public WordMarkov(String filename) {
		MarkovParser parser = new MarkovParser(filename);
		parsewords(parser.parse());
	}
	
	public WordMarkov(){
		this("data.txt");
	}
	
	public String getSentence(){
		StringBuilder retval = new StringBuilder();
		
		Word current = null;
		boolean found = false;
		
		while(!found){
			current = master.getRandom();
			if(current.getInitWord()) found = true;
		}
		
		current.getNext();
		
		while(!current.getEndWord()){
			retval.append(current.getText());
			retval.append(" ");
			
			current = current.getNext();
		}
		
		retval.append(current.getText());
		
		return retval.toString();
	}
	
	public String getLimit(int limit){
		StringBuilder retval = new StringBuilder();
		
		Word current = master.getRandom();
		
		while(limit-- > 0){
			retval.append(current.getText());
			retval.append(" ");
			
			current = current.getNext();
		}

		return retval.toString();
	}
	
	public static void main(String[] args){
		WordMarkov markov = new WordMarkov("data.txt");
		
		System.out.println(markov.getSentence());
	}
}