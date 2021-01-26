import java.util.Scanner;

/* Программа считает количество различных возможных тестовых образцов из натуральных чисел,
 *   составленных из одинаковых цифр (например 11, 222, 3333 и т.д.), ограниченных диапазоном [l, r]
 *
 * @input long l - левая граница для тестового образца
 * @input long r - правая граница для тестового образца
 * @output long count - количество найденных тестовых образцов
 * */

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long l = scanner.nextLong();
        long r = scanner.nextLong();
        String min = Long.toString(l);
        String max = Long.toString(r);
        int minDigits = min.length();
        int maxDigits = max.length();
        int firstDigit = Integer.parseInt(min.substring(0, 1));
        long count = 0;
        for (int currentDigit = firstDigit; currentDigit < 10; currentDigit++) {
            StringBuilder sample = new StringBuilder();
            for (int k = 0; k < minDigits; k++) {
                sample.append(currentDigit);
            }
            for (int j = minDigits; j <= maxDigits; j++) {
                long test = Long.parseLong(sample.toString());
                if (test >= l && test <= r) {
                    count++;
                }
                sample.append(currentDigit);
            }
        }
        System.out.println(count);
    }
}