package MVC;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class MVCDriver {

    public static void main( String[] args ) {
        BankAccountModel model = new BankAccountModel( 1000000.00 );
        BankAccountView view = new BankAccountView( model );
        BankAccountCLV clv = new BankAccountCLV( model );

        JFrame frame = new JFrame();

        WindowAdapter windowAdapter = new WindowAdapter() {
            public void windowClosing( WindowEvent event ) {
                System.exit( 0 );
            }
        };

        frame.addWindowListener( windowAdapter );
        frame.getContentPane().add( view );
        frame.pack();
        frame.show();
    }
}
