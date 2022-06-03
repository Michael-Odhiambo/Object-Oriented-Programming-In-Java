package MVC;

import java.util.ArrayList;
import java.util.Iterator;

public class BankAccountModel {

    private double balance;
    private ArrayList<Observer> listeners = new ArrayList<>();

    public BankAccountModel( double initialDeposit ) {
        setBalance( initialDeposit );
    }

    protected void setBalance( double newBalance ) {
        this.balance = newBalance;
        updateObservers();
    }

    private void updateObservers() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            Observer observer = ( Observer ) i.next();
            observer.update();
        }
    }

    public void depositFunds( double amount ) {
        setBalance( getBalance() + amount );
    }

    public double getBalance() {
        return this.balance;
    }

    public double withdrawFunds( double amount ) {
        if ( amount > getBalance() )
            amount = getBalance();
        setBalance( getBalance() - amount );
        return amount;
    }

    public void register( Observer observer ) {
        listeners.add( observer );
        observer.update();
    }

    public void deregister( Observer observer ) {
        listeners.remove( observer );
    }
}
