import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class FastCollinearPointsTest {
    @Test
    public void throwsNPEIfProvidePointsArrayIsNull() throws Exception {
        assertThatThrownBy(() ->
            new FastCollinearPoints(null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void throwsNEPIfAnElementInProvidedPointsArrayIsNull() {
        assertThatThrownBy(() ->
            new FastCollinearPoints(new Point[]{ null, null })
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void throwsIAEIfProvidedPointsArrayHasADuplicate() {
        assertThatThrownBy(() -> {
            Point duplicate = new Point(5, 5);
            new FastCollinearPoints(new Point[]{ duplicate, duplicate });
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void returnsLineSegmentWhen4PointsInALine() {
        Point[] points = linearPoints(4);

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        LineSegment[] result = collinear.segments();

        assertThat(result).hasSize(1);
        assertThat(result[0].p()).isEqualByComparingTo(points[0]);
        assertThat(result[0].q()).isEqualByComparingTo(points[3]);
    }

    @Test
    public void doesNotReturnLineSegmentsForScatteredPoints() {
        Point[] points = scatteredPoints();

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        LineSegment[] result = collinear.segments();

        assertThat(result).hasSize(0);
    }

    @Test
    public void returnsLineSegmentWhenLinearPointsAreMergedWithScatteredPoints() {
        Point[] points = mergePoints(linearPoints(4), scatteredPoints());

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        LineSegment[] result = collinear.segments();

        assertThat(result).hasSize(1);
        assertThat(result[0].p()).isEqualByComparingTo(new Point(0, 0));
        assertThat(result[0].q()).isEqualByComparingTo(new Point(3, 3));
    }

    private Point[] linearPoints(int number) {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            points.add(new Point(i, i));
        }

        return points.toArray(new Point[]{});
    }

    private Point[] scatteredPoints() {
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(2, 5));
        points.add(new Point(2, 10));
        points.add(new Point(4, 30));
        points.add(new Point(40, 6));

        return points.toArray(new Point[]{});
    }

    private Point[] mergePoints(Point[] first, Point[] second) {
        Point[] merged = new Point[first.length + second.length];

        int firstIdx = 0;
        int secondIdx = 0;
        int current = 0;

        while (firstIdx < first.length || secondIdx < second.length) {
            if (current % 2 == 0) {
                merged[current] = first[firstIdx];
                firstIdx++;
            } else {
                merged[current] = second[secondIdx];
                secondIdx++;
            }
            current++;
        }
        return merged;
    }
}
