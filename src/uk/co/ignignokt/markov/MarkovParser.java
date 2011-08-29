package uk.co.ignignokt.markov;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MarkovParser {
	private String filename;
	
	public MarkovParser(String name){
		this.filename = name;
	}
	
	public MarkovParser(){
		this.filename = "data.txt";
	}
	
	public String parse(){
		FileReader fr = null;
		StringBuilder document = new StringBuilder();

		try {
			fr = new FileReader(filename);
		} catch (FileNotFoundException e){
			System.out.println("File not found");
			System.exit(-1);
		}

		BufferedReader br = new BufferedReader(fr);

		String line;
		
		try {
			while((line = br.readLine()) != null){
				if(line.equals("")){ 
					document.append("\n");
				} else {
					document.append(line);
				}
				
				document.append(" ");
			}
		} catch(IOException e) {
			System.out.println("IO Error");
			System.exit(-1);
		}
		
		return document.toString();
	}
}
