/*
 * The MIT License
 *
 * Copyright 2011 Andrew Etches <andy@ignignokt.co.uk>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package uk.co.ignignokt.markov;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class that represents a markov chain.
 * 
 * This class can be iterated over, which will yield a full sentence.
 */
public class Chain {
        
        private SecureRandom randomGenerator;

        private Map<String, Word> words;
        private List<Word> start_words;

        /**
         * Construct the WordList object.
         */
        public Chain() {
                words = new HashMap<String, Word>();
                start_words = new ArrayList<Word>();
                randomGenerator = new SecureRandom();
                randomGenerator.setSeed(System.currentTimeMillis());
        }

        /**
         * Either get the word with that text, or create and add a word with
         * that text.
         * 
         * @param strword The string representation of the word
         * @return The Word object created or found.
         */
        public Word getWord(String strword) {
                Word retval = words.get(strword);

                if (retval == null) {
                        retval = new Word(strword);
                        words.put(strword, retval);
                }

                return retval;
        }
        
        /**
         * Get the random number generator used by chain.
         * 
         * @return The generator.
         */
        public Random getGenerator(){
                return this.randomGenerator;
        }

        /**
         * Add a word to the markov chain.
         * 
         * @param a The first word.
         * @param b The word the add to the first.
         * @param first Is the first word the start of the sentence?
         */
        public void addWord(Word a, Word b, boolean first) {
                if (first) {
                        start_words.add(a);
                }

                a.addWord(b);
        }

        /**
         * Add a word to the markov chain.
         * 
         * This version assumes it's not the start of a sentence.
         * 
         * @param a The first word to add
         * @param b The word to add to the first.
         */
        public void addWord(Word a, Word b) {
                addWord(a, b, false);
        }

        /**
         * Get the first word of a sentence.
         * 
         * @return Word object that is the start of a sentence.
         */
        public Word getStart() {
                int randomInt = randomGenerator.nextInt(start_words.size());

                return start_words.get(randomInt);
        }

        /**
         * Helper function to parse a sentence into Word objects.
         * 
         * @param sentence The sentence to parse
         * @return A list of words that have been added to the wordlist,
         * but not been chained yet.
         */
        private List<Word> getListOfWords(final String sentence) {
                String parsed = sentence.trim().replace('\n', ' ');

                String[] strlist = parsed.split(" +");
                List<Word> wordList = new ArrayList<Word>();

                for (String sword : strlist) {
                        wordList.add(this.getWord(sword));
                }

                wordList.add(this.getWord(""));

                return wordList;
        }
        
        /**
         * Adds a given sentence to the markov chain.
         * 
         * This function will issue a trim and replace any newlines with a
         * space, it also is OK if the gap between words is more than one 
         * space, however it assumes that only a single sentence is passed,
         * so parsing into sentences is up to the user.
         * 
         * @param sentence The sentence to add to the chain.
         */
        public void addSentence(String sentence){
                List<Word> wordlist = getListOfWords(sentence);

                for (int i = 0; i < wordlist.size() - 1; i++) {
                        this.addWord(wordlist.get(i), wordlist.get(i + 1),
                                        i == 0);
                }
        }

        /**
         * Returns a representation of the markov chain structure.
         * 
         * The structure it returns is as follows:
         * WORD
         *     CHILD_WORD
         *     null <- if it's an end of sentence word.
         * 
         * @return String representing the markov chain structure.
         */
        public String getStructure() {
                StringBuilder retval = new StringBuilder();

                for (Word word : this.words.values()) {
                        if (word.getText().equals("")) {
                                continue;
                        }

                        retval.append(word);

                        for (Word child : word.getChildren()) {
                                retval.append("    " + child.toString());
                        }
                }

                return retval.toString();
        }

        /**
         * Generates a markov chain sentence.
         * 
         * There is no limit to the length of this generated
         * sentence, but, depending on your input it shouldn't
         * be anything to worry about.
         * 
         * @return A sentence generated from the markov chain.
         */
        public String getSentence() {
                StringBuilder retval = new StringBuilder();
                Word loop = this.getStart();
                
                while(!loop.isEnd()){
                        retval.append(loop.getText());
                        retval.append(' ');
                        loop = loop.getNext(randomGenerator);
                }
                
                return retval.toString();
        }
        
        /**
         * Generates a markov chain sentence.
         * 
         * The limit of words is either limit or when it hits an
         * end of sentence word, whichever comes first.
         * 
         * @param limit The maximum number of words in the sentence.
         * @return A sentence generated from the markov chain.
         */
        public String getSentence(int limit) {
                StringBuilder retval = new StringBuilder();
                Word loop = this.getStart();
                
                int i = 0;
                
                while(!loop.isEnd()){
                        if(i++ > limit) return retval.toString();
                        retval.append(loop.getText());
                        retval.append(' ');
                        loop = loop.getNext(randomGenerator);
                }

                return retval.toString();
        }
}


