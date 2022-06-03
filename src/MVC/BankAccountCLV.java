package MVC;

public class BankAccountCLV implements Observer {

    private BankAccountModel model;

    public BankAccountCLV( BankAccountModel model ) {
        this.model = model;
        this.model.register( this );
    }

    public void update() {
        System.out.println( "Current Balance: $" + model.getBalance() );
    }
}
