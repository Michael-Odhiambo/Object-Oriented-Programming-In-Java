package Blackjack.Iteration2.Tests;

import junit.framework.TestCase;
import org.junit.Assert;
import Blackjack.Iteration2.*;

public class DeckTest extends TestCase {

    private Deck deck;
    private final static DeckPile pile = new MockPile();

    public DeckTest( String name ) {
        super( name );
    }

    protected void setUp() {
        deck = new Deck();
    }

    public void testDeck() {
        deck.addToStack( pile );
    }

    private static class MockPile extends DeckPile {
        public void addCards( Card[] cards ) {
            Assert.assertTrue( "The pile should have been passed 52 cards",
                    cards.length == 52 );
            for ( int i = 0; i < 52; i++ )
                Assert.assertTrue( "Card should not be null", cards[i] != null );
        }
    }


}
