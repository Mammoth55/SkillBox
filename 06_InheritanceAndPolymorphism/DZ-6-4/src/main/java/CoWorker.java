public abstract class CoWorker implements Employee {

    private Company company;
    private String position;
    private String name;
    private double balance;
    private double bonusBase;

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

    @Override
    public double getBonusBase() {
        return bonusBase;
    }

    @Override
    public void setBonusBase(double bonusBase) {
        this.bonusBase = bonusBase;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public abstract double getMonthSalary();
}