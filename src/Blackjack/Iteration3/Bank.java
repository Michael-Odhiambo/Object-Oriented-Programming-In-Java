package Blackjack.Iteration3;

public class Bank {

    private int total, bet;

    public Bank( int initialAmount ) {
        total = initialAmount;
    }

    public void place100Bet() {
        placeBet( 100 );
    }

    public void place50Bet() {
        placeBet( 50 );
    }

    public void place10Bet() {
        placeBet( 10 );
    }

    public void win() {
        total += ( 2 * bet );
        bet = 0;
    }

    public void lose() {
        // Already taken out of total;
        bet = 0;
    }

    public void blackjack() {
        total += ( ( ( 3 * bet ) / 2 ) + bet );
        bet = 0;
    }

    public void standOff() {
        total += bet;
        bet = 0;
    }

    private void placeBet( int amount ) {
        bet = amount;
        total -= amount;
    }

    public String toString() {
        return ( "$" + total + ".00" );
    }
}
