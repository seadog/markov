/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.ignignokt.markov;

import org.junit.*;

import uk.co.ignignokt.markov.Word;
import static org.junit.Assert.*;

public class WordTest {
        Word testerWord;

        public WordTest() {
        }

        @Before
        public void setUp() {
                testerWord = new Word("tester");
        }

        @Test
        public void testGetText(){
                assertEquals("tester", testerWord.getText());
        }

        @Test
        public void testEmptySize(){
                assertEquals(testerWord.getSize(), 0);
        }

        @Test
        public void testAddWordSize(){
                testerWord.addWord(new Word("Whatever"));
                assertEquals(testerWord.getSize(), 1);
        }

        @Test
        public void testAddWordCheck(){
                Word toAdd = new Word("Whatever");
                testerWord.addWord(toAdd);
                assertTrue(testerWord.getChildren().contains(toAdd));
        }
}
