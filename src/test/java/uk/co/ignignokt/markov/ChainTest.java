/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.ignignokt.markov;

import org.junit.*;

import uk.co.ignignokt.markov.Chain;
import static org.junit.Assert.*;

public class ChainTest {
        Chain wordlist;

        @Before
        public void setUp() {
                wordlist = new Chain();
        }

        @Test
        public void testAddStartNull(){
                wordlist.addSentence("hi");
                 assertEquals(wordlist.getStart().getText(), "hi");
        }
        
        @Test
        public void testAddStart(){
                wordlist.addSentence("hi whatever");
                assertEquals(wordlist.getStart().getText(), "hi");
                assertEquals(wordlist.getStart().getNext().getText(), "whatever");
        }
        
        @Test
        public void testAdd(){
                wordlist.addSentence("car is blue");
                
                assertEquals(wordlist.getStart().getNext().getText(), "is");
                assertEquals(wordlist.getStart().getNext().getNext().getText(), "blue");
        }
        
        @Test
        public void testAddSpaces(){
                wordlist.addSentence("car      is                        blue");
                
                assertEquals(wordlist.getStart().getNext().getText(), "is");
                assertEquals(wordlist.getStart().getNext().getNext().getText(), "blue");
        }
        
        @Test
        public void testPrependSpaces(){
                wordlist.addSentence("   car    is     blue");
                
                assertEquals(wordlist.getStart().getNext().getText(), "is");
                assertEquals(wordlist.getStart().getNext().getNext().getText(), "blue");
        }
}
