package MVC;

import java.awt.event.ActionEvent;

public class BankAccountController implements BankAccountActivityListener {

    private BankAccountView view;
    private BankAccountModel model;

    public BankAccountController( BankAccountView view, BankAccountModel model ) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void withdrawActionPerformed( BankActivityEvent event ) {
        model.withdrawFunds( event.getAmount() );
    }

    @Override
    public void depositActionPerformed( BankActivityEvent event ) {
        model.depositFunds( event.getAmount() );
    }
}
