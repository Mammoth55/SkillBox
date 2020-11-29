import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import javax.sound.sampled.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaSoundRecorder {
    static final long RECORD_TIME = 10000;
    static final String ACCESS_TOKEN = "sl.Al4CJ0757jDOMUbUgoykxHFV8TI2jDTf1F14x6zAbYruMZrjU_F3x0SQVwZ6eFBtTzusXwu2tLYp7Xfy7JwISp1EN57yXKbsRotVQpJCetPMh9OLyBupyMgER5UVK_aRTA3nMLc";
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

    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return format;
    }

    public void recordAudio(long milliseconds) {

        //TODO: 20201120_201454.wav
        file = new File(getNewFileName() + ".wav");
        start();
        stop(milliseconds);
        eraseFile(milliseconds);
    }

    /**
     * Captures the sound and record into a WAV file
     */
    public void start() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    line.open(format);
                    line.start();   // start capturing
                    AudioInputStream ais = new AudioInputStream(line);
                    AudioSystem.write(ais, fileType, file);
                } catch (Exception ioe) {
                    ioe.printStackTrace();
                }
            }
        };
        System.out.println("START recording...");
        thread.start();
    }

    public void eraseFile(long milliseconds) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(milliseconds + 5000);
                    file.delete();
                } catch (Exception ioe) {
                    ioe.printStackTrace();
                }
            }
        };
        thread.start();
    }

    /**
     * Closes the target data line to finish capturing and recording
     */
    public void stop(long milliseconds) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(milliseconds);
                    System.out.println("DONE.");
                    line.stop();
                    line.close();

                //TODO: upload file to Dropbox
                //TODO: delete file
                    try (InputStream in = new FileInputStream(file)) {
                        client.files().uploadBuilder("/" + file.getName()).uploadAndFinish(in);
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public static String getNewFileName() {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMdd_HHmmss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static void main(String[] args) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 myClient = new DbxClientV2(config, ACCESS_TOKEN);
        JavaSoundRecorder recorder = new JavaSoundRecorder(myClient);
        recorder.recordAudio(RECORD_TIME);
    }
}