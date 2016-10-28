import org.omg.PortableServer.POA;

import java.util.ArrayList;
import java.util.Comparator;

public class FastCollinearPoints {
    private static final int COLLINEAR_THRESHOLD = 4;
    private Point[] points;
    private LineSegment[] segments;


    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("points array was null");
        }

        verifyPointsArray(points);
        this.points = points;
    }

    private void verifyPointsArray(Point[] points) {
        for (int outter = 0; outter < (points.length - 1); outter++) {
            Point current = points[outter];
            if (current == null) {
                throw new NullPointerException("Points array contained a null");
            }

            for (int i = outter + 1; i < points.length; i++) {
                Point other = points[i];
                if (current.compareTo(other) == 0) {
                    throw new IllegalArgumentException("Duplicate point found: " + other);
                }
            }
        }
    }

    public LineSegment[] segments() {
        if (this.segments != null) {
            return this.segments;
        }

        ArrayList<LineSegment> segments = new ArrayList<>();
        for (int currentIdx = 0; currentIdx < this.points.length; currentIdx++) {
            Point current = this.points[currentIdx];
            Comparator<Point> originComparator = current.slopeOrder();

            Point[] other = splicePointsAfter(currentIdx);
            if (other.length < COLLINEAR_THRESHOLD - 1) {
                break;
            }

            double currentSlope = 0.0d;
            int numberOfMatches = 1;
            Point end;

            mergeSort(other, originComparator);
            for (int i = 0; i < other.length; i++) {
                Point point = other[i];
                if (point.compareTo(current) == 0) continue;

                double newSlope = point.slopeTo(current);
                if (newSlope != currentSlope) {
                    currentSlope = newSlope;
                    numberOfMatches = 1;
                    end = null;
                } else {
                    end = point;
                    numberOfMatches++;
                }

                if (numberOfMatches >= (COLLINEAR_THRESHOLD - 1)) {
                    segments.add(new LineSegment(current, end));
                }
            }
        }

        this.segments = segments.toArray(new LineSegment[] {});
        return this.segments;
    }

    private Point[] splicePointsAfter(int current) {
        int splicedLength = this.points.length - (current + 1);
        Point[] copy = new Point[splicedLength];

        System.arraycopy(
            this.points,
            current + 1,
            copy,
            0,
            splicedLength
        );

        return copy;
    }

    public int numberOfSegments() {
        return this.segments.length;
    }


    private void mergeSort(Point[] array, Comparator<Point> comparator) {
        Point[] buffer = new Point[array.length];
        mergeSort(array, buffer, 0, array.length - 1, comparator);
    }

    private void mergeSort(Point[] array, Point[] buffer, int start, int end, Comparator<Point> comparator) {
        if (start == end) return;

        int mid = (end + start) / 2;

        mergeSort(array, buffer, start, mid, comparator);
        mergeSort(array, buffer, mid + 1, end, comparator);

        merge(array, buffer, start, mid, end, comparator);

    }

    private void merge(Point[] toBeMerged, Point[] buffer, int start, int mid, int end, Comparator<Point> comparator) {
        for (int i = start; i <= end; i++) {
            buffer[i] = toBeMerged[i];
        }

        int left = start;
        int right = mid + 1;
        int current = left;

        while (left <= mid && right <= end) {
            if (comparator.compare(toBeMerged[left], toBeMerged[right]) == -1) {
                toBeMerged[current] = buffer[right];
                right++;
            } else {
                toBeMerged[current] = buffer[left];
                left++;
            }
            current++;
        }

        for (int i = left; i <= mid; i++) {
            toBeMerged[current] = buffer[i];
            current++;
        }
    }
}
