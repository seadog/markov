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
