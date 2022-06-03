package MVC;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BankAccountView extends JPanel implements Observer {

    public final static String DEPOSIT = "Deposit";
    public final static String WITHDRAW = "Withdraw";

    private BankAccountModel model;
    private BankAccountController controller;

    private JButton depositButton = new JButton( DEPOSIT );
    private JButton withdrawButton = new JButton( WITHDRAW );
    private JTextField amountField = new JTextField();
    private JLabel balanceLabel = new JLabel();

    public BankAccountView( BankAccountModel model ) {
        this.model = model;
        this.model.register( this );
        attachController( makeController() );
        buildUI();
    }

    @Override
    public void update() {
        balanceLabel.setText( "Balance: " + model.getBalance() );
    }

    public double getAmount() {
        // Assume that the user entered a valid number.
        return Double.parseDouble( amountField.getText() );
    }

    /**
     * Wires the given controller to the view, allows outside object to set the controller.
     */
    public void attachController( BankAccountController controller ) {
        this.controller = controller;
    }

    protected BankAccountController makeController() {
        return new BankAccountController( this, model );
    }

    private void buildUI() {
        setLayout( new BorderLayout() );

        // Associate each button with a command string.
        depositButton.setActionCommand( DEPOSIT );
        withdrawButton.setActionCommand( WITHDRAW );

        JPanel buttons = new JPanel( new BorderLayout() );
        JPanel balance = new JPanel( new BorderLayout() );
        buttons.add( depositButton, BorderLayout.WEST );
        buttons.add( withdrawButton, BorderLayout.EAST );
        balance.add( balanceLabel, BorderLayout.NORTH );
        balance.add( amountField, BorderLayout.SOUTH );
        add( balance, BorderLayout.NORTH );
        add( buttons, BorderLayout.SOUTH );

        depositButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed( ActionEvent event ) {
                        fireDepositEvent();
                    }
                }
        );

        withdrawButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed( ActionEvent event ) {
                        fireWithdrawEvent();
                    }
                }
        );

    }

    private void fireDepositEvent() {
        BankActivityEvent event = new BankActivityEvent( getAmount() );
        controller.depositActionPerformed( event );
    }

    private void fireWithdrawEvent() {
        BankActivityEvent event = new BankActivityEvent( getAmount() );
        controller.withdrawActionPerformed( event );
    }
}
