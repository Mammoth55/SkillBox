import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class getEffectiveCoresTest {

    @Test
    public void whenCoresFull() {
        int actual = Main.getEffectiveCores(151, 8);
        int expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    public void whenCoresHalf() {
        int actual = Main.getEffectiveCores(7, 8);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void whenCoresQuarter() {
        int actual = Main.getEffectiveCores(2, 8);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void whenCoresSingle() {
        int actual = Main.getEffectiveCores(1, 8);
        int expected = 1;
        assertEquals(expected, actual);
    }
}