import redis.clients.jedis.Jedis;
import java.util.Set;
import static java.lang.System.out;

public class RedisStorage {

    // очередь для вывода
    private Jedis jedis;

    // счетчик для бонусного подъема в топ очереди
    private int count = 0;

    // ограничение счетчика для срабатывания
    private final int MAX_COUNT = 10;

    private final String USERS_KEY = "MEETING";

    private final String QUEUE_KEY = "QUEUE";

    private final static String REDIS_URI = "redis://127.0.0.1:6379";

    public String popQueueItemById(long Id) {
        String str = jedis.lindex(QUEUE_KEY, Id);
        jedis.lrem(QUEUE_KEY, 1, str);
        return str;
    }

    public void pushQueueItem(String value) {
        jedis.rpush(QUEUE_KEY, value);
    }

    public void clearQueue() {
        jedis.del(QUEUE_KEY);
    }

    public long getQueueSize() {
        return jedis.llen(QUEUE_KEY);
    }

    public Set<String> getSortedSet() {
        return jedis.zrange(USERS_KEY, 0, -1);
    }

    public int incrementCount() {
        return ++count;
    }

    public void clearCount() {
        count = 0;
    }

    public int getMaxCount() {
        return MAX_COUNT;
    }

    void init() {
        try {
            out.println("Try to connect to REDIS...");
            jedis = new Jedis(REDIS_URI);
            out.println("Done !");
        } catch (Exception Exc) {
            out.println("Не удалось подключиться к Redis !");
            out.println(Exc.getMessage());
        }
        Set<String> registeredUsers = getSortedSet();
        clearQueue();
        out.println("Received users : " + registeredUsers.size());
        for (String str : registeredUsers) {
            jedis.rpush(QUEUE_KEY, str);
        }
    }
}