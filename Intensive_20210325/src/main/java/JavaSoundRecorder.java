import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import javax.sound.sampled.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaSoundRecorder {

    static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
    static final int RECORD_TIME = 10000;
    static final String ACCESS_TOKEN = "iU0_-ADvc2sAAAAAAAAAAQOdoZgQQGA88hl3vXuFOXfedSniUbW_fk9w2GINTMmy";
    DbxClientV2 client;
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    AudioFormat format;
    TargetDataLine line;
    DataLine.Info info;

    public JavaSoundRecorder(DbxClientV2 client) {
        this.client = client;
        format = getAudioFormat();
        info = new DataLine.Info(TargetDataLine.class, format);
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
        JavaSoundRecorder recorder = new JavaSoundRecorder(client);
        recorder.recordAudio(RECORD_TIME);
        Thread.sleep(RECORD_TIME);
    }

    public void recordAudio(int milliseconds) {
        File file = new File(formatter.format(new Date()) + ".wav");
        start(file);
        finish(file, milliseconds);
    }

    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public void start(File file) {
        Thread thread = new Thread(() -> {
            try {
                line = (TargetDataLine) AudioSystem.getLine(info);
                line.open(format);
                line.start();
                AudioInputStream ais = new AudioInputStream(line);
                AudioSystem.write(ais, fileType, file);
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }
        });
        System.out.println("START recording...");
        thread.start();
    }

    void finish(File file, int milliseconds) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(milliseconds);
                line.stop();
                line.close();
                System.out.println("Uploading file " + file.getName());
                try (InputStream in = new FileInputStream(file)) {
                    client.files().uploadBuilder("/" + file.getName()).uploadAndFinish(in);
                }
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("File " + file.getName() + " uploaded.");
            recordAudio(milliseconds);
        });
        thread.start();
    }
}