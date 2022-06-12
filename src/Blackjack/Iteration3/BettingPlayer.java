package Blackjack.Iteration3;

public abstract class BettingPlayer extends Player {

    private Bank bank;

    public BettingPlayer( String name, Hand hand, Bank bank ) {
        super( name, hand );
        this.bank = bank;
    }

    public String toString() {
        return ( getName() + ": " + getHand().toString() + " " + bank.toString() );
    }

    public void win() {
        bank.win();
        super.win();
    }

    public void lose() {
        bank.lose();
        super.lose();
    }

    public void standOff() {
        bank.standOff();
        super.standOff();
    }

    public void blackjack() {
        bank.blackjack();
        super.blackjack();
    }

    protected PlayerState getInitialState() {
        return getBettingState();
    }
    protected PlayerState getBettingState() {
        return new BettingState();
    }

    protected final Bank getBank() {
        return this.bank;
    }

    protected abstract void bet();

    private class BettingState implements PlayerState {
        public void handChanged() {
            // Not possible in betting state
        }

        public void handPlayable() {
            // Not possible in betting state
        }

        public void handBlackjack() {
            // Not possible in betting state
        }

        public void handBusted() {
            // Not possible in betting state
        }

        public void execute( Dealer dealer ) {
            bet();
            setCurrentState( getWaitingState() );
            dealer.doneBetting( BettingPlayer.this );
        }
    }
}
