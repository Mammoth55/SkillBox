import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
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
    public void whenTransferAndGetBalance() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future> tasks = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            tasks.add(executorService.submit(() -> {
                long amount = finalI * 1000 + 31000;
                for (String keyFrom : bank.getAccounts().keySet()) {
                    for (String keyTo : bank.getAccounts().keySet()) {
                        if (keyFrom.equals(keyTo)) {
                            continue;
                        }
                        bank.transfer(keyFrom, keyTo, amount);
                    }
                }
            }));
        }
        executorService.shutdown();
        for (Future task : tasks) {
            task.get();
        }
        long actual = 0;
        for (String key : bank.getAccounts().keySet()) {
            actual += bank.getBalance(key);
        }
        assertEquals(TOTAL_MONEY, actual);
    }
}