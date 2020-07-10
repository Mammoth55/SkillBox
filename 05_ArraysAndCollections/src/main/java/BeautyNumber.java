import java.util.*;

public class BeautyNumber {

    private static String numberCreate() {
        char digit1 = (char) (Math.random() * 10 + (int) '0');
        char digit2 = (char) (Math.random() * 10 + (int) '0');
        char digit3 = (char) (Math.random() * 10 + (int) '0');
        char symb1 = (char) (Math.random() * 26 + (int) 'A');
        char symb2 = (char) (Math.random() * 26 + (int) 'A');
        char symb3 = (char) (Math.random() * 26 + (int) 'A');
        String reg = String.valueOf((int) (Math.random() * 199 + 1));
        reg = reg.length() == 1 ? "0" + reg : reg;
        StringBuilder builder = new StringBuilder();
        builder.append(symb1).append(digit1).append(digit2).append(digit3).append(symb2).append(symb3).append(reg);
        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер вида 'W456RD199' : ");
        String input = in.nextLine().trim();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2100000; i++) {
            list.add(numberCreate());
        }
        long timeBegin = System.nanoTime();
        boolean st = list.contains(input);
        long timeElapsed = (System.nanoTime() - timeBegin) / 1000;
        System.out.print("Поиск перебором : номер ");
        System.out.print(st ? "найден, " : "не найден, ");
        System.out.println("поиск занял : " + timeElapsed + " мс.");
        Collections.sort(list);
        timeBegin = System.nanoTime();
        int index = Collections.binarySearch (list, input);
        timeElapsed = (System.nanoTime() - timeBegin) / 1000;
        System.out.print("Бинарный поиск : номер ");
        System.out.print(index >= 0 ? "найден, " : "не найден, ");
        System.out.println("поиск занял : " + timeElapsed + " мс.");
        Set<String> list2 = new HashSet<>();
        for (int i = 0; i < 2100000; i++) {
            list2.add(numberCreate());
        }
        timeBegin = System.nanoTime();
        st = list2.contains(input);
        timeElapsed = (System.nanoTime() - timeBegin) / 1000;
        System.out.print("Поиск в HashSet : номер ");
        System.out.print(st ? "найден, " : "не найден, ");
        System.out.println("поиск занял : " + timeElapsed + " мс.");
        Set<String> list3 = new TreeSet<>();
        for (int i = 0; i < 2100000; i++) {
            list3.add(numberCreate());
        }
        timeBegin = System.nanoTime();
        st = list3.contains(input);
        timeElapsed = (System.nanoTime() - timeBegin) / 1000;
        System.out.print("Поиск в TreeSet : номер ");
        System.out.print(st ? "найден, " : "не найден, ");
        System.out.println("поиск занял : " + timeElapsed + " мс.");
    }
}