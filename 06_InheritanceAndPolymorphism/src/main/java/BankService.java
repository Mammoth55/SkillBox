import java.util.*;

public class BankService {

    private static Set<Account> accounts = new HashSet<>();

    public Optional<Account> findByRequisite(String requisite) {
        return accounts.stream().filter(account -> account.getRequisite().equals(requisite)).findFirst();
    }

    public boolean sendMoney(String srcRequisite, String destRequisite, double amount) {
        boolean status = false;
        Optional<Account> srcAccount = findByRequisite(srcRequisite);
        Optional<Account> destAccount = findByRequisite(destRequisite);
        if (srcAccount.isPresent() && destAccount.isPresent() && srcAccount.get().getBalance() >= amount && amount > 0) {
            if (srcAccount.get().decrementBalance(amount)) {
                destAccount.get().incrementBalance(amount);
                status = true;
            }
        }
        return status;
    }
}