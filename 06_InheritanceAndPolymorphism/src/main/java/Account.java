import java.util.GregorianCalendar;
import java.util.Objects;

public class Account {

    protected String requisite;
    protected double balance;
    protected GregorianCalendar addDate;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
        this.addDate = new GregorianCalendar();
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public double getBalance() {
        return balance;
    }

    public void incrementBalance(double amount) {
        this.balance += amount;
        this.addDate = new GregorianCalendar();
    }

    public boolean decrementBalance(double amount) {
        boolean rsl = false;
        if (balance >= amount) {
            balance -= amount;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}