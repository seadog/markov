package uk.co.ignignokt.markov.word;

import java.util.Collection;
import java.util.List;

import uk.co.ignignokt.markov.Markov;

public class WordMarkov implements Markov {
	WordList master = new WordList();

	public void addParagraph(String paragraph){
		paragraph = paragraph.trim();
		String removed = paragraph.replace('\n', ' ');

		String[] list = removed.split(" +");
		
		for(int i = 0; i < list.length-1; i++){
			master.addWord(list[i], list[i+1]);
		}
	}

	public WordMarkov(){
	}
	
	public String getStructure(){
		StringBuilder retval = new StringBuilder();
		
		Collection<Word> words = master.getStructure();
		
		for(Word word : words){
			retval.append(word.getText() + " -> " + word.isStart() + " : " + word.isEnd() + "\n");
			List<Word> children = word.getChildren();
			
			for(Word child : children){
				retval.append("    " +  child.getText() + "\n");
			}
		}
		
		return retval.toString();
	}
	
	public String getLimit(int limit){
		StringBuilder retval = new StringBuilder();
		
		Word current = master.getRandom();
		
		while(limit-- > 0){
			retval.append(current.getText());
			retval.append(" ");
			
			current = current.getNext();
			if(current == null) return retval.toString();
		}

		return retval.toString();
	}
	
	public String getSentence(){
		StringBuilder retval = new StringBuilder();
		
		Word current = master.getRandom();
		
		while(!current.isStart()) current = master.getRandom();
		
		while(!current.isEnd()){
			retval.append(current.getText());
			retval.append(" ");
			current = current.getNext();
		}
		
		retval.append(current.getText()); retval.append(" ");
		
		return retval.toString();
	}
}
