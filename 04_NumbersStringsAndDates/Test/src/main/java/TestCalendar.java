import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TestCalendar {

    private static final int DAY = 19;
    private static final int MONTH = 4;
    private static final int YEAR = 1971;

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(YEAR, MONTH , DAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - E", Locale.ENGLISH);
        for (int i = 0; i < 50; i++) {
            System.out.println(i + " - " + dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.YEAR, 1);
        }
    }
}