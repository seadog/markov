package uk.co.ignignokt.markov;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import uk.co.ignignokt.markov.external.*;

public class Main {
	public static void main(String[] args) throws IOException{
		Markov wm = new Markov();
		
		FileInputStream fis = new FileInputStream("data.txt");
		UnicodeReader ur = new UnicodeReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(ur);
		
		String line;
		StringBuilder builder = new StringBuilder();
		
		while((line = br.readLine()) != null){
			if(line.equals("")){
				wm.addSentence(builder.toString());
				builder = new StringBuilder();
				continue;
			} else {
				builder.append(line);
				builder.append(' ');
			}
		}
		
		wm.addSentence(builder.toString());
		
                System.out.println(wm.getStructure());
		System.out.println(wm.getSentence());
	}
}
