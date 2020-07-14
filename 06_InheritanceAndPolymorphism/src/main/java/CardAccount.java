public class CardAccount extends Account {

    protected final double comission = 1.01;

    public CardAccount(String requisite, double balance) {
        super(requisite, balance);
    }

    @Override
    public boolean decrementBalance(double amount) {
        boolean rsl = false;
        if (balance >= amount * comission) {
            balance -= amount * comission;
            rsl = true;
        }
        return rsl;
    }
}