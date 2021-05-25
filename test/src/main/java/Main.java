import java.time.LocalDate;
import java.util.List;

public class Main {

    public static int getNeededGoodsQuantityForTargetDate(int stockBalance, int deliveryTime, int rateStock,
                                                          List<Integer> salesForecast) {
        int currentMonthCorrection, currentDaysToCorrect, quantity = 0;
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();
        LocalDate deliveryDate = currentDate.plusDays(deliveryTime);
        int deliveryMonth = deliveryDate.getMonthValue() + (deliveryDate.getYear() - currentDate.getYear()) * 12;
        int deliveryDay = deliveryDate.getDayOfMonth();
        LocalDate lastDate = deliveryDate.plusDays(rateStock);
        int lastMonth = lastDate.getMonthValue() + (lastDate.getYear() - deliveryDate.getYear()) * 12;
        int lastDay = lastDate.getDayOfMonth();

        for (int i = deliveryMonth - currentMonth; i <= (lastMonth - currentMonth); i++) {
            if (i >= salesForecast.size()) {
                System.out.println("Ошибка, диапазон выходит за пределы !");
                break;
            }
            quantity += salesForecast.get(i);
            if (i == deliveryMonth - currentMonth) {
                currentMonthCorrection = (int) (salesForecast.get(i) * deliveryDay / (double) getCurrentMonthSize(deliveryDate));
                quantity -= currentMonthCorrection;
                System.out.println("Прогноз продаж слева = -" + currentMonthCorrection);
            }
            if (i == lastMonth - currentMonth) {
                currentMonthCorrection = (int) (salesForecast.get(i) * (1 - lastDay / (double) getCurrentMonthSize(lastDate)));
                quantity -= currentMonthCorrection;
                System.out.println("Прогноз продаж справа = -" + currentMonthCorrection);
            }
            System.out.println("Накопление к заказу = " + quantity);
        }
        for (int i = 0; i <= (deliveryMonth - currentMonth); i++) {
            if (i == 0) {
                currentDaysToCorrect = currentDay + deliveryTime > getCurrentMonthSize(currentDate) ?
                        getCurrentMonthSize(currentDate) - currentDay : deliveryTime;
                currentMonthCorrection = (int) (salesForecast.get(i) * currentDaysToCorrect / (double) getCurrentMonthSize(currentDate));
                stockBalance -= currentMonthCorrection;
                System.out.println("Продажи остатка слева = -" + currentMonthCorrection);

            }
            if (i == deliveryMonth - currentMonth) {
                currentMonthCorrection = (int) (salesForecast.get(i) * deliveryDay / (double) getCurrentMonthSize(deliveryDate));
                stockBalance -= currentMonthCorrection;
                System.out.println("Продажи остатка справа = -" + currentMonthCorrection);
            }
            System.out.println("Остаток заказу = " + stockBalance);
        }
        return quantity - stockBalance;
    }

    public static int getCurrentMonthSize(LocalDate date) {
        return date.getMonth().length(date.isLeapYear());
    }

    public static void main(String[] args) {
        System.out.println("Заказать = " + getNeededGoodsQuantityForTargetDate(40, 8, 80,
                List.of(54, 60, 80, 120, 90, 85, 65, 55, 40, 35)));
    }
}