import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;


public class BruteCollinearPointsTest {
    private int MAX_POINT_VALUE = 32767;
    private Random numberGen;


    @Before
    public void setUp() throws Exception {
        numberGen = new Random();
    }

    @Test
    public void throwsNPEIfProvidePointsArrayIsNull() throws Exception {
        assertThatThrownBy(() ->
            new BruteCollinearPoints(null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void throwsNEPIfAnElementInProvidedPointsArrayIsNull() {
        assertThatThrownBy(() ->
                new BruteCollinearPoints(new Point[]{ null, null })
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void throwsIAEIfProvidedPointsArrayHasADuplicate() {
        assertThatThrownBy(() -> {
            Point duplicate = new Point(5, 5);
            new BruteCollinearPoints(new Point[]{ duplicate, duplicate });
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void returnsLineSegmentWhen4PointsInALine() {
        Point[] points = linearPoints(4);

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        LineSegment[] result = collinear.segments();

        assertThat(result).hasSize(1);
        assertThat(result[0].p()).isEqualByComparingTo(points[0]);
        assertThat(result[0].q()).isEqualByComparingTo(points[3]);
    }

    @Test
    public void doesNotReturnLineSegmentsForScatteredPoints() {
        Point[] points = scatteredPoints(4);

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        LineSegment[] result = collinear.segments();

        assertThat(result).hasSize(0);
    }

    private Point[] linearPoints(int number) {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            points.add(new Point(i, i));
        }

        return points.toArray(new Point[]{});
    }

    private Point[] scatteredPoints(int number) {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            points.add(new Point(randomInt(), randomInt()));
        }

        return points.toArray(new Point[]{});
    }

    private int randomInt() {
        return numberGen.nextInt(MAX_POINT_VALUE);
    }
}
