import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class BankTest {

    private Bank bank;
    private final long TOTAL_MONEY = 210000000;

    @Before
    public void whenBegin() {
        bank = new Bank();
        Map<String, Account> accountsTest = new ConcurrentHashMap<>();
        accountsTest.put("0000000000", new Account(true, "0000000000", 10000000));
        accountsTest.put("0000000001", new Account(true, "0000000001", 10000000));
        accountsTest.put("0000000002", new Account(true, "0000000002", 10000000));
        accountsTest.put("0000000003", new Account(true, "0000000003", 10000000));
        accountsTest.put("0000000004", new Account(true, "0000000004", 10000000));
        accountsTest.put("0000000005", new Account(true, "0000000005", 10000000));
        accountsTest.put("0000000006", new Account(true, "0000000006", 10000000));
        accountsTest.put("0000000007", new Account(true, "0000000007", 10000000));
        accountsTest.put("0000000008", new Account(true, "0000000008", 10000000));
        accountsTest.put("0000000009", new Account(true, "0000000009", 10000000));
        accountsTest.put("0000000010", new Account(true, "0000000010", 10000000));
        accountsTest.put("0000000011", new Account(true, "0000000011", 10000000));
        accountsTest.put("0000000012", new Account(true, "0000000012", 10000000));
        accountsTest.put("0000000013", new Account(true, "0000000013", 10000000));
        accountsTest.put("0000000014", new Account(true, "0000000014", 10000000));
        accountsTest.put("0000000015", new Account(true, "0000000015", 10000000));
        accountsTest.put("0000000016", new Account(true, "0000000016", 10000000));
        accountsTest.put("0000000017", new Account(true, "0000000017", 10000000));
        accountsTest.put("0000000018", new Account(true, "0000000018", 10000000));
        accountsTest.put("0000000019", new Account(true, "0000000019", 10000000));
        accountsTest.put("0000000020", new Account(true, "0000000020", 10000000));
        bank.setAccounts(accountsTest);
    }

    @Test
    public void whenTransfer() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 15; i++) {
            int finalI = i;
            executorService.execute(() -> {
                long amount = finalI * 1000 + 32000;
                for (String keyFrom : bank.getAccounts().keySet()) {
                    for (String keyTo : bank.getAccounts().keySet()) {
                        if (keyFrom.equals(keyTo)) {
                            continue;
                        }
                        synchronized (bank) {
                            bank.transfer(keyFrom, keyTo, amount);
                        }
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        long actual = 0;
        for (String key : bank.getAccounts().keySet()) {
            actual += bank.getBalance(key);
        }
        assertEquals(TOTAL_MONEY, actual);
    }

    @Test
    public void whenGetBalance() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                synchronized (bank) {
                    for (String key : bank.getAccounts().keySet()) {
                        bank.getBalance(key);
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        long actual = 0;
        for (String key : bank.getAccounts().keySet()) {
            actual += bank.getBalance(key);
        }
        assertEquals(TOTAL_MONEY, actual);
    }
}