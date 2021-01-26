import java.util.Scanner;

/* Программа ищет перестановку пары членов в введенном ряде натуральных чисел,
 *   чтобы после замены получился ряд, где на четных местах (начиная с 1) стоят четные числа,
 *   а на нечетных - нечетные
 * @input int count - число членов ряда натуральных чисел
 * @input int[] row - ряд натуральных чисел, количеством count
 * @output int pos1, pos2 - 2 индекса элементов для обмена, если таковые найдутся, иначе -1 -1
 * */

public class Main3 {

    private static boolean rowIsCorrect(int[] heightList) {
        for (int i = 0; i < heightList.length; i++) {
            if ((i + 1) % 2 != heightList[i] % 2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] heightList = new int[n];
        for (int i = 0; i < n; i++) {
            heightList[i] = scanner.nextInt();
        }
        int change;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                change = heightList[i];
                heightList[i] = heightList[j];
                heightList[j] = change;
                if (rowIsCorrect(heightList)) {
                    System.out.println(++i + " " + ++j);
                    System.exit(0);
                }
                change = heightList[i];
                heightList[i] = heightList[j];
                heightList[j] = change;
            }
        }
        System.out.println("-1 -1");
    }
}