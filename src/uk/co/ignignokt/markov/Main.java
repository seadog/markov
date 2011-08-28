package uk.co.ignignokt.markov;

import uk.co.ignignokt.markov.word.WordMarkov;

public class Main {
	public static void main(String[] args){
		WordMarkov wm = new WordMarkov();
		
		System.out.println(wm.getSentence());
		System.out.println(wm.getSentence());
		System.out.println(wm.getSentence());
	}
}
