package Blackjack.Iteration2.Tests;

import Blackjack.Iteration2.*;

import junit.framework.TestCase;
import org.junit.Assert;

public class BlackjackDealerTest extends TestCase {

    private BlackjackDealer dealer;
    private Hand dealerHand;
    private Hand playerHand;
    private MockPlayer player;
    private MockDealer mockDealer;

    private final static Card H10 = new Card( Suit.HEARTS, Rank.TEN );
    private final static Card H9 = new Card( Suit.HEARTS, Rank.NINE );
    private final static Card H2 = new Card( Suit.HEARTS, Rank.TWO );

    public BlackjackDealerTest( String name ) {
        super( name );
    }

    protected void setUp() {
        dealerHand = new Hand();
        DeckPile pile = new DeckPile();
        ( new Deck() ).addToStack( pile );
        ( new Deck() ).addToStack( pile );
        ( new Deck() ).addToStack( pile );
        ( new Deck() ).addToStack( pile );
        dealer = new BlackjackDealer( "Dealer", dealerHand, pile );
        mockDealer = new MockDealer( "Mock Dealer", dealerHand, pile );
        playerHand = new Hand();
        player = new MockPlayer( "Mock", playerHand );
    }

    public void testHit() {
        dealer.hit( player );
        Assert.assertTrue( "Player should have one card", player.numberOfCards() == 1 );
    }

    public void testShouldHit() {
        // Need to add a player, get the dealer into the proper state, and then
        // check should hit
        mockDealer.addPlayer( player );
        mockDealer.reset();
        mockDealer.addCard( H10 );
        mockDealer.addCard( H2 );
        mockDealer.standing( player );
        Assert.assertTrue( "Less than 17, should hit", mockDealer.hit() );
        mockDealer.addCard( H2 );
        Assert.assertTrue( "Less than 17, should hit", mockDealer.hit() );
        mockDealer.addCard( H2 );
        Assert.assertTrue( "Less than 17, should hit", mockDealer.hit() );
        mockDealer.addCard( H2 );
        Assert.assertTrue( "Greater than 17, should not hit", !mockDealer.hit() );
        mockDealer.addCard( H2 );
        Assert.assertTrue( "Greater than 17, should not hit", !mockDealer.hit() );
        mockDealer.addCard( H2 );
        Assert.assertTrue( "Greater than 17, should not hit", !mockDealer.hit() );
    }

    public void testNewGame() {
        mockDealer.addPlayer( player );
        mockDealer.newGame();
        Assert.assertTrue( "Player should have gotten a turn", player.didPlay() );
        Assert.assertTrue( "Player should have 2 cards", player.numberOfCards() == 2 );
    }

    public void testDeal() {
        mockDealer.addPlayer( player );
        mockDealer.reset();
        mockDealer.deal();
        Assert.assertTrue( "Player should have 2 cards", player.numberOfCards() == 2 );
        Assert.assertTrue( "Dealer should have 2 cards", mockDealer.numberOfCards() == 2 );
    }


    private class MockDealer extends BlackjackDealer {
        private int numberOfCards;

        public MockDealer( String name, Hand hand, DeckPile pile ) {
            super( name, hand, pile );
        }

        public void addCard( Card card ) {
            numberOfCards++;
            super.addCard( card );
        }

        public int numberOfCards() {
            return numberOfCards;
        }

        public boolean hit() {
            return super.hit();
        }
    }

    private class MockPlayer extends Player {

        private boolean didPlay = false;
        private int numberOfCards;

        public MockPlayer( String name, Hand hand ) {
            super( name, hand );
        }

        public void addCard( Card card ) {
            numberOfCards++;
            super.addCard( card );
        }

        public int numberOfCards() {
            return numberOfCards;
        }

        public boolean didPlay() {
            return didPlay;
        }

        public void play( Dealer dealer ) {
            didPlay = true;
            super.play( dealer );
        }

        public boolean hit() {
            return false;
        }
    }

}
