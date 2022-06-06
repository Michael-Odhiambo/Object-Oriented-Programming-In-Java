package Blackjack.Iteration2;

public class Card {

    private Rank rank;
    private Suit suit;
    private boolean faceUp;

    // Creates a new card - only use the constants to initialize the card.
    public Card( Suit suit, Rank rank ) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setFaceUp() {
        this.faceUp = true;
    }

    public void setFaceDown() {
        this.faceUp = false;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public String toString() {
        return rank.toString() + suit.toString();
    }
}
