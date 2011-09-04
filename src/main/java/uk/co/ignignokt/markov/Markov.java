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

import java.util.ArrayList;
import java.util.List;

import uk.co.ignignokt.markov.word.Word;
import uk.co.ignignokt.markov.word.WordList;

/**
 * Markov chain output class.
 * 
 * Class that interfaces with the Word and WordList classes in order to
 * generate the markov chain.
 * 
 * @see uk.co.ignignokt.markov.word.Word
 * @see uk.co.ignignokt.markov.word.WordList
 */
public class Markov {

        /**
         * The WordList used to store our Word objects.
         */
        private WordList master;

        /**
         * Constructor, takes no arguments and simply gives us the object.
         */
        public Markov() {
                this.master = new WordList();
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
                        wordList.add(master.getWord(sword));
                }

                wordList.add(master.getWord(""));

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
        public void addSentence(String sentence) {
                List<Word> wordlist = getListOfWords(sentence);

                for (int i = 0; i < wordlist.size() - 1; i++) {
                        master.addWord(wordlist.get(i), wordlist.get(i + 1),
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

                for (Word word : master.getStructure()) {
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
         * The limit of words is either limit or when it hits an
         * end of sentence word, whichever comes first.
         * 
         * @param limit The maximum number of words in the sentence.
         * @return A sentence generated from the markov chain.
         */
        public String getLimit(int limit) {
                StringBuilder retval = new StringBuilder();
                
                int i = 0;
                for(Word x : master){
                        if(i++ > limit) return retval.toString();
                        retval.append(x.getText());
                        retval.append(' ');
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

                for(Word x : master){
                        retval.append(x.getText());
                        retval.append(' ');
                }

                return retval.toString();
        }
}
