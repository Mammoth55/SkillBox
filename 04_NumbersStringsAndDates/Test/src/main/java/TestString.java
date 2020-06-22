import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestString {

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        return text.replaceAll("<.*?>", placeholder);
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
        int sum = 0;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }
        System.out.println(sum);
    }

    public static void separateWords() {
        String text = "America's top court has ruled that employers who fire workers for being gay or transgender are breaking the country's civil rights laws.\n"
                + "In a 6-3 decision, the Supreme Court said federal law, which prohibits discrimination based on sex, should be understood to include sexual orientation and gender identity.\n"
                + "The ruling is a major win for LGBT workers and their allies.\n"
                + "And it comes even though the court has grown more conservative.\n"
                + "Lawyers for the employers had argued that the authors of the 1964 Civil Rights Act had not intended it to apply to cases involving sexual orientation or transgender. The Trump administration sided with that argument.\n"
                + "But Judge Neil Gorsuch, who was nominated to the court by President Donald Trump, said acting against an employee on those grounds necessarily takes sex into account."
                + "An employer who fires an individual for being homosexual or transgender fires that person for traits or actions it would not have questioned in members of a different sex, he wrote. The limits of the drafters' imagination supply no reason to ignore the law's demands.\n"
                + "What does this mean?\n"
                + "Title VII of the Civil Rights Act of 1964 forbids employers from discriminating against employees on the basis of sex as well as gender, race, colour, national origin and religion.\n"
                + "Under the Obama administration, the federal Equal Employment Opportunity Commission, which enforces the anti-discrimination law, said it included gender identity and sexual orientation. But the Trump administration has moved to roll back some protections in health care and other areas.\n"
                + "While some states in the US had already explicitly extended such protections to LGBT workers, many have not.";
        Pattern pattern = Pattern.compile("\\s*(\\s|,|\"|\\?|!|;|-|\\.)\\s*");
        Matcher matcher = pattern.matcher(text);
        String[] words = pattern.split(text);
        for (String word : words) {
            System.out.println(word);
        }
    }

    public static void fioExtract() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите ФИО : ");
        String str = in.nextLine();
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я]+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("Фамилия : " + matcher.group());
        }
        if (matcher.find()) {
            System.out.println("Имя : " + matcher.group());
        }
        if (matcher.find()) {
            System.out.println("Отчество : " + matcher.group());
        }
    }

    public static String phoneNumberTransform(String str) {
        String s = str.replaceAll("[^0-9]", "");
        int num = s.length();
        char symb1 = s.charAt(0);
        if (num > 11 || num < 10 || (num == 11 && symb1 != '8' && symb1 != '7')) {
            s = "Неверный формат номера";
        } else {
            if (num == 11) {
                s = s.substring(1);
            }
            s = "7" + s;
        }
        return s;
    }

    public static void main(String[] args) {
        // alphabeticWithCode();
        // salaryTotal();
        // separateWords();
        // fioExtract();
        System.out.println(phoneNumberTransform("+7 909 123-45-67"));
        System.out.println(phoneNumberTransform("+7 (909) 1234-567"));
        System.out.println(phoneNumberTransform("8-905-1234567"));
        System.out.println(phoneNumberTransform("9-453-1234567"));
        System.out.println(phoneNumberTransform("8-905-123"));
        System.out.println(phoneNumberTransform("905-1234567"));
        System.out.println(phoneNumberTransform("8-905-12345672342"));
        //
        System.out.println(searchAndReplaceDiamonds("<1234 4561 7895>0649", "***"));
        System.out.println(searchAndReplaceDiamonds(" <1234 4561 7895> 0649, <мама> может все, это же <мама> ", "папа"));
    }
}