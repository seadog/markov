package uk.co.ignignokt.markov.word;

import java.util.Iterator;

class WordIterator implements Iterator<Word> {
        Word iter;
        Word check = null;
        
        WordIterator(Word start){
                this.iter = start;
        }

        @Override
        public boolean hasNext() {
                if(check != null) iter = check;
                return !iter.isEnd();
        }

        @Override
        public Word next() {
                check = iter.getNext();
                return iter;
        }

        @Override
        public void remove() {
                return;
        }
}
