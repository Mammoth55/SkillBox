import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod> {

    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private long from;
    private long to;

    /**
     * Time period within one day
     *
     * @param from
     * @param to
     */
    public TimePeriod(long from, long to) {
        this.from = from;
        this.to = to;
        if (! dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(to))))
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day !");
    }

    public void appendTime(long visitTime) {
        if (!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(visitTime))))
            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
        if (visitTime < from) {
            from = visitTime;
        } else if (visitTime > to) {
            to = visitTime;
        }
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String from = dateFormat.format(this.from);
        String to = timeFormat.format(this.to);
        return from + "-" + to;
    }

    @Override
    public int compareTo(TimePeriod period) {
        Date current = new Date();
        Date compared = new Date();
        try {
            current = dayFormat.parse(dayFormat.format(new Date(from)));
            compared = dayFormat.parse(dayFormat.format(new Date(period.from)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return current.compareTo(compared);
    }
}