package Encapsulation;

public class Dealer {
    private Deck deck;

    public Dealer( Deck deck ) {
        this.deck = deck;
    }

    public void shuffle() {
        int numberOfCards = deck.size();
        for ( int i = 0; i < numberOfCards; i++ ) {
            int index = (int)( Math.random()*numberOfCards );
            Card cardAti = deck.getCardAt( i );
            Card cardAtIndex = deck.getCardAt( index );
            deck.replace( i, cardAtIndex );
            deck.replace( index, cardAti );

        }
    }

    public Card dealCard() {
        if ( deck.size() > 0 )
            return deck.removeCardFromFront();
        return null;
    }
}
