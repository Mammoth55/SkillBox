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
        if (++redis.count >= redis.MAX_COUNT) {
            index = new Random().nextInt(redis.registeredUsers.size());
            redis.count = 0;
        }
        user = redis.jedis.lindex(redis.QUEUE_KEY, index);
        redis.jedis.lrem(redis.QUEUE_KEY, 1, user);
        redis.jedis.rpush(redis.QUEUE_KEY, user);
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