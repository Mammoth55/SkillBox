import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import javax.sound.sampled.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaSoundRecorder {

    static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
    static final long RECORD_TIME = 10000;
    static final String ACCESS_TOKEN = "79TYUOAZQxoAAAAAAAAAAeaFnb51l_XomwXcXdeW0qBpnFm2l36Pke3nRP6gs01I";
    static File file;
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    AudioFormat format;
    TargetDataLine line;
    DbxClientV2 client;

    public JavaSoundRecorder(DbxClientV2 client) {
        this.client = client;
        format = getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        // checks if system supports the data line
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line not supported");
            System.exit(0);
        }
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        for (int i = 0; i < 3; i++) {
            JavaSoundRecorder recorder = new JavaSoundRecorder(client);
            recorder.recordAudio(RECORD_TIME);
            Thread.sleep(RECORD_TIME + 5000);
        }
    }

    public void recordAudio(long milliseconds) {
        //TODO: 20210225_212223.wav
        file = new File(formatter.format(new Date()) + ".wav");
        start();
        stop(milliseconds);
        //TODO: delete file
        eraseFile(milliseconds);
    }

    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public void start() {
        Thread thread = new Thread(() -> {
            try {
                line.open(format);
                line.start();   // start capturing
                AudioInputStream ais = new AudioInputStream(line);
                AudioSystem.write(ais, fileType, file);
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }
        });
        System.out.println("START recording...");
        thread.start();
    }

    public void eraseFile(long milliseconds) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(milliseconds + 5000);
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void stop(long milliseconds) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(milliseconds);
                System.out.println("DONE.");
                line.stop();
                line.close();
            //TODO: upload file to Dropbox
                System.out.println("Uploading file " + file.getName());
                try (InputStream in = new FileInputStream(file)) {
                    client.files().uploadBuilder("/" + file.getName()).uploadAndFinish(in);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("File " + file.getName() + " uploaded.");
        });
        thread.start();
    }
}