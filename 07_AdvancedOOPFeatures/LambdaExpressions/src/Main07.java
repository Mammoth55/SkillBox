import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Main07 {
    
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

    private static void printFlight(Flight f) {
        System.out.println(f.getDate() + " / " + f.getAircraft());
    }

    private static void salarySort() {
        ArrayList<Employee> staff = loadStaffFromFile();
        Calendar startPeriod = Calendar.getInstance();
        Calendar endPeriod = Calendar.getInstance();
        startPeriod.set(2017, Calendar.JANUARY, 1);
        endPeriod.set(2017, Calendar.DECEMBER, 31);
        staff.stream()
                .filter(e -> e.getWorkStart().after(startPeriod.getTime())
                        && e.getWorkStart().before(endPeriod.getTime()))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
    }

    private static void flightSort() {
        Airport airport = Airport.getInstance();
        Calendar currentDateTime = Calendar.getInstance();
        Calendar limitDateTime = Calendar.getInstance();
        limitDateTime.add(Calendar.HOUR, 2);
        airport.getTerminals().stream().flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getType() == Flight.Type.DEPARTURE
                        && f.getDate().after(currentDateTime.getTime())
                        && f.getDate().before(limitDateTime.getTime()))
                .sorted(Comparator.comparing(Flight::getDate))
                .forEach(f -> printFlight(f));
    }

    public static void main(String[] args) {
        salarySort();
        System.out.println("-----------------------------------------------------------------");
        flightSort();
    }
}