package Blackjack.Iteration2;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Player {

    private Hand hand;
    private String name;
    private PlayerState currentState;
    private ArrayList<PlayerListener> listeners = new ArrayList<>();

    public Player( String name, Hand hand ) {
        this.name = name;
        this.hand = hand;
        setCurrentState( getInitialState() );
    }

    /**
     * This method can be accessed by subclasses but cannot be overriden
     */
    protected final void setCurrentState( PlayerState state ) {
        currentState = state;
        hand.setHolder( state );
    }

    public void addCard( Card card ) {
        hand.addCard( card );
    }

    public void play( Dealer dealer ) {
        currentState.execute( dealer );
    }

    public void reset() {
        hand.reset();
        setCurrentState( getInitialState() );
        notifyChanged();
    }

    protected PlayerState getInitialState() {
        return new WaitingState();
    }

    protected void notifyChanged() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) i.next();
            playerListener.playerChanged( this );
        }
    }

    public void addListener( PlayerListener playerListener ) {
        listeners.add( playerListener );
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name + ": " + hand.toString();
    }

    public void win() {
        notifyWin();
    }

    protected void notifyWin() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) i.next();
            playerListener.playerWon( this );
        }
    }

    public void lose() {
        notifyLose();
    }

    protected void notifyLose() {
        Iterator i = listeners.iterator();
        PlayerListener playerListener = ( PlayerListener ) i.next();
        playerListener.playerLost( this );
    }

    public void standOff() {
        notifyStandoff();
    }

    protected void notifyStandoff() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) i.next();
            playerListener.playerStandoff( this );
        }
    }
    public void blackjack() {
        notifyBlackjack();
    }

    protected void notifyBlackjack() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) i.next();
            playerListener.playerBlackjack( this );
        }
    }

    protected void notifyStanding() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) i.next();
            playerListener.playerStanding( this );
        }
    }

    protected void notifyBusted() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) i.next();
            playerListener.playerBusted( this );
        }
    }

    public Hand getHand() {
        return this.hand;
    }

    protected final PlayerState getCurrentState() {
        return this.currentState;
    }

    protected PlayerState getBustedState() {
        return new BustedState();
    }

    protected PlayerState getStandingState() {
        return new StandingState();
    }

    protected PlayerState getPlayingState() {
        return new PlayingState();
    }

    protected PlayerState getWaitingState() {
        return new WaitingState();
    }

    protected PlayerState getBlackjackState() {
        return new BlackjackState();
    }

    protected abstract boolean hit();

    private class WaitingState implements PlayerState {

        @Override
        public void handPlayable() {
            setCurrentState( getPlayingState() );  // Transition
        }

        @Override
        public void handBlackjack() {
            setCurrentState( getBlackjackState() );  // Transition
            notifyBlackjack();
        }

        @Override
        public void handBusted() {
            // Not possible in waiting state
        }

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute( Dealer dealer ) {
            // Do nothing while waiting
        }
    }

    private class BustedState implements PlayerState {

        @Override
        public void handPlayable() {
            // Not possible in busted state
        }

        @Override
        public void handBlackjack() {
            // Not possible in busted state
        }

        @Override
        public void handBusted() {
            // Not possible in busted state
        }

        @Override
        public void handChanged() {
            // Not possible in busted state
        }

        @Override
        public void execute( Dealer dealer ) {
            dealer.busted( Player.this );
        }
    }

    private class BlackjackState implements PlayerState {

        @Override
        public void handPlayable() {
            // Not possible in blackjack state
        }

        @Override
        public void handBlackjack() {
            // Not possible in blackjack state
        }

        @Override
        public void handBusted() {
            // Not possible in blackjack state
        }

        @Override
        public void handChanged() {
            // Not possible in blackjack state
        }

        @Override
        public void execute( Dealer dealer ) {
            dealer.blackjack( Player.this );
        }
    }

    private class StandingState implements PlayerState {
        @Override
        public void handPlayable() {
            // Not possible in standing state
        }

        @Override
        public void handBlackjack() {
            // Not possible in standing state
        }

        @Override
        public void handBusted() {
            // Not possible in standing state
        }

        @Override
        public void handChanged() {
            // Not possible in standing state
        }

        @Override
        public void execute( Dealer dealer ) {
            dealer.standing( Player.this );
        }
    }

    private class PlayingState implements PlayerState {

        @Override
        public void handPlayable() {
            // Can ignore in playing state
        }

        @Override
        public void handBlackjack() {
            // Not possible in playing state
        }

        @Override
        public void handBusted() {
            setCurrentState( getBustedState() );
            notifyBusted();
        }

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute( Dealer dealer ) {
            if ( hit() )
                dealer.hit( Player.this );
            else {
                setCurrentState( getStandingState() );
                notifyStanding();
            }
            currentState.execute( dealer );  // Transition
        }
    }
}
