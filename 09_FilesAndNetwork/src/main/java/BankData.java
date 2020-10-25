import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BankData {

    private static String dataFile = "files/movementList.org";
    private static List<Transaction> transactions = new ArrayList<>();
    private static HashMap<String, Double> shops = new HashMap<>();

    public static void main(String[] args) {
        try {
            Path dataPath = Paths.get(dataFile);
            if (dataFile.endsWith(".csv")) {
                transactions = parseCSV(dataPath);
            } else if (dataFile.endsWith(".org")) {
                transactions = parseORG(dataPath);
            } else {
                throw new RuntimeException("Unknown file type : " + dataFile);
            }
            printSumOfIncome(transactions);
            printSumOfExpenses(transactions);
            printSummaryByGroups(transactions);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printSumOfIncome(List<Transaction> transactions) {
        double sum = transactions.stream().mapToDouble(e -> e.getIncome()).sum();
        System.out.println("Сумма доходов : " + sum + " руб.");
    }

    private static void printSumOfExpenses(List<Transaction> transactions) {
        double sum = transactions.stream().mapToDouble(e -> e.getExpense()).sum();
        System.out.println("Сумма расходов : " + sum + " руб.");
    }

    private static void printSummaryByGroups(List<Transaction> transactions) {
        String shop;
        double expense;
        for (Transaction transaction : transactions) {
            shop = transaction.getDescription();
            expense = transaction.getExpense();
            if (expense > 0) {
                if (shops.containsKey(shop)) {
                    shops.put(shop, shops.get(shop) + expense);
                } else {
                    shops.put(shop, expense);
                }
            }
        }
        System.out.println("===========================================================");
        System.out.println("Суммы расходов по организациям :");
        for (String shOP : shops.keySet()) {
            System.out.println(shOP + "  = " + shops.get(shOP) + " руб.");
        }
    }

    private static List<Transaction> parseCSV(Path csvFile) throws IOException {
        final int COLUMNS_COUNT = 8;
        List<Transaction> transactions = new ArrayList<>();
        StringBuilder builder;
        try (Scanner scanner = new Scanner(csvFile)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.replaceAll("\\D", "").equals("")) {
                    continue; // пропускаем шапку таблицы и строки без данных
                }
                line = line.replaceAll("\"(\\d+),(\\d+)\"", "$1.$2");
                double in, out;
                String[] fragments = line.split(",");
                if (fragments.length != COLUMNS_COUNT) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                out = Double.parseDouble(fragments[COLUMNS_COUNT - 1]);
                in = Double.parseDouble(fragments[COLUMNS_COUNT - 2]);
                String[] segments = fragments[COLUMNS_COUNT - 3].split("    ");
                String[] words = segments[1].split("[\\/\\\\]");
                builder = new StringBuilder();
                for (int i = 1; i < words.length; i++) {
                    builder.append(words[i]);
                    builder.append("\\");
                }
                transactions.add(new Transaction(builder.toString(), in, out));
            }
            return transactions;
        }
    }

    private static List<Transaction> parseORG(Path orgFile) throws IOException {
        final int COLUMNS_COUNT = 5;
        List<Transaction> transactions = new ArrayList<>();
        try (Scanner scanner = new Scanner(orgFile)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.replaceAll("\\D", "").equals("")) {
                    continue; // пропускаем шапку таблицы и строки без данных
                }
                line = line.replaceAll("(\\d+),(\\d+)", "$1.$2");
                double in, out;
                String[] fragments = line.trim().split("\\s*\\|\\s*");
                if (fragments.length != COLUMNS_COUNT + 1) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                out = Double.parseDouble(fragments[COLUMNS_COUNT]);
                in = Double.parseDouble(fragments[COLUMNS_COUNT - 1]);
                transactions.add(new Transaction(fragments[COLUMNS_COUNT - 3], in, out));
            }
            return transactions;
        }
    }
}