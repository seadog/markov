package uk.co.ignignokt.markov.word;

import uk.co.ignignokt.markov.Markov;

public class WordMarkov implements Markov {
	WordList master = new WordList();

	public void addParagraph(String paragraph){
		paragraph = paragraph.trim();
		String removed = paragraph.replace('\n', ' ');

		String[] list = removed.split(" +");
		
		for(int i = 0; i < list.length-2; i++){
			master.addWord(list[i], list[i+1]);
		}
	}

	public WordMarkov(){
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
