package uk.co.ignignokt.markov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import uk.co.ignignokt.markov.word.WordMarkov;

public class Main {
	public static void main(String[] args) throws IOException{
		WordMarkov wm = new WordMarkov();
		
		FileReader fr = new FileReader("data.txt");
		BufferedReader br = new BufferedReader(fr);
		
		br.read(); // get rid of first byte of file bug.
		
		String line;
		StringBuilder builder = new StringBuilder();
		
		while((line = br.readLine()) != null){
			if(line.equals("")){
				wm.addParagraph(builder.toString());
				builder = new StringBuilder();
				continue;
			} else {
				builder.append(line);
				builder.append(' ');
			}
		}
		
		wm.addParagraph(builder.toString());
		
		//System.out.println(wm.getStructure());
		System.out.println(wm.getSentence());
	}
}
