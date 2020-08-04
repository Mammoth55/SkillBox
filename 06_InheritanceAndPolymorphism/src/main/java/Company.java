import java.util.*;

public class Company {

    public String name;
    public List<Employee> staff = new ArrayList<>();
    public double fixedPartOperatorSalary = 25000;
    public double fixedPartManagerSalary = 20000;
    public double fixedPartTopManagerSalary = 80000;

    public Company(String name) {
        this.name = name;
    }

    public double getIncome() {
        return Math.random() * 2000000 + 9000000;
    }

    public void hire(Employee employee) {
        staff.add(employee);
    }

    public void hireAll(List<Employee> novices) {
        for (Employee novice : novices) {
            staff.add(novice);
        }
    }

    public void fire(Employee employee) {
        staff.remove(employee);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        List<Employee> list = new ArrayList<>(this.staff);
        if (count > 0 && count < list.size()) {
            Collections.sort(list, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return (int) (o1.getMonthSalary() - o2.getMonthSalary());
                }
            });
            while (list.size() > count) {
                list.remove(count);
            }
        }
        return list;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        List<Employee> list = new ArrayList<>(this.staff);
        if (count > 0 && count <= list.size()) {
            Collections.sort(list, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return (int) (o2.getMonthSalary() - o1.getMonthSalary());
                }
            });
            while (list.size() > count) {
                list.remove(count);
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
        Company company = new Company("JAVA Ltd.");
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
        System.out.println("START SIZE : " + company.staff.size());
        System.out.println("TOP15 :");
        for (Employee emp : company.getTopSalaryStaff(15)) {
            System.out.println(emp.getMonthSalary());
        }
        System.out.println("BOTTOM30 :");
        for (Employee emp : company.getLowestSalaryStaff(30)) {
            System.out.println(emp.getMonthSalary());
        }
        System.out.println("STAFF before REMOVE : " + company.staff.size());
        for (int i = 265; i < 270; i++) {
            company.staff.remove(265);
        }
        for (int i = 220; i < 260; i++) {
            company.staff.remove(220);
        }
        for (int i = 90; i < 180; i++) {
            company.staff.remove(90);
        }
        System.out.println("STAFF after REMOVE : " + company.staff.size());
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