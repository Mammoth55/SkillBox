import java.util.*;

public class Company {

    private String name;
    private List<Employee> staff;
    private Map<Class, Integer> fixedPartSalaries;

    public Company(String name, Map<Class, Integer> fixedSalaries) {
        this.name = name;
        this.fixedPartSalaries = fixedSalaries;
        this.staff = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getStaff() {
        return staff;
    }

    public void setStaff(List<Employee> staff) {
        this.staff = staff;
    }

    public Map<Class, Integer> getFixedPartSalaries() {
        return fixedPartSalaries;
    }

    public void setFixedPartSalaries(Map<Class, Integer> fixedPartSalaries) {
        this.fixedPartSalaries = fixedPartSalaries;
    }

    public double getIncome() {
        double income = 0;
        for (Employee emp : this.staff) {
            income += emp.getBonusBase();
        }
        return income;
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
        if (count < 1 || count > list.size()) {
            count = list.size();
        }
        list.sort(Comparator.comparing(Employee::getMonthSalary));
        list = list.subList(0, count);
        return list;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        List<Employee> list = new ArrayList<>(this.staff);
        if (count < 1 || count > list.size()) {
            count = list.size();
        }
        list.sort(Comparator.comparing(Employee::getMonthSalary).reversed());
        list = list.subList(0, count);
        return list;
    }
}