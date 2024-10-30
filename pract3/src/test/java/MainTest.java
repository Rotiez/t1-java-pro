import edu.t1.CustomThreadPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void shouldThrowExceptionOnCreate(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CustomThreadPool(0);
        });
    }

    @Test
    public void shouldThrowNPEOnExecute(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            CustomThreadPool threadPool = new CustomThreadPool(1);
            threadPool.execute(null);
        });
    }

    @Test
    public void shouldThrowExceptionOnExecuteWhenShutdown(){
        Assertions.assertThrows(IllegalStateException.class, () -> {
            CustomThreadPool pool = new CustomThreadPool(1);
            pool.shutdown();
            pool.execute(() -> System.out.println("Test"));
        });
    }
}
