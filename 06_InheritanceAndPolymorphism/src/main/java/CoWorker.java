public class CoWorker {

    protected Company company;
    protected String position;
    protected String name;
    protected double balance;
    protected double bonusBase;

    public CoWorker(Company company, String position, String name, double balance) {
        this.company = company;
        this.position = position;
        this.name = name;
        this.balance = balance;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBonusBase() {
        return bonusBase;
    }

    public void setBonusBase(double bonusBase) {
        this.bonusBase = bonusBase;
    }
}