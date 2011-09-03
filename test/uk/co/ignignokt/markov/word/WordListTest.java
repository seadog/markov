/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.ignignokt.markov.word;

import org.junit.*;
import static org.junit.Assert.*;

public class WordListTest {
        WordList wordlist;

        @Before
        public void setUp() {
                wordlist = new WordList();
        }

        @After
        public void tearDown() {
        }

        @Test
        public void trueTest(){
                assertEquals(true, true);
        }
}
