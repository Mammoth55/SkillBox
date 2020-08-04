public class Manager extends CoWorker implements Employee {

    public Manager(Company company, String position, String name, double balance) {
        super(company, position, name, balance);
        this.setBonusBase(Math.random() * 25000 + 115000);
    }

    @Override
    public double getMonthSalary() {
        return this.company.fixedPartManagerSalary + 0.05 * this.bonusBase;
    }
}