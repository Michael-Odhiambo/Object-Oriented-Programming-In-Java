package Encapsulation;

public class Card {

    private int rank;
    private int suit;
    private boolean faceUp;

    // Suits
    public static final int DIAMONDS = 4;
    public static final int HEARTS = 3;
    public static final int SPADES = 6;
    public static final int CLUBS = 5;

    // Ranks
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int JACK = 74;
    public static final int QUEEN = 81;
    public static final int KING = 75;
    public static final int ACE = 65;

    public Card( int suit, int rank ) {
        // In a real program, you would need to do validation on the arguments.
        this.suit = suit;
        this.rank = rank;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public void faceUp() {
        faceUp = true;
    }

    public void faceDown() {
        faceUp = false;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public String display() {
        String display = getRankDisplayValue() + getSuitsDisplayValue();
        return display;
    }

    private String getRankDisplayValue() {
        String theRank;
        if ( rank > 10 )
            theRank = String.valueOf( (char) rank );
        else
            theRank = String.valueOf( (char) rank );
        return theRank;
    }

    private String getSuitsDisplayValue() {
        char theSuit;
        switch ( suit ) {
            case DIAMONDS :
                theSuit = (char) DIAMONDS;
                break;
            case HEARTS :
                theSuit = (char) HEARTS;
                break;
            case SPADES :
                theSuit = (char) SPADES;
                break;
            default :
                theSuit = (char) CLUBS;
        }
        return String.valueOf( theSuit );
    }
}
