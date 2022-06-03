package MVC;

public class Console implements Observer {

    // Console singleton.
    public static final Console INSTANCE = new Console();
    private BankAccountModel model;

    // Private to avoid instantiation.
    private Console() {}

    public void setModel( BankAccountModel model ) {
        this.model = model;
        this.model.register( this );
    }

    public void update() {
        System.out.println( "Current Balance: $" + model.getBalance() );
    }
}
