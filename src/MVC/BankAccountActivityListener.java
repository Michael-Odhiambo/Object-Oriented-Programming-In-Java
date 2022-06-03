package MVC;

public interface BankAccountActivityListener {
    abstract void withdrawActionPerformed( BankActivityEvent event );
    abstract void depositActionPerformed( BankActivityEvent event );
}
