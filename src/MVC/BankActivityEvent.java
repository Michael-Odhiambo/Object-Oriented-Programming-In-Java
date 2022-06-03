package MVC;

public class BankActivityEvent {
    private double amount;

    public BankActivityEvent( double amount ) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
