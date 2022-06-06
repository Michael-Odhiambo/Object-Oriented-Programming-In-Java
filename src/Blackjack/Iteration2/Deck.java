package Blackjack.Iteration2;

import java.util.Iterator;
import java.util.Random;

public class Deck {

    private Card[] deck;
    private int positionInDeck;

    public Deck() {
        buildCards();
    }

    public void buildCards() {
        deck = new Card[ 52 ];
        Iterator suitsIterator = Suit.SUITS.iterator();
        int counter = 0;
        while ( suitsIterator.hasNext() ) {
            Suit suit = ( Suit ) suitsIterator.next();
            Iterator ranksIterator = Rank.RANKS.iterator();
            while ( ranksIterator.hasNext() ) {
                Rank rank = ( Rank ) ranksIterator.next();
                deck[ counter ] = new Card( suit, rank );
                counter++;
            }
        }
    }

    public void addToStack( DeckPile stack ) {
        stack.addCards( deck );
    }
}
