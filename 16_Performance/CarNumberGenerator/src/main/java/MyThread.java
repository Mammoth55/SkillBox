import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MyThread implements Runnable {

    private final int regionStart;
    private final int regionCount;
    private final String fileName;

    public MyThread(int regionStart, int regionCount, String fileName) {
        this.regionStart = regionStart;
        this.regionCount = regionCount;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " in progress...");
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (int regionCode = regionStart; regionCode < regionStart + regionCount; regionCode++) {
                if (regionCode == 0) {
                    continue;
                }
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < Loader.NUMBER_CAPACITY; number++) {
                    for (char firstLetter : Loader.LETTERS) {
                        for (char secondLetter : Loader.LETTERS) {
                            for (char thirdLetter : Loader.LETTERS) {
                                builder.append(firstLetter);
                                builder.append(padNumber(number, 3));
                                builder.append(secondLetter);
                                builder.append(thirdLetter);
                                builder.append(padNumber(regionCode, 2));
                                builder.append('\n');
                            }
                        }
                    }
                }
                writer.write(builder.toString());
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder padNumber(int number, int numberLength) {
        StringBuilder builder = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - builder.length();
        StringBuilder zeros = new StringBuilder(3);
        if (padSize > 0) {
            zeros.append("0".repeat(padSize));
        }
        zeros.append(builder);
        return zeros;
    }
}