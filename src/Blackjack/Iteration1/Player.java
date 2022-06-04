package Blackjack.Iteration1;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Player {

    private Hand hand;
    private String name;
    private ArrayList<PlayerListener> listeners = new ArrayList<>();

    public Player( String name, Hand hand ) {
        this.name = name;
        this.hand = hand;
    }

    public void addCard( Card card ) {
        hand.addCard( card );
        notifyListeners();
    }

    protected void notifyListeners() {
        Iterator listenersIterator = listeners.iterator();
        while ( listenersIterator.hasNext() ) {
            PlayerListener listener = ( PlayerListener ) listenersIterator.next();
            listener.handChanged( this );
        }
    }

    public void play( Dealer dealer ) {
        // Play until the player either busts or stands.
        while ( !isBusted() && hit() ) {
            dealer.hit( this );
        }
        // But not, tell the dealer that the player is done, otherwise, nothing will happen
        // when the player returns.
        stopPlay( dealer );
    }

    public boolean isBusted() {
        return hand.bust();
    }

    protected abstract boolean hit();

    /**
     * The call to passTurn MUST be inside a protected method. The Dealer needs to override this
     * behavior!! Otherwise it will loop forever.
     */
    protected void stopPlay( Dealer dealer ) {
        dealer.passTurn();
    }

    protected Hand getHand() {
        return hand;
    }

    public void addListener( PlayerListener listener ) {
        listeners.add( listener );
    }

    public void reset() {
        hand.reset();
    }

    public String toString() {
        return name + ": " + hand.toString();
    }
}
