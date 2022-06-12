package Blackjack.Iteration3;

import java.util.ArrayList;
import java.util.Iterator;

public class Hand {

    private ArrayList<Card> cards = new ArrayList<>();
    private static final int BLACKJACK = 21;
    private HandListener holder;
    private int numberOfAces;

    public Hand() {
        // Set the holder to a blank listener so that it will not be null if not externally set.
        setHolder( new HandListener() {
            public void handPlayable(){}
            public void handBlackjack(){}
            public void handBusted(){}
            public void handChanged(){}
        } );
    }

    public void setHolder( HandListener holder ) {
        this.holder = holder;
    }

    public Iterator getCards() {
        return cards.iterator();
    }

    public void addCard( Card card ) {
        cards.add( card );
        holder.handChanged();
        if ( card.getRank() == Rank.ACE )
            numberOfAces++;
        if ( bust() ) {
            holder.handBusted();
            return;
        }
        if ( blackjack() ) {
            holder.handBlackjack();
            return;
        }
        if ( cards.size() >= 2 ) {
            holder.handPlayable();
            return;
        }
    }

    public boolean blackjack() {
        if ( cards.size() == 2 && total() == BLACKJACK )
            return true;
        return false;
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
        int temporaryAces = numberOfAces;
        while ( total > BLACKJACK && temporaryAces > 0 ) {
            total = total - 10;
            temporaryAces--;
        }
        return total;
    }

    public void reset() {
        cards.clear();
        numberOfAces = 0;
    }

    public void turnOver() {
        Iterator cardsIterator = cards.iterator();
        while ( cardsIterator.hasNext() ) {
            Card card = ( Card ) cardsIterator.next();
            card.setFaceUp();
        }
    }

    public boolean isEqual( Hand otherHand ) {
        return this.total() == otherHand.total();
    }

    public boolean isGreaterThan( Hand otherHand ) {
        return this.total() > otherHand.total();
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
