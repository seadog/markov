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

public class Markov {

        private WordList master = new WordList();

        private List<Word> getListOfWords(String sentence) {
                sentence = sentence.trim();
                sentence = sentence.replace('\n', ' ');

                String[] strlist = sentence.split(" +");
                List<Word> wordList = new ArrayList<Word>();

                for (String sword : strlist) {
                        wordList.add(master.getWord(sword));
                }

                wordList.add(master.getWord(""));

                return wordList;
        }

        public void addSentence(String sentence) {
                List<Word> wordlist = getListOfWords(sentence);

                for (int i = 0; i < wordlist.size() - 1; i++) {
                        master.addWord(wordlist.get(i), wordlist.get(i + 1),
                                        i == 0);
                }
        }

        public String getStructure() {
                StringBuilder retval = new StringBuilder();

                for (Word word : master.getStructure()) {
                        if (word.getText().equals(""))
                                continue;
                        retval.append(word);

                        for (Word child : word.getChildren()) {
                                retval.append("    " + child.toString());
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
                        if (current.getText().equals("")) {
                                return retval.toString();
                        }
                }
                return retval.toString();
        }

        public String getSentence() {
                StringBuilder retval = new StringBuilder();

                Word current = master.getStart();

                while (!current.getText().equals("")) {
                        retval.append(current.getText());
                        retval.append(" ");
                        current = current.getNext();
                }

                return retval.toString();
        }
}
