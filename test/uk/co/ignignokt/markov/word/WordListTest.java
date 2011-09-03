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

        @Test
        public void testAddStartNull(){
                wordlist.addWord("Hi", null, true);
                assertEquals(wordlist.getStart().getText(), "Hi");
        }
        
        @Test
        public void testAddStart(){
                wordlist.addWord("hi", "whatever", true);
                assertEquals(wordlist.getStart().getText(), "hi");
                assertEquals(wordlist.getStart().getNext().getText(), "whatever");
        }
        
        @Test
        public void testAdd(){
                wordlist.addWord("car", "is", true);
                wordlist.addWord("is", "blue");
                
                assertEquals(wordlist.getStart().getNext().getText(), "is");
                assertEquals(wordlist.getStart().getNext().getNext().getText(), "blue");
        }
}
