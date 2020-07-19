public class ClientUL extends Client {

    public ClientUL(String inn, String name, Account account) {
        super(inn, name, account);
    }

    @Override
    public boolean incrementBalance(double amount) {
        return this.account.incrementBalance(amount);
    }

    @Override
    public boolean decrementBalance(double amount) {
        return this.account.decrementBalance(amount * 1.01);
    }

    @Override
    public void about() {
        System.out.println("У юридических лиц — снятие с комиссией 1%.");
    }
}