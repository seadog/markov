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

import java.util.Collection;
import java.util.List;

import uk.co.ignignokt.markov.word.Word;
import uk.co.ignignokt.markov.word.WordList;

public class Markov {

        private WordList master = new WordList();

        public void addSentence(String sentence) {
                sentence = sentence.trim();
                String removed = sentence.replace('\n', ' ');

                String[] list = removed.split(" +");

                for (int i = 0; i < list.length; i++) {
                        if (i == 0 && list.length == 1) {
                                master.addWord(list[i], null, true);
                        } else if (i == 0) {
                                master.addWord(list[i], list[i + 1], true);
                        } else if (i == list.length - 1) {
                                master.addWord(list[i], null);
                        } else {
                                master.addWord(list[i], list[i + 1]);
                        }
                }
        }

        public String getStructure() {
                StringBuilder retval = new StringBuilder();

                Collection<Word> words = master.getStructure();

                for (Word word : words) {
                        retval.append(word.getText());
                        retval.append("\n");
                        List<Word> children = word.getChildren();

                        for (Word child : children) {
                                if (child == null) {
                                        retval.append("    ");
                                        retval.append((String) null);
                                        retval.append("\n");
                                } else {
                                        retval.append("    ");
                                        retval.append(child.getText());
                                        retval.append("\n");
                                }
                        }
                }

                return retval.toString();
        }

        public String getLimit(int limit) {
                StringBuilder retval = new StringBuilder();

                Word current = master.getStart();

                while (limit-- > 0) {
                        retval.append(current.getText());
                        retval.append(" ");

                        current = current.getNext();
                        if (current == null) {
                                return retval.toString();
                        }
                }

                return retval.toString();
        }

        public String getSentence() {
                StringBuilder retval = new StringBuilder();

                Word current = master.getStart();

                while (current != null) {
                        retval.append(current.getText());
                        retval.append(" ");
                        current = current.getNext();
                }

                return retval.toString();
        }
}
