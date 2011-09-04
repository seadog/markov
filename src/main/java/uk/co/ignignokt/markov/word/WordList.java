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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class that represents a markov chain.
 * 
 * This class can be iterated over, which will yield a full sentence.
 */
public class WordList implements Iterable<Word> {

        private Map<String, Word> words;
        private List<Word> start_words;

        /**
         * Construct the WordList object.
         */
        public WordList() {
                words = new HashMap<String, Word>();
                start_words = new ArrayList<Word>();
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
         * Add a word to the markov chain using strings.
         * 
         * These should generally only be used internally, but might
         * be useful otherwise.
         * 
         * @param a The first word.
         * @param b The word to add to the first.
         * @param first Is the first word the start of a sentence?
         */
        public void addWord(String a, String b, boolean first) {
                Word a2 = getWord(a);
                Word b2 = getWord(b);

                if (first) {
                        start_words.add(a2);
                }

                a2.addWord(b2);
        }

        /**
         * Add a word to the markov chain using strings.
         * 
         * These should generally only be used internally, but might
         * be useful otherwise, this method assumes the first word
         * is not the start of a sentence.
         * 
         * @param a The first word.
         * @param b The word to add to the first.
         */
        public void addWord(String a, String b) {
                addWord(a, b, false);
        }

        /**
         * Get a collection representing all the words in the markov
         * chain.
         * 
         * @return Collection of words in the chain.
         */
        public Collection<Word> getStructure() {
                return words.values();
        }

        /**
         * Get the first word of a sentence.
         * 
         * @return Word object that is the start of a sentence.
         */
        public Word getStart() {
                Random generator = new Random();
                int randomInt = generator.nextInt(start_words.size());

                return start_words.get(randomInt);
        }

        @Override
        public Iterator<Word> iterator() {
                return new WordIterator(getStart());
        }
}


