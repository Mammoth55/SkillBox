import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayListSystem {

    private static Map<String, String> phoneBook = new TreeMap<>();

    private static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("\\b(.+)@(.+)\\b");
        Matcher matcher = pattern.matcher(str);
        boolean status = false;
        if (matcher.find()) {
            status = true;
        }
        return status;
    }

    private static String phoneDetect(String str) {
        String s = str.replaceAll("[^0-9]", "");
        int num = s.length();
        if (num < 10 || num > 11 || (num == 11 && s.charAt(0) != '8' && s.charAt(0) != '7')) {
            s = "";
        } else {
            if (num == 11) {
                s = s.substring(1);
            }
            s = "7" + s;
        }
        return s;
    }

    private static String nameDetect(String str) {
        String s;
        Pattern pattern = Pattern.compile("\\b[a-zA-Zа-яА-Я]+");
        Matcher matcher = pattern.matcher(str);
        s = matcher.find() ? matcher.group() : "";
        return s;
    }

    private static void whenNameReceived(String inputName) {
        boolean isExist = false;
        for (String num : phoneBook.keySet()) {
            if (phoneBook.get(num).equals(inputName)) {
                printCard(num);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            String input;
            do {
                System.out.println("ENTER Phone for new User :");
                Scanner in = new Scanner(System.in);
                input = phoneDetect(in.nextLine());
            } while (input.equals(""));
            phoneBook.put(input, inputName);
        }
    }

    private static void whenPhoneReceived(String num) {
        if (phoneBook.keySet().contains(num)) {
            printCard(num);
        } else {
            String input;
            do {
                System.out.println("ENTER Name for new User :");
                Scanner in = new Scanner(System.in);
                input = nameDetect(in.nextLine());
            } while (input.equals(""));
            phoneBook.put(num, input);
        }
    }

    private static void printCard(String num) {
        System.out.println("Phone : " + num + "\t" + " Name : " + phoneBook.get(num));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isLoop = true;
        while (isLoop) {
            System.out.print("Enter command 'LIST'/'EXIT' or your NAME/NUMBER : ");
            String input = in.nextLine().trim();
            switch (input) {
                case "LIST":
                    System.out.println("PHONE LIST :");
                    for (String num : phoneBook.keySet()) {
                        String name = phoneBook.get(num);
                        System.out.println(num + " => " + name);
                    }
                    break;
                case "EXIT":
                    isLoop = false;
                    break;
                default:
                    String number = phoneDetect(input);
                    if (number.equals("")) {
                        whenNameReceived(input);
                    } else {
                        whenPhoneReceived(number);
                    }
                    System.out.println("Done.");
                    break;
            }
        }
    }
}