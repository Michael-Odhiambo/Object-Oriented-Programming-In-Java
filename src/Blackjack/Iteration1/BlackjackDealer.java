package Blackjack.Iteration1;

import java.util.ArrayList;

public class BlackjackDealer extends Player implements Dealer {

    private DeckPile cards;
    private ArrayList<Player> players = new ArrayList<>();
    private int playerIndex;

    public BlackjackDealer( String name, Hand hand, DeckPile cards ) {
        super( name, hand );
        this.cards = cards;
    }

    public void passTurn() {
        if ( playerIndex != players.size() ) {
            Player player = players.get( playerIndex );
            playerIndex++;
            player.play( this );
        }
        else
            this.play( this );
    }

    public void addPlayer( Player player ) {
        players.add( player );
    }

    public void hit( Player player ) {
        player.addCard( cards.dealUp() );
    }

    /**
     * Override so that the dealer shows his cards before he starts to play.
     */
    public void player( Dealer dealer ) {
        exposeCards();
        super.play( dealer );
    }

    private void exposeCards() {
        getHand().turnOver();
        notifyListeners();
    }

    public void newGame() {
        // Deal cards and tell the first player to play.
        deal();
        passTurn();
    }

    public void deal() {
        cards.shuffle();
        // Reset each player and deal one card up to each and self.
        Player[] player = new Player[ players.size() ];
        players.toArray( player );
        for ( int i = 0; i < player.length; i++ ) {
            player[i].reset();
            player[i].addCard( cards.dealUp() );
        }
        this.addCard( cards.dealUp() );

        // Deal one more card to each player and one down to self.
        for ( int i = 0; i < player.length; i++ ) {
            player[i].addCard( cards.dealUp() );
        }
        this.addCard( cards.dealDown() );
    }

    protected void stopPlay( Dealer dealer ) {
        // Do nothing here in the dealer, simply let the game stop. If this were not
        // overridden, it would call passTurn() and loop forever.
    }

    protected boolean hit() {
        if ( getHand().total() <= 16 )
            return true;
        return false;
    }



}
