import redis.clients.jedis.Jedis;
import java.util.Set;
import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Sorted Set'ом
    Set<String> registeredUsers;

    // очередь для вывода
    Jedis jedis;

    // счетчик для бонусного подъема в топ очереди
    int count = 0;

    // ограничение счетчика для срабатывания
    final int MAX_COUNT = 10;

    final String USERS_KEY = "MEETING";

    final String QUEUE_KEY = "QUEUE";

    private final static String REDIS_PORT = "redis://127.0.0.1:6379";

    void init() {
        try {
            out.println("Try to connect to REDIS...");
            jedis = new Jedis(REDIS_PORT);
            out.println("Done !");
        } catch (Exception Exc) {
            out.println("Не удалось подключиться к Redis !");
            out.println(Exc.getMessage());
        }
        registeredUsers = jedis.zrange(USERS_KEY, 0, -1);
        jedis.del(QUEUE_KEY);
        out.println("Received users : " + registeredUsers.size());
        for (String str : registeredUsers) {
            jedis.rpush(QUEUE_KEY, str);
        }
    }
}