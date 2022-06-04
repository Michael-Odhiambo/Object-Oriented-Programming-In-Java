package Blackjack.Iteration1;

public class HumanPlayer extends Player {

    private static final String HIT = "H";
    private static final String STAND = "S";
    private static final String MESSAGE = "[H]it or [S]tand";
    private static final String DEFAULT = "Invalid";

    public HumanPlayer( String name, Hand hand ) {
        super( name, hand );
    }

    @Override
    protected boolean hit() {
        while ( true ) {
            Console.INSTANCE.printMessage( MESSAGE );
            String response = Console.INSTANCE.readInput( DEFAULT );
            if ( response.equalsIgnoreCase( HIT ) )
                return true;
            else if ( response.equalsIgnoreCase( STAND ) )
                return false;
            // If we get here, loop until we get meaningful input.
        }
    }
}
