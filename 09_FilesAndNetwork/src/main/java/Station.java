import java.util.Objects;

public class Station {

    private String lineNumber;
    private String name;

    public Station(String lineNumber, String name) {
        this.lineNumber = lineNumber;
        this.name = name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\n"
                + "\"line\": \"" + lineNumber + "\",\n"
                + "\"station\": \"" + name + "\",\n"
                + "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return Objects.equals(getLineNumber(), station.getLineNumber()) &&
                Objects.equals(getName(), station.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLineNumber(), getName());
    }
}