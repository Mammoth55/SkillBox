import java.util.Objects;

public abstract class Client {

    protected String inn;
    protected String name;
    protected Account account;

    public Client(String inn, String name, Account account) {
        this.inn = inn;
        this.name = name;
        this.account = account;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getBalance() {
        return this.account.getBalance();
    }

    public abstract boolean incrementBalance(double amount);

    public abstract boolean decrementBalance(double amount);

    public abstract void about();

    @Override
    public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Client client = (Client) o;
            return Objects.equals(inn, client.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inn);
    }
}