import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {

    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public static String phoneNumberNormalize(String str) {
        String s = str.replaceAll("[^0-9]", "");
        int num = s.length();
        char symbol1 = s.charAt(0);
        if (num > 11 || num < 10 || (num == 11 && symbol1 != '8' && symbol1 != '7')) {
            throw new IllegalArgumentException();
        } else {
            if (num == 11) {
                s = s.substring(1);
            }
            s = "7" + s;
        }
        return s;
    }

    private static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("\\b(.+)@(.+)\\b");
        Matcher matcher = pattern.matcher(str);
        boolean status = false;
        if (matcher.find()) {
            status = true;
        }
        return status;
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException();
        }
        components[3] = phoneNumberNormalize(components[3]);
        if (components[3].length() == 0 || !isEmail(components[2])) {
            throw new IllegalArgumentException();
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}