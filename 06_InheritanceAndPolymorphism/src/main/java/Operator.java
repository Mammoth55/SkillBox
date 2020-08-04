public class Operator extends CoWorker implements Employee {

    public Operator(Company company, String position, String name, double balance) {
        super(company, position, name, balance);
        this.setBonusBase(0);
    }

    @Override
    public double getMonthSalary() {
        return this.company.fixedPartOperatorSalary;
    }
}