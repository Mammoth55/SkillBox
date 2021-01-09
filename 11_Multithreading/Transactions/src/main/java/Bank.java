import java.util.Hashtable;
import java.util.Random;

public class Bank {

    private Hashtable<String, Account> accounts = new Hashtable<>();
    private final Random random = new Random();
    private static final long MAXSAFETRANSFER = 50000;

    public Hashtable<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Hashtable<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account accountFrom = accounts.get(fromAccountNum);
        accountFrom.setMoney(accountFrom.getMoney() - amount);
        Account accountTo = accounts.get(toAccountNum);
        accountTo.setMoney(accountTo.getMoney() + amount);
        try {
            if (amount > MAXSAFETRANSFER && isFraud(fromAccountNum, toAccountNum, amount)) {
                accountFrom.setIsActive(false);
                accountTo.setIsActive(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accounts.put(fromAccountNum, accountFrom);
        accounts.put(toAccountNum, accountTo);
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }
}