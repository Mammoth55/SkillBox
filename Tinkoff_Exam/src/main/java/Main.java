import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* Программа считает максимальную сумму увеличения исходной суммы всех введенных натуральных чисел,
 * после некоторого кол-ва замен любых цифр из введенных чисел на произвольную цифру
 *
 * @input int n, k - кол-во вводимых ниже чисел для анализа и кол-во возможных замен цифр
 * @input int[] numbers - массив натуральных чисел для анализа
 * @output long sum - максимальная сумма увеличения после всех возможных замен цифр
 * */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        List<Integer> inList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            inList.add(scanner.nextInt());
        }
        List<Integer> outList = new ArrayList<>();
        for (Integer j : inList) {
            String str = Integer.toString(j);
            for (int i = 0; i < str.length(); i++) {
                outList.add((int) Math.pow(10, str.length() - i - 1) * (9 - Integer.parseInt(str.substring(i, i + 1))));
            }
        }
        long sum = 0;
        Collections.sort(outList, Collections.reverseOrder());
        int count = Math.min(k, outList.size());
        for (int i = 0; i < count; i++) {
            sum += outList.get(i);
        }
        System.out.println(sum);
    }
}