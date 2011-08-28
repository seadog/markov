import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Markov {
	WordList master;
	StringBuilder document = new StringBuilder();

	private void parsetext(String text){
		String[] words = text.split(" +");
		
		for(int i = 0; i < words.length -2; i++){
			if(words[i].equals("") || words[i+1].equals("")) continue;
			master.addWord(words[i], words[i+1]);
		}
	}
	
	public Markov(String filename) throws IOException{
		master = new WordList();

		FileReader fr = null;

		try {
			fr = new FileReader(filename);
		} catch (FileNotFoundException e){
			System.out.println("File not found");
			System.exit(-1);
		}

		BufferedReader br = new BufferedReader(fr);

		String line;
		while((line = br.readLine()) != null){
			document.append(line);
			document.append(" ");
		}
		
		parsetext(document.toString());
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
	
	public String getSentence(int limit){
		StringBuilder retval = new StringBuilder();
		
		Word current = master.getRandom();
		
		while(limit-- > 0){
			retval.append(current.getText());
			retval.append(" ");
			
			current = current.getNext();
		}

		return retval.toString();
	}
	
	public static void main(String[] args) throws IOException{
		Markov markov = new Markov("data.txt");
		
		System.out.println(markov.getSentence());
	}
}
