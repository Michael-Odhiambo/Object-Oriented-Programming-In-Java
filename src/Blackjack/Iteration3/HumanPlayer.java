package Blackjack.Iteration3;

public class HumanPlayer extends BettingPlayer {

    private static final String HIT = "H";
    private static final String STAND = "S";
    private static final String PLAYMESSAGE = "[H]it or [S]tand";
    private static final String BETMESSAGE = "Place Bet: [10] [50] or [100]";
    private static final String BET10 = "10";
    private static final String BET50 = "50";
    private static final String BET100 = "100";
    private static final String DEFAULT = "Invalid";

    public HumanPlayer( String name, Hand hand, Bank bank ) {
        super( name, hand, bank );
    }

    @Override
    protected boolean hit() {
        while ( true ) {
            Console.INSTANCE.printMessage( PLAYMESSAGE );
            String response = Console.INSTANCE.readInput( DEFAULT );
            if ( response.equalsIgnoreCase( HIT ) )
                return true;
            else if ( response.equalsIgnoreCase( STAND ) )
                return false;
            // If we get here, loop until we get meaningful input.
        }
    }

    protected void bet() {
        while ( true ) {
            Console.INSTANCE.printMessage( BETMESSAGE );
            String response = Console.INSTANCE.readInput( DEFAULT );
            if ( response.equals( BET10 ) ) {
                getBank().place10Bet();
                return;
            }
            if ( response.equals( BET50 ) ) {
                getBank().place50Bet();
                return;
            }
            if ( response.equals( BET100 ) ) {
                getBank().place100Bet();
                return;
            }
        }
    }
}
