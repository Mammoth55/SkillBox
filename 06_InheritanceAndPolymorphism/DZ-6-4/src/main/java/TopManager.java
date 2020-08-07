public class TopManager extends CoWorker implements Employee {

    public TopManager(Company company, String position, String name, double balance) {
        super(company, position, name, balance);
        this.setBonusBase(0);
    }

    @Override
    public double getMonthSalary() {
        return this.getCompany().getIncome() > 9999999 ?
                this.getCompany().getFixedPartSalaries().get(this.getClass()) * 2.5
                : this.getCompany().getFixedPartSalaries().get(this.getClass());
    }
}