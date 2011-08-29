package uk.co.ignignokt.markov;

import uk.co.ignignokt.markov.word.WordMarkov;

public class Main {
	public static void main(String[] args){
		System.out.println("Start:");
		long startTime = System.currentTimeMillis();
		WordMarkov wm = new WordMarkov();
		
		System.out.println(wm.getSentence());
		System.out.println(wm.getSentence());
		System.out.println(wm.getSentence());
		System.out.println(wm.getSentence());

		long endTime = System.currentTimeMillis();
		
		System.out.println("Took: " + (endTime-startTime) + "ms");
	}
}
