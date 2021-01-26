import java.util.Scanner;

/* Программа ищет замену одного члена в введенном ряде натуральных чисел,
 *   чтобы после замены получился ряд, где на i-ом месте находится число не равное i,
 *   но равное одному из остальных индексов ряда
 * @input int count - число членов ряда натуральных чисел
 * @input int[] row - ряд натуральных чисел, количеством count
 * @output int position - индекс элемента для замены, int new - новый элемент, если таковые найдутся, иначе -1 -1
 * */

public class Main4 {

    private static boolean rowIsCorrect(int[] targetList) {
        int sum = 0;
        for (int i = 0; i < targetList.length; i++) {
            sum += targetList[i];
            if (targetList[i] == i + 1) {
                return false;
            }
        }
        if (sum != (targetList.length + 1) * targetList.length / 2) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] targetList = new int[n];
        for (int i = 0; i < n; i++) {
            targetList[i] = scanner.nextInt();
        }
        int save;
        for (int i = 0; i < n; i++) {
            save = targetList[i];
            for (int j = 0; j < n; j++) {
                targetList[i] = j + 1;
                if (rowIsCorrect(targetList)) {
                    System.out.println(++i + " " + ++j);
                    System.exit(0);
                }
            }
            targetList[i] = save;
        }
        System.out.println("-1 -1");
    }
}