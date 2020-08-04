public class TopManager extends CoWorker implements Employee {

    public TopManager(Company company, String position, String name, double balance) {
        super(company, position, name, balance);
        this.setBonusBase(0);
    }

    @Override
    public double getMonthSalary() {
        return this.company.getIncome() > 9999999 ?
                this.company.fixedPartTopManagerSalary * 2.5 : this.company.fixedPartTopManagerSalary;
    }
}