package uk.co.ignignokt.markov;

public interface Markov {
	public String getLimit(int limit);
	public String getSentence();
	public void addParagraph(String paragraph);
}
