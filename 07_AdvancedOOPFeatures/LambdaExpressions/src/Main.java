import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    
    private static String staffFile
            = "C:\\projects\\SkillBox\\java_basics\\07_AdvancedOOPFeatures\\LambdaExpressions\\data\\staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();
        Collections.sort(staff, (e1, e2) -> {
            int rsl = e1.getSalary().compareTo(e2.getSalary());
            return rsl != 0 ? rsl : e1.getName().compareTo(e2.getName());
        });
        for (Employee emp: staff) {
            System.out.println(emp);
        }
    }
}