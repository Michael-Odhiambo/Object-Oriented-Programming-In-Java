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

    protected final void setCurrentState( PlayerState state ) {
        currentState = state;
        hand.setHolder( state );
    }

    protected final PlayerState getCurrentState() {
        return currentState;
    }

    public void addCard( Card card ) {
        hand.addCard( card );
    }

    protected void notifyChanged() {
        Iterator listenersIterator = listeners.iterator();
        while ( listenersIterator.hasNext() ) {
            PlayerListener listener = ( PlayerListener ) listenersIterator.next();
            listener.playerChanged( this );
        }
    }

    public void play( Dealer dealer ) {
        currentState.execute( dealer );
    }

    public boolean isBusted() {
        return hand.bust();
    }

    protected abstract boolean hit();

    public void win() {
        notifyWin();
    }

    protected void notifyWin() {
        Iterator listenersIterator = listeners.iterator();
        while ( listenersIterator.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) listenersIterator.next();
            playerListener.playerWon( this );
        }
    }

    public void lose() {
        notifyLose();
    }

    protected void notifyLose() {
        Iterator listenersIterator = listeners.iterator();
        while ( listenersIterator.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) listenersIterator.next();
            playerListener.playerLost( this );
        }
    }

    public void standOff() {
        notifyStandOff();
    }

    protected void notifyStandOff() {
        Iterator listenersIterator = listeners.iterator();
        while ( listenersIterator.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) listenersIterator.next();
            playerListener.playerStandoff( this );
        }
    }

    public void blackjack() {
        notifyBlackjack();
    }

    protected void notifyBlackjack() {
        Iterator listenersIterator = listeners.iterator();
        while ( listenersIterator.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) listenersIterator.next();
            playerListener.playerBlackjack( this );
        }
    }

    protected void notifyStanding() {
        Iterator listenersIterator = listeners.iterator();
        while ( listenersIterator.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) listenersIterator.next();
            playerListener.playerStanding( this );
        }
    }

    protected void notifyBusted() {
        Iterator listenersIterator = listeners.iterator();
        while ( listenersIterator.hasNext() ) {
            PlayerListener playerListener = ( PlayerListener ) listenersIterator.next();
            playerListener.playerBusted( this );
        }
    }

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
        setCurrentState( getInitialState() );
        notifyChanged();
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName() + ": " + hand.toString();
    }

    protected PlayerState getBustedState() {
        return new Busted();
    }

    protected PlayerState getStandingState() {
        return new Standing();
    }

    protected PlayerState getPlayingState() {
        return new Playing();
    }

    protected PlayerState getWaitingState() {
        return new Waiting();
    }

    protected PlayerState getBlackjackState() {
        return new Blackjack();
    }

    protected PlayerState getInitialState() {
        return new Waiting();
    }


    private class Waiting implements PlayerState {
        public void handChanged() {
            notifyChanged();
        }

        public void handPlayable() {
            setCurrentState(getPlayingState());
            // Transition
        }

        public void handBlackjack() {
            setCurrentState(getBlackjackState());
            // Transition
        }

        public void handBusted() {
            // Not possible in waiting state
        }

        public void execute(Dealer dealer) {
            // Do nothing while waiting
        }
    }

        private class Busted implements PlayerState {
            public void handChanged() {
                // Not possible in busted state
            }

            public void handPlayable() {
                // Not possible in busted state
            }

            public void handBlackjack() {
                // Not possible in busted state
            }

            public void handBusted() {
                // Not possible in busted state
            }

            public void execute( Dealer dealer ) {
                dealer.busted( Player.this );
                // Terminate
            }
        }

        private class Blackjack implements PlayerState {
            public void handChanged() {
                // Not possible in Blackjack state
            }

            public void handPlayable() {
                // Not possible in Blackjack state
            }

            public void handBlackjack() {
                // Not possible in blackjack state
            }

            public void handBusted() {
                // Not possible in blackjack state
            }

            public void execute( Dealer dealer ) {
                dealer.blackjack( Player.this );
                // Terminate
            }
        }

        private class Standing implements PlayerState {

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
            public void execute(Dealer dealer) {
                dealer.standing( Player.this );
                // Terminate
            }
        }

        private class Playing implements PlayerState {

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
            public void execute(Dealer dealer) {
                if ( hit() )
                    dealer.hit( Player.this );
                else {
                    setCurrentState( getStandingState() );
                    notifyStanding();
                }
                currentState.execute( dealer );
                // Transition
            }
        }
}
