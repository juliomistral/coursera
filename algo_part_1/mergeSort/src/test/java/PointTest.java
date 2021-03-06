import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class PointTest {
    @Test
    public void pointWithTheLargerYValueIsGreater() throws Exception {
        Point larger = new Point(0, 10);
        Point smaller = new Point(0, 1);

        assertThat(larger.compareTo(smaller)).isEqualTo(1);
        assertThat(smaller.compareTo(larger)).isEqualTo(-1);
    }

    @Test
    public void pointWithEqualYButLargerXIsGreater() {
        Point larger = new Point(10, 0);
        Point smaller = new Point(0, 0);

        assertThat(larger.compareTo(smaller)).isEqualTo(1);
        assertThat(smaller.compareTo(larger)).isEqualTo(-1);
    }

    @Test
    public void pointsWithEqualYAndEqualXAreEqual() {
        Point larger = new Point(10, 10);
        Point smaller = new Point(10, 10);

        assertThat(larger.compareTo(smaller)).isEqualTo(0);
    }

    @Test
    public void slopeOfHorizontalLineIsZero() {
        Point first = new Point(0, 10);
        Point second = new Point(10, 10);

        assertThat(first.slopeTo(second)).isEqualTo(0);
    }

    @Test
    public void slopeOfVerticalLineIsPositiveInfinity() {
        Point first = new Point(0, 10);
        Point second = new Point(0, 20);

        assertThat(first.slopeTo(second)).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    public void slopeOfEqualPointsIsNegativeInfinity() {
        Point first = new Point(0, 10);
        Point second = new Point(0, 10);

        assertThat(first.slopeTo(second)).isEqualTo(Double.NEGATIVE_INFINITY);
    }
}
