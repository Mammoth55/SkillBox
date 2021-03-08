import java.util.Random;
import static java.lang.System.out;

public class RedisMain {

    // временная задержка
    private static final int SLEEP = 1000; // 1 секунда

    private static void log(String user) {
        String log = "На главной странице показываем '" + user + "'.";
        out.println(log);
    }

    private static String getNextUser(RedisStorage redis) {
        String user;
        long index = 0;
        if (redis.incrementCount() >= redis.getMaxCount()) {
            index = new Random().nextInt((int) redis.getQueueSize());
            redis.clearCount();
        }
        user = redis.popQueueItemById(index);
        redis.pushQueueItem(user);
        return user;
    }

    public static void main(String[] args) throws InterruptedException {
        RedisStorage redis = new RedisStorage();
        redis.init();
        for (;;) {
            log(getNextUser(redis));
            Thread.sleep(SLEEP);
        }
    }
}