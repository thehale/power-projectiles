package io.github.jhale1805.util;

import java.util.Iterator;

/**
 * A utility class that can iterate between any two integers.
 */
public class Incrementor implements Iterable<Integer> {

    private Integer start;
    private Integer end;

    private class NumberIterator implements Iterator<Integer> {
        
        private Integer start;
        private Integer current;
        private Integer end;

        public NumberIterator(Integer start, Integer end) {
            this.start = start;
            this.current = start;
            this.end = end;
        }
        
        @Override
        public boolean hasNext() {
            if (start <= end) return current <= end;
            else if (start >= end) return current >= end;
            else return false;
        }

        @Override
        public Integer next() {
            int out = current;

            if (start <= end) current++;
            else if (start >= end) current--;
            else;  // Nothing to increment towards.
            
            return out;
        }

    }

    /**
     * Standard constructor 
     * @param start The initial value of the iteration sequence.
     * @param end The ending value of the iteration sequence.
     */
    public Incrementor(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new NumberIterator(start, end);
    }
    
}
