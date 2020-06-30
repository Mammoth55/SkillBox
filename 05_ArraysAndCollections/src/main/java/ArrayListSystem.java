import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.math.NumberUtils.isParsable;

public class ArrayListSystem {

    private static List<String> tasks = new ArrayList<>();

    public static void checkEmail(String str) {
        Pattern pattern = Pattern.compile("\\b(.+)@(.+)\\b");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("Email detected - " + matcher.group());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean dontExit = true;
        while (dontExit) {
            System.out.print("Введите команду (LIST/ADD/EDIT/DELETE/EXIT) : ");
            List<String> input = new ArrayList<>();
            for (String str : in.nextLine().trim().split("\\s+", 3)) {
                input.add(str);
            }
            switch (input.get(0)) {
                case "LIST":
                    System.out.println("TASK LIST :");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + "\t" + tasks.get(i));
                        }
                    break;
                case "ADD":
                    if (input.size() == 1 || (input.size() == 3 &&  isParsable(input.get(1))
                            && (Integer.parseInt(input.get(1)) < 0 || Integer.parseInt(input.get(1)) >= tasks.size()))) {
                        System.out.println("Wrong ADD format !");
                    } else {
                        if (input.size() == 3 && isParsable(input.get(1))) {
                            tasks.add(Integer.parseInt(input.get(1)), input.get(2));
                            checkEmail(input.get(2));
                        } else {
                            if (input.size() == 3) {
                                input.set(1, input.get(1) + " " + input.get(2));
                                input.remove(2);
                            }
                            tasks.add(input.get(1));
                            checkEmail(input.get(1));
                        }
                        System.out.println("Done.");
                    }
                    break;
                case "EDIT":
                    if (input.size() < 3 || !isParsable(input.get(1)) || Integer.parseInt(input.get(1)) < 0
                            || Integer.parseInt(input.get(1)) >= tasks.size()) {
                        System.out.println("Wrong EDIT format !");
                    } else {
                        tasks.set(Integer.parseInt(input.get(1)), input.get(2));
                        checkEmail(input.get(2));
                        System.out.println("Done.");
                    }
                    break;
                case "DELETE":
                    if (input.size() == 1 || input.size() == 3 || !isParsable(input.get(1))
                            || Integer.parseInt(input.get(1)) < 0 || Integer.parseInt(input.get(1)) >= tasks.size()) {
                        System.out.println("Wrong DELETE format !");
                    } else {
                        tasks.remove(Integer.parseInt(input.get(1)));
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