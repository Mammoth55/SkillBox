import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayListSystem {

    private static Set<String> eMail = new TreeSet<>();

    public static boolean checkEmail(String str) {
        Pattern pattern = Pattern.compile("\\b(.+)@(.+)\\b");
        Matcher matcher = pattern.matcher(str);
        boolean status = false;
        if (matcher.find()) {
            status = true;
        }
        return status;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean dontExit = true;
        while (dontExit) {
            System.out.print("Введите команду (LIST/ADD/EXIT) : ");
            List<String> input = new ArrayList<>();
            for (String str : in.nextLine().trim().split("\\s+", 2)) {
                input.add(str);
            }
            switch (input.get(0)) {
                case "LIST":
                    System.out.println("EMAIL LIST :");
                    int index = 0;
                    for (String s : eMail) {
                        System.out.println(index++ + "\t" + s);
                    }
                    break;
                case "ADD":
                    if (input.size() == 1 || !checkEmail(input.get(1))) {
                        System.out.println("Wrong ADD format !");
                    } else {
                        eMail.add(input.get(1));
                        System.out.println("Done.");
                    }
                    break;
                case "EXIT":
                    dontExit = false;
                    break;
                default:
                    System.out.println("Неверная команда !");
                    break;
            }
        }
    }
}