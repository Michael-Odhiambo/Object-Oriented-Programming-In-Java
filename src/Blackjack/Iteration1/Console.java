package Blackjack.Iteration1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Console implements PlayerListener {

    // Console singleton.
    public static final Console INSTANCE = new Console();
    private BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );

    private Console() {}

    public void printMessage( String message ) {
        System.out.println( message );
    }

    public String readInput( String defaultInput ) {
        String response;
        try {
            response = in.readLine();
        }
        catch ( IOException ioe ) {
            return defaultInput;
        }
        return response;
    }

    public void handChanged( Player player ) {
        printMessage( player.toString() );
    }
}
