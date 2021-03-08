import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RedisStorageTest {

    private RedisStorage redis;

    @Before
    public void setUp() {
        redis = new RedisStorage();
        redis.init();
    }

    @Test
    public void testSize() {
        long l = redis.getQueueSize();
        assertEquals(20, l);
    }

    @Test
    public void testPopAndPush() {
        String expected = redis.popQueueItemById(0);
        redis.pushQueueItem(expected);
        for (int i = 1; i < redis.getQueueSize(); i++) {
            redis.pushQueueItem(redis.popQueueItemById(0));
        }
        assertEquals(expected, redis.popQueueItemById(0));
    }
}