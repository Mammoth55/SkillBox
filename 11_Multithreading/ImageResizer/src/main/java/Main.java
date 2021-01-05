import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int NEWWIDTH = 300;
    private static int CORESTOTAL = Runtime.getRuntime().availableProcessors();

    public static int getEffectiveCores(int filesTotal, int cores) {
        if (filesTotal < cores) {
            return getEffectiveCores(filesTotal, cores / 2);
        } else {
            return cores;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String srcFolder = "D:/Camera";
        String dstFolder = "D:/Camera/icons";
        File srcDir = new File(srcFolder);
        File[] files;
        File[] filesTotal = srcDir.listFiles();
        System.out.println("In this PC CPU-cores detected : " + CORESTOTAL);
        int filesCountTotal = filesTotal.length;
        if (filesCountTotal == 0) {
            System.out.println("No pictures in folder !");
            System.exit(0);
        }
        int coresReal = getEffectiveCores(filesCountTotal, CORESTOTAL);
        int filesCountPerCore = filesCountTotal / coresReal;
        int lastFilesCount = filesCountTotal % coresReal;
        System.out.println("Pictures in folder detected : " + filesCountTotal);
        System.out.println("Threads to be created : " + coresReal);
        System.out.println("Pictures creating in progress...");
        List<Thread> threads = new ArrayList<>();
        Path dstPath = Paths.get(dstFolder);
        try {
            if (!Files.exists(dstPath)) {
                Files.createDirectories(dstPath);
            }
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < coresReal; i++) {
                if (i == coresReal - 1) {
                    files = new File[filesCountPerCore + lastFilesCount];
                    System.arraycopy(filesTotal, filesCountPerCore * i,
                            files, 0,
                            filesCountPerCore + lastFilesCount);
                } else {
                    files = new File[filesCountPerCore];
                    System.arraycopy(filesTotal, filesCountPerCore * i,
                            files, 0,
                            filesCountPerCore);
                }
                Thread thread = new Thread(new ImageResizer(files, NEWWIDTH, dstFolder, startTime));
                threads.add(thread);
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("All threads done !");
    }
}