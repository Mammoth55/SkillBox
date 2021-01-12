public class Account implements Comparable<Account> {

    private boolean isActive;
    private String accountNumber;
    private long money;

    public Account(boolean isActive, String accountNumber, long money) {
        this.isActive = isActive;
        this.accountNumber = accountNumber;
        this.money = money;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public int compareTo(Account o) {
        return this.accountNumber.compareTo(o.accountNumber);
    }
}