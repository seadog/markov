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

package uk.co.ignignokt.markov.word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents each word that is added to the markov chain, you should
 * be careful meddling with any protected methods here as they should be called
 * from WordList only.
 */
public class Word {

        private String text;
        private List<Word> next;

        /**
         * Create a Word object
         * 
         * Creating a word object does not add it to the chain automatically.
         * 
         * @param text The word to add, it is assumed this is a single word.
         */
        public Word(String text) {
                this.next = new ArrayList<Word>();
                this.text = text;
        }

        /**
         * Get all of the children of a Word. 
         * 
         * @return List of Word objects.
         */
        public List<Word> getChildren() {
                return next;
        }

        /**
         * Gets the number of children the Word object has.
         * 
         * @return Number of children we have.
         */
        public int getSize() {
                return next.size();
        }

        /**
         * Get the text used with this Word object.
         * 
         * @return The text assosciated with this Word
         */
        public String getText() {
                return this.text;
        }

        /**
         * Adds a Word to this Word's chain.
         * 
         * This should only be used if both of the Words are already in the
         * WordList associated with this Word. 
         * 
         * @param word The Word to add to this Word's chain.
         */
        protected void addWord(Word word) {
                next.add(word);
        }

        /**
         * Gets the next word in the chain.
         * 
         * This method uses java's built in random generator, not sure
         * how good it is in terms of uniform output.
         * 
         * @return Next word in the chain.
         */
        public Word getNext() {
                Random generator = new Random();
                int randomInt = generator.nextInt(next.size());

                return next.get(randomInt);
        }

        /**
         * Check if this is the final word in a sentence.
         * 
         * @return true if this is the last word in a sentence, false otherwise.
         */
        public boolean isEnd() {
                return this.text.equals("");
        }
        
        @Override
        public String toString() {
                if (text.equals("")) {
                        return "null\n";
                }
                return text + "\n";
        }
}
