import java.util.Scanner;

public class TestString {

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        int diamondLeft = text.indexOf("<");
        int diamondRight = text.indexOf(">");
        return text.substring(0, diamondLeft) + placeholder + text.substring(diamondRight + 1);
    }

    public static void alphabeticWithCode() {
        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.println(c + "\t" + (int) c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            System.out.println(c + "\t" + (int) c);
        }
        for (char c = 'А'; c <= 'Я'; c++) {
            System.out.println(c + "\t" + (int) c);
        }
        for (char c = 'а'; c <= 'я'; c++) {
            System.out.println(c + "\t" + (int) c);
        }
    }

    public static void salaryTotal() {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int indexFirstEnd = text.indexOf(" руб");
        int indexFirstBegin = text.substring(0, indexFirstEnd).lastIndexOf(" ") + 1;
        int indexSecondEnd = text.substring(indexFirstEnd + 4).indexOf(" руб") + indexFirstEnd + 4;
        int indexSecondBegin = text.substring(0, indexSecondEnd).lastIndexOf(" ") + 1;
        int indexLastEnd = text.lastIndexOf(" руб");
        int indexLastBegin = text.substring(0, indexLastEnd).lastIndexOf(" ") + 1;
        System.out.println("Сумма заработков = "
                + (Integer.parseInt(text.substring(indexLastBegin, indexLastEnd))
                + Integer.parseInt(text.substring(indexSecondBegin, indexSecondEnd))
                + Integer.parseInt(text.substring(indexFirstBegin, indexFirstEnd)))
                + " руб.");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите ФИО : ");
        String str = in.nextLine();
        str = str.trim();
        int space1 = str.indexOf(" ");
        System.out.println("Фамилия : " + str.substring(0, space1));
        int space2 = str.substring(space1 + 1).indexOf(" ") + space1 + 1;
        System.out.println("Имя : " + str.substring(space1 + 1, space2));
        int space3 = str.substring(space2 + 1).indexOf(" ");
        if (space3 < 0) {
            space3 = str.length();
        } else {
            space3 += space2 + 1;
        }
        System.out.println("Отчество : " + str.substring(space2 + 1, space3));
    }
}