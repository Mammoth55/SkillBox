import java.io.File;

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

    public static void main(String[] args) {
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
            ImageResizer resizer = new ImageResizer(files, NEWWIDTH, dstFolder, startTime);
            new Thread(resizer).start();
        }
    }
}