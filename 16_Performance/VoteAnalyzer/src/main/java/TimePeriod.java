import java.text.SimpleDateFormat;

public class TimePeriod implements Comparable<TimePeriod> {

    private static final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
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
        if (from / MILLISECONDS_IN_DAY != to / MILLISECONDS_IN_DAY)
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day !");
    }

    public void appendTime(long visitTime) {
        if (this.from / MILLISECONDS_IN_DAY != visitTime / MILLISECONDS_IN_DAY)
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
        return dateFormat.format(this.from) + "-" + timeFormat.format(this.to);
    }

    @Override
    public int compareTo(TimePeriod period) {
        return (int) (this.from / MILLISECONDS_IN_DAY - period.from / MILLISECONDS_IN_DAY);
    }
}