import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankData {

    private static String dataFile = "files/movementList.csv";
    private static double out = 0, in = 0;
    private static Map<String, Double> shops = new HashMap<>();

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));
            lines.remove(0);
            double currentOut;
            int len;
            for (String line : lines) {
                String[] fragments = line.split(",");
                len = fragments.length;
                if (len != 8) {
                    if (len == 9) {
                        fragments[7] = fragments[7].substring(1) + "."
                                + fragments[8].substring(0, fragments[8].length() - 2);
                    } else {
                        System.out.println("Wrong line: " + line);
                        continue;
                    }
                }
                currentOut = Double.parseDouble(fragments[7]);
                out += currentOut;
                in += Double.parseDouble(fragments[6]);
                if (currentOut > 0) {
                    String[] segments = fragments[5].split("    ");
                    String[] words = segments[1].split("[\\/\\\\]");
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < words.length; i++) {
                        builder.append(words[i]);
                        builder.append("\\");
                    }
                    String str = builder.toString();
                    if (shops.keySet().contains(str)) {
                        shops.put(str, shops.get(str) + currentOut);
                    } else {
                        shops.put(str, new Double(currentOut));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Сумма расходов : " + out + " руб.");
        System.out.println("Сумма доходов : " + in + " руб.");
        System.out.println("===========================================================");
        System.out.println("Суммы расходов по организациям :");
        for (String str : shops.keySet()) {
            System.out.println(str + "  = " + shops.get(str) + " руб.");
        }
    }
}