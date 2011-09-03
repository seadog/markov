/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.ignignokt.markov.word;

import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {
        Word testerWord;

        public WordTest() {
        }

        @Before
        public void setUp() {
                testerWord = new Word("tester");
        }

        @After
        public void tearDown() {
        }

        @Test
        public void testGetText(){
                assertEquals("tester", testerWord.getText());
        }

        /*
         *         public List<Word> getChildren() {
                return next;
        }

        public int getSize() {
                return next.size();
        }

        public String getText() {
                return this.text;
        }

        public void addWord(Word word) {
                next.add(word);
        }

        public Word getNext() {
                Random generator = new Random();
                int randomInt = generator.nextInt(next.size());

                return next.get(randomInt);
        }
         */
}
