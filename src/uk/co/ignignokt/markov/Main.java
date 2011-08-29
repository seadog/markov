package uk.co.ignignokt.markov;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import uk.co.ignignokt.markov.word.WordMarkov;
import uk.co.ignignokt.markov.external.*;

public class Main {
	public static void main(String[] args) throws IOException{
		WordMarkov wm = new WordMarkov();
		
		FileInputStream fis = new FileInputStream("data.txt");
		UnicodeReader ur = new UnicodeReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(ur);
		
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
		
		System.out.println(wm.getSentence());
	}
}
