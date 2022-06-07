package Blackjack.Iteration2;

import java.util.ArrayList;
import java.util.Iterator;

public class BlackjackDealer extends Player implements Dealer {

    private DeckPile cards;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> waitingPlayers;
    private ArrayList<Player> standingPlayers;
    private ArrayList<Player> bustedPlayers;
    private ArrayList<Player> blackjackPlayers;

    public BlackjackDealer( String name, Hand hand, DeckPile cards ) {
        super( name, hand );
        this.cards = cards;
    }

    public void blackjack( Player player ) {
        blackjackPlayers.add( player );
        play( this );
    }

    public void busted( Player player ) {
        bustedPlayers.add( player );
        play( this );
    }

    public void standing( Player player ) {
        standingPlayers.add( player );
        play( this );
    }

    public void hit( Player player ) {
        player.addCard( cards.dealUp() );
    }

    public void addPlayer( Player player ) {
        players.add( player );
    }

    public void reset() {
        waitingPlayers = new ArrayList<>();
        standingPlayers = new ArrayList<>();
        bustedPlayers = new ArrayList<>();
        blackjackPlayers = new ArrayList<>();
        waitingPlayers.addAll( players );
        cards.reset();
        Iterator i = waitingPlayers.iterator();
        while ( i.hasNext() ) {
            Player player = ( Player ) i.next();
            player.reset();
        }
    }

    public void newGame() {
        reset();
        play( this );
    }

    public void deal() {
        cards.shuffle();
        Player[] player = new Player[ waitingPlayers.size() ];
        waitingPlayers.toArray( player );
        for ( int i = 0; i < player.length; i++ )
            player[i].addCard( cards.dealUp() );
        this.addCard( cards.dealUp() );

        for ( int i = 0; i < player.length; i++ )
            player[i].addCard( cards.dealUp() );
        this.addCard( cards.dealDown() );
    }

    protected boolean hit() {
        if ( standingPlayers.size() > 0 && getHand().total() < 17 )
            return true;
        return false;
    }

    private void exposeHand() {
        getHand().turnOver();
        notifyChanged();
    }

    protected PlayerState getBlackjackState() {
        return new DealerBlackjackState();
    }

    protected PlayerState getDealingState() {
        return new DealerDealingState();
    }

    protected PlayerState getBustedState() {
        return new DealerBustedState();
    }

    protected PlayerState getStandingState() {
        return new DealerStandingState();
    }

    protected PlayerState getWaitingState() {
        return new DealerWaitingState();
    }

    protected PlayerState getInitialState() {
        return new DealerDealingState();
    }

    private class DealerBustedState implements PlayerState {

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
            Iterator i = standingPlayers.iterator();
            while ( i.hasNext() ) {
                Player player = ( Player ) i.next();
                player.win();
            }
            i = blackjackPlayers.iterator();
            while ( i.hasNext() ) {
                Player player = ( Player ) i.next();
                player.blackjack();
            }
            i = bustedPlayers.iterator();
            while ( i.hasNext() ) {
                Player player = ( Player ) i.next();
                player.lose();
            }
        }
    }

    private class DealerBlackjackState implements PlayerState {

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
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {
            exposeHand();
            Iterator i = players.iterator();
            while ( i.hasNext() ) {
                Player player = ( Player ) i.next();
                if ( player.getHand().blackjack() )
                    player.standOff();
                else
                    player.lose();
            }
        }
    }

    private class DealerStandingState implements PlayerState {

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
            Iterator i = standingPlayers.iterator();
            while ( i.hasNext() ) {
                Player player = ( Player ) i.next();
                if ( player.getHand().isEqual( getHand() ) )
                    player.standOff();
                else if ( player.getHand().isGreaterThan( getHand() ) )
                    player.win();
                else
                    player.lose();
            }
        }
    }

    private class DealerWaitingState implements PlayerState {

        @Override
        public void handPlayable() {
            // Not possible in waiting state
        }

        @Override
        public void handBlackjack() {
            // Not possible in waiting state
        }

        @Override
        public void handBusted() {
            // Not possible in waiting state
        }

        @Override
        public void handChanged() {
            // Not possible in waiting state
        }

        @Override
        public void execute( Dealer dealer ) {
            if ( !waitingPlayers.isEmpty() ) {
                Player player = waitingPlayers.get( 0 );
                waitingPlayers.remove( player );
                player.play( dealer );
            }
            else {
                setCurrentState( getPlayingState() );
                exposeHand();
                getCurrentState().execute( dealer );  // Transition and execute
            }
        }
    }

    private class DealerDealingState implements PlayerState {

        @Override
        public void handPlayable() {
            setCurrentState( getWaitingState() );  // Transition
        }

        @Override
        public void handBlackjack() {
            setCurrentState( getBlackjackState() );  // Transition
            notifyBlackjack();
        }

        @Override
        public void handBusted() {
            // Not possible in dealing state
        }

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute( Dealer dealer ) {
            deal();
            getCurrentState().execute( dealer );  // Transition and execute
        }
    }
}