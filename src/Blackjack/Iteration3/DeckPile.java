package Blackjack.Iteration3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class DeckPile {

    private ArrayList<Card> stack = new ArrayList<>();
    private int positionInDeckPile;
    private Random randomNumberGenerator = new Random();

    public void addCards( Card[] cards ) {
        for ( int i = 0; i < cards.length; i++ ) {
            stack.add( cards[i] );
        }
    }

    public void shuffle() {
        reset();
        for ( int i = 0; i < 4; i++ )
            randomize();
    }

    public void reset() {
        positionInDeckPile = 0;
        Iterator stackIterator = stack.iterator();
        while ( stackIterator.hasNext() ) {
            Card card = ( Card ) stackIterator.next();
            card.setFaceDown();
        }
    }

    private void randomize() {
        int numberOfCards = stack.size();
        for ( int i = 0; i < numberOfCards; i++ ) {
            int index = randomNumberGenerator.nextInt( numberOfCards );
            Card cardI = stack.get( i );
            Card cardIndex = stack.get( index );
            stack.set( i, cardIndex );
            stack.set( index, cardI );
        }
    }

    private Card deal() {
        if ( positionInDeckPile != stack.size() ) {
            Card card = stack.get( positionInDeckPile );
            positionInDeckPile++;
            return card;
        }
        return null;
    }

    public Card dealUp() {
        Card card = deal();
        if ( card != null )
            card.setFaceUp();
        return card;
    }

    public Card dealDown() {
        Card card = deal();
        if ( card != null )
            card.setFaceDown();
        return card;
    }
}
