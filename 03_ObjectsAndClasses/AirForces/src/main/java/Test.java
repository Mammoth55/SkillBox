import com.skillbox.airport.*;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Aircraft> listAircraft = airport.getAllAircrafts();
        for (Aircraft a : listAircraft) {
            System.out.println(a.toString());
        }
        System.out.println("=========================================================");
        System.out.println("TOTAL Aircraft in Airport = " + listAircraft.size());
    }
}
