public class ClientFL extends Client {

    public ClientFL(String inn, String name, Account account) {
        super(inn, name, account);
    }

    @Override
    public boolean incrementBalance(double amount) {
        return this.account.incrementBalance(amount);
    }

    @Override
    public boolean decrementBalance(double amount) {
        return this.account.decrementBalance(amount);
    }

    @Override
    public void about() {
        System.out.println("У физических лиц пополнение и снятие происходит без комиссии.");
    }
}