package Blackjack.Iteration1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Suit {

    // Statically define all valid values of a Suit.
    public static final Suit DIAMONDS = new Suit( ( char ) 4 );
    public static final Suit HEARTS = new Suit( ( char ) 3 );
    public static final Suit SPADES = new Suit( ( char ) 6 );
    public static final Suit CLUBS = new Suit( ( char ) 5 );

    // Helps to iterate over the enum values.
    public static final Suit[] VALUES = { DIAMONDS, HEARTS, SPADES, CLUBS };
    public static final List SUITS = Collections.unmodifiableList( Arrays.asList( VALUES ) );

    private final char displayValue;

    // Do not allow instantiation by outside objects.
    private Suit( char displayValue ) {
        this.displayValue = displayValue;
    }

    public String toString() {
        return String.valueOf( displayValue );
    }
}
