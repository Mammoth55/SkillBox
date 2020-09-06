package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Line implements Comparable<Line> {

    private int number;
    private String name;
    private List<Station> stations;

    public Line(int number, String name) {
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }

    @Override
    public int compareTo(Line line) {
        return Integer.compare(number, line.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Line) obj) == 0;
    }
}