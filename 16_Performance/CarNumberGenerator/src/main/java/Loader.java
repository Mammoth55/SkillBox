import java.util.ArrayList;
import java.util.List;

// первичный замер 56 сек/регион (среднее из трех замеров)
// после рефакторинга padNumber (String => StringBuilder) = 56 сек/регион, т.к. выигрыш без конкатенаций тут несущественный
// после рефакторинга main (String => StringBuilder) = 56 сек/регион, т.к. выигрыш без конкатенаций тут несущественный
// после рефакторинга main (FileOutputStream => PrintWriter) = 1.9 сек/регион,
//      т.к. вывод буферизирован и выгрузка буфера оптимизирована
// перекалибровка на 32 региона = 41 сек
// после рефакторинга main (Multithreading => 4 потоков + 4 файлов) = 20 сек,
//      т.к. 4 ядра CPU и распределение вычислений между ними
// после рефакторинга main (Multithreading => 8 потоков + 8 файлов) = 18 сек,
//      т.к. 4 ядра CPU и распределение вычислений между ними
// после рефакторинга main (Multithreading => 16 потоков + 16 файлов) = 22 сек,
//      т.к. 4 ядра CPU и распределение вычислений между ними
// Т.о. по итогу вероятно оптимальное кол-во потоков это 2 для каждого физического ядра CPU
//      при распределении работы для достижения макс.эффективности

public class Loader {

    static final int NUMBER_CAPACITY = 1000;
    static final int REGION_CAPACITY = 32;
    static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    static final String FILE_NAME = "src/main/resources/numbers";
    static final String FILE_EXT = ".txt";

    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();
        System.out.println("Program started...");
        int maxThread = Math.min(REGION_CAPACITY, 2 * Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        for (int count = 0; count < maxThread; count++) {
            Thread thread = new Thread(new MyThread(count * REGION_CAPACITY / maxThread,
                    REGION_CAPACITY / maxThread,
                    FILE_NAME + Integer.toString(count) + FILE_EXT));
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Program ended in " + (System.currentTimeMillis() - start) + " ms");
    }
}