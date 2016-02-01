import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PercolationStatsTest {
    public @Rule ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldFailIfSizeOfGridIsZero() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        PercolationStats stats = new PercolationStats(0, 10);
    }

    @Test
    public void shouldFailIfSizeOfGridIsLessThanZero() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        PercolationStats stats = new PercolationStats(-1, 10);
    }

    @Test
    public void shouldFailIfNumberOfRunsIsZero() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        PercolationStats stats = new PercolationStats(1, 0);
    }

    @Test
    public void shouldFailIfNumberOfRunsIsLessThanZero() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        PercolationStats stats = new PercolationStats(1, -1);
    }
}
