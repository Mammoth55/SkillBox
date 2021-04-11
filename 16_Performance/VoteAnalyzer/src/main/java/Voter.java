import java.text.SimpleDateFormat;
import java.util.Date;

public class Voter {

    private final String name;
    private final Date birthDay;

    public Voter(String name, Date birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object obj) {
        Voter voter = (Voter) obj;
        return name.equals(voter.name) && birthDay.equals(voter.birthDay);
    }

    @Override
    public int hashCode() {
        long code = name.hashCode() + birthDay.hashCode();
        if (code > Integer.MAX_VALUE) {
            code /= 2;
        }
        return (int) code;
    }

    public String toString() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        return name + " (" + dayFormat.format(birthDay) + ")";
    }

    public String getName() {
        return name;
    }

    public Date getBirthDay() {
        return birthDay;
    }
}
