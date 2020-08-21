import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

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
        staff.stream()
                .filter(e -> e.getWorkStart().after( new Date(116, 11, 31)))
                .filter(e -> e.getWorkStart().before(new Date(118, 00, 01)))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
        Airport airport = Airport.getInstance();
        Date currentDate = new Date();
        int currentHour = currentDate.getHours();
        if (currentHour >= 22) {
            currentHour -= 24;
        }
        final int cur = currentHour;
        airport.getTerminals().stream().flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getType() == Flight.Type.DEPARTURE
                        && (f.getDate().getHours() - cur) * 60
                        + f.getDate().getMinutes() - currentDate.getMinutes() <= 120
                        && (f.getDate().getHours() - cur) * 60
                        + f.getDate().getMinutes() - currentDate.getMinutes() >= 0)
                .forEach(f -> System.out.println(f + " / " + f.getAircraft()));
    }
}