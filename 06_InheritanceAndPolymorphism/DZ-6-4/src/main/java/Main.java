import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Class, Integer> fixedSalaries
                = Map.of(Operator.class,25000, Manager.class, 30000, TopManager.class, 80000);
        Company company = new Company("JAVA Ltd.", fixedSalaries);
        for (int i = 0; i < 180; i++) {
            Employee operator = new Operator(company,"Operator", "No Name", 0);
            company.hire(operator);
        }
        for (int i = 0; i < 80; i++) {
            Employee manager = new Manager(company,"Manager", "No Name", 0);
            company.hire(manager);
        }
        for (int i = 0; i < 10; i++) {
            Employee topManager = new TopManager(company,"TopManager", "No Name", 0);
            company.hire(topManager);
        }
        System.out.println("START SIZE : " + company.getStaff().size());
        System.out.println("TOP15 :");
        for (Employee emp : company.getTopSalaryStaff(15)) {
            System.out.println(emp.getMonthSalary());
        }
        System.out.println("BOTTOM30 :");
        for (Employee emp : company.getLowestSalaryStaff(30)) {
            System.out.println(emp.getMonthSalary());
        }
        System.out.println("STAFF before Dismissal : " + company.getStaff().size());
        for (int i = 265; i < 270; i++) {
            company.getStaff().remove(265);
        }
        for (int i = 220; i < 260; i++) {
            company.getStaff().remove(220);
        }
        for (int i = 90; i < 180; i++) {
            company.getStaff().remove(90);
        }
        System.out.println("STAFF after Dismissal : " + company.getStaff().size());
        System.out.println("TOP15 :");
        for (Employee emp : company.getTopSalaryStaff(15)) {
            System.out.println(emp.getMonthSalary());
        }
        System.out.println("BOTTOM30 :");
        for (Employee emp : company.getLowestSalaryStaff(30)) {
            System.out.println(emp.getMonthSalary());
        }
    }
}