import org.apache.commons.lang3.StringUtils;
import java.util.*;

public class Main {

    static final Scanner IN = new Scanner(System.in);
    static final String MONGO_URL = "127.0.0.1";
    static final int MONGO_PORT = 27017;
    static final String DATABASE_NAME = "skill-mongo";

    public static void main(String[] args) {
        MyMongoDbStorage storage = new MyMongoDbStorage(MONGO_URL, MONGO_PORT, DATABASE_NAME);
        boolean isExit = false;
        List<String> list;
        while (!isExit) {
            list = getCommandFromConsole("Введите команду (eg HELP, EXIT) : ");
            switch (list.get(0)) {
                case ("EXIT"):
                    isExit = true;
                    break;
                case ("HELP"):
                    System.out.println("EXIT - выход из программы");
                    System.out.println("HELP - помощь");
                    System.out.println("ADD_SHOP name - добавить Магазин 'name' в БД");
                    System.out.println("ADD_PRODUCT name price - добавить товар 'name' с ценой 'price' в БД");
                    System.out.println("LOAD_PRODUCT name shop - загрузить товар 'name' в магазин 'shop' в БД");
                    System.out.println("STATISTICS");
                    printSeparateLine();
                    break;
                case ("ADD_SHOP"):
                    if (list.size() == 2 && list.get(1).length() > 0) {
                        storage.addNewShop(list.get(1));
                    } else {
                        printWrongCommand();
                    }
                    break;
                case ("ADD_PRODUCT"):
                    if (list.size() == 3
                            && list.get(1).length() > 0
                            && list.get(2).length() > 0
                            && StringUtils.isNumeric(list.get(2))) {
                        storage.addNewProduct(list.get(1), Integer.parseInt(list.get(2)));
                    } else {
                        printWrongCommand();
                    }
                    break;
                case ("LOAD_PRODUCT"):
                    if (list.size() == 3
                            && list.get(1).length() > 0
                            && list.get(2).length() > 0) {
                        storage.uploadProductToShop(list.get(1), list.get(2));
                    } else {
                        printWrongCommand();
                    }
                    break;
                case ("STATISTICS"):
                    System.out.println("-+++ STATISTICS +++-");
                    storage.printStatistics();
                    printSeparateLine();
                    break;
                default:
                    printWrongCommand();
                    break;
            }
        }
        System.out.println("Good Luck !");
    }

    static void printWrongCommand() {
        System.out.println("Wrong command, try again !");
        printSeparateLine();
    }

    static List<String> getCommandFromConsole(String message) {
        List<String> list = new ArrayList<>();
        while (list.size() == 0) {
            System.out.print(message);
            list = Arrays.asList(IN.nextLine().trim().toUpperCase().split("\\s+", 3));
        }
        return list;
    }

    static void printSeparateLine() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n");
    }
}