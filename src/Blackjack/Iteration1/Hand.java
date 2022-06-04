package Blackjack.Iteration1;

import java.util.ArrayList;
import java.util.Iterator;

public class Hand {

    private ArrayList<Card> cards = new ArrayList<>();
    private static final int BLACKJACK = 21;

    public void addCard( Card card ) {
        cards.add( card );
    }

    public boolean bust() {
        if ( total() > BLACKJACK )
            return true;
        return false;
    }

    public int total() {
        int total = 0;
        Iterator cardsIterator = cards.iterator();
        while ( cardsIterator.hasNext() ) {
            Card card = ( Card ) cardsIterator.next();
            total += card.getRank().getRank();
        }
        return total;
    }

    public void reset() {
        cards.clear();
    }

    public void turnOver() {
        Iterator cardsIterator = cards.iterator();
        while ( cardsIterator.hasNext() ) {
            Card card = ( Card ) cardsIterator.next();
            card.setFaceUp();
        }
    }

    public String toString() {
        Iterator cardsIterator = cards.iterator();
        String string = "";
        while ( cardsIterator.hasNext() ) {
            Card card = ( Card ) cardsIterator.next();
            string = string + " " + card.toString();
        }
        return string;
    }
}
