package Encapsulation;

import java.util.LinkedList;

public class Deck {

    private LinkedList<Card> deck;

    public Deck() {
        buildCards();
    }

    private void buildCards() {
        deck = new LinkedList<>();
        deck.add( new Card( Card.CLUBS, Card.TWO ) );
        deck.add( new Card( Card.CLUBS, Card.THREE ) );
        deck.add( new Card( Card.CLUBS, Card.FOUR ) );
        deck.add( new Card( Card.CLUBS, Card.FIVE ) );
    }

    public Card getCardAt( int index ) {
        if ( index < deck.size() )
            return deck.get( index );
        return null;
    }

    public void replace( int index, Card card ) {
        deck.set( index, card );
    }

    public int size() {
        return deck.size();
    }

    public Card removeCardFromFront() {
        if ( deck.size() > 0 ) {
            Card card = deck.removeFirst();
            return card;
        }
        return null;
    }

    public void returnCardToBack( Card card ) {
        deck.add( card );
    }

}
