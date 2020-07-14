import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositAccount extends Account {

    public DepositAccount(String requisite, double balance) {
        super(requisite, balance);
    }

    @Override
    public boolean decrementBalance(double amount) {
        boolean rsl = false;
        Calendar calendarToday = new GregorianCalendar();
        calendarToday.add(Calendar.MONTH, -1);
        if (this.addDate.after(calendarToday) && balance >= amount) {
            balance -= amount;
            rsl = true;
        }
        return rsl;
    }
}