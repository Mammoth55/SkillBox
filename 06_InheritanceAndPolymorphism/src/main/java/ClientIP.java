import java.util.GregorianCalendar;

public class ClientIP extends Client {

    public ClientIP(String inn, String name, Account account) {
        super(inn, name, account);
    }

    @Override
    public boolean incrementBalance(double amount) {
        if (amount < 1000 && amount > 0) {
            amount *= 0.99;
        } else if (amount >= 1000) {
            amount *= 0.995;
        } else {
            amount = 0;
        }
        return this.account.incrementBalance(amount);
    }

    @Override
    public boolean decrementBalance(double amount) {
        return this.account.decrementBalance(amount);
    }

    @Override
    public void about() {
        System.out.println("У ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей.\n"
                + "И с комиссией 0,5%, если сумма больше либо равна 1000 рублей.");
    }
}