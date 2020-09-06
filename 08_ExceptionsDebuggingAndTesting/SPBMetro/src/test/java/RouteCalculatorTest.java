import core.Line;
import core.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RouteCalculatorTest {

    List<Station> route;
    StationIndex stationIndex;
    RouteCalculator calculator;
    Line line1, line2, line3;
    Station station11, station12, station21, station22, station31, station32;

    @BeforeEach
    void setUp() {
        line1 = new Line(1, "Первая");
        line2 = new Line(2, "Вторая");
        line3 = new Line(3, "Третья");
        station11 = new Station("A", line1);
        station12 = new Station("B", line1);
        line1.addStation(station11);
        line1.addStation(station12);
        station21 = new Station("K", line2);
        station22 = new Station("L", line2);
        line2.addStation(station21);
        line2.addStation(station22);
        station31 = new Station("X", line3);
        station32 = new Station("Y", line3);
        line3.addStation(station31);
        line3.addStation(station32);
        route = new ArrayList<>();
        route.add(station11);
        route.add(station12);
        route.add(station22);
        route.add(station21);
        stationIndex = new StationIndex();
        stationIndex.addStation(station11);
        stationIndex.addStation(station12);
        stationIndex.addStation(station21);
        stationIndex.addStation(station22);
        stationIndex.addStation(station31);
        stationIndex.addStation(station32);
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addConnection(List.of(station12, station22));
        stationIndex.addConnection(List.of(station21, station31));
        calculator = new RouteCalculator(stationIndex);
    }

    @Test
    void getShortestRouteWhenOnLine() {
        List<Station> actual = calculator.getShortestRoute(station11, station12);
        List<Station> expected = List.of(station11, station12);
        assertEquals(expected, actual);
    }

    @Test
    void getShortestRouteWithOneConnection() {
        List<Station> actual = calculator.getShortestRoute(station11, station21);
        List<Station> expected = List.of(station11, station12, station22, station21);
        assertEquals(expected, actual);
    }

    @Test
    void getShortestRouteWithTwoConnection() {
        List<Station> actual = calculator.getShortestRoute(station11, station32);
        List<Station> expected = List.of(station11, station12, station22, station21, station31, station32);
        assertEquals(expected, actual);
    }

    @Test
    void calculateDurationWithOneConnection() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void calculateDurationWithTwoConnection() {
        route.add(station31);
        route.add(station32);
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 14.5;
        assertEquals(expected, actual, 0.1);
    }
}