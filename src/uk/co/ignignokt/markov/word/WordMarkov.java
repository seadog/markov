package uk.co.ignignokt.markov.word;

import uk.co.ignignokt.markov.Markov;

public class WordMarkov implements Markov {
	WordList master = new WordList();

	public void addParagraph(String paragraph){
		String removed = paragraph.replace('\n', ' ');

		String[] list = removed.split(" +");
		
		for(int i = 0; i < list.length-2; i++){
			master.addWord(i, list[i], list[i+1]);
		}
	}

	public WordMarkov(){
	}
	
	public String getLimit(int limit){
		StringBuilder retval = new StringBuilder();
		
		Word current = master.getRandom();
		
		while(!current.isStart()) current = master.getRandom();
		
		while(limit-- > 0){
			retval.append(current.getText());
			retval.append(" ");
			
			current = current.getNext();
			if(current == null) return retval.toString();
		}

		return retval.toString();
	}
	
	public String getSentence(){
		//TODO: fix this!1
		return getLimit(20);
	}
}
