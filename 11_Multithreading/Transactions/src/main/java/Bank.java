import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

    private Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private static final long MAX_SAFE_TRANSFER = 50000;

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public static Account min(Account s1, Account s2) {
        return s1.compareTo(s2) > 0 ? s2 : s1;
    }

    public static Account max(Account s1, Account s2) {
        return s1.compareTo(s2) > 0 ? s1 : s2;
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
        Account accountTo = accounts.get(toAccountNum);
        synchronized(max(accountFrom, accountTo)) {
            synchronized(min(accountFrom, accountTo)) {
                accountFrom.setMoney(accountFrom.getMoney() - amount);
                accountTo.setMoney(accountTo.getMoney() + amount);
                try {
                    if (amount > MAX_SAFE_TRANSFER && isFraud(fromAccountNum, toAccountNum, amount)) {
                        accountFrom.setIsActive(false);
                        accountTo.setIsActive(false);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        synchronized (account) {
            return account.getMoney();
        }
    }
}