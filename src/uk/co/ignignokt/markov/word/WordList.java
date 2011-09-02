package uk.co.ignignokt.markov.word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordList {

        private Map<String, Word> words;
        private List<Word> start_words;

        public WordList() {
                words = new HashMap<String, Word>();
                start_words = new ArrayList<Word>();
        }

        public void addWord(String word, String next, boolean first) {
                Word entry = words.get(word);
                if (next == null) {
                        if (entry == null) {
                                entry = new Word(word);
                                words.put(word, entry);
                        }

                        entry.addWord(null);

                        if (first) {
                                start_words.add(entry);
                        }

                        return;
                }

                Word to = words.get(next);

                if (entry == null) {
                        entry = new Word(word);
                        words.put(word, entry);
                }

                if (to == null) {
                        to = new Word(next);
                        words.put(next, to);
                }

                if (first) {
                        start_words.add(entry);
                }

                entry.addWord(to);
        }

        public void addWord(String word, String next) {
                addWord(word, next, false);
        }

        public Collection<Word> getStructure() {
                return words.values();
        }

        public Word getStart() {
                Random generator = new Random();
                int randomInt = generator.nextInt(start_words.size());

                return start_words.get(randomInt);
        }
}
