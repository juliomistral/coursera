import java.util.ArrayList;


public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] segments;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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

    // the line segments
    public LineSegment[] segments() {
        if (this.segments == null) {
            ArrayList<LineSegment> results = new ArrayList<>();

            for (int i = 0; i < (this.points.length - 3); i++) {
                for (int j = i + 1; j < (this.points.length - 2); j++) {
                    for (int k = j + 1; k < (this.points.length - 1); k++) {
                        for (int l = k + 1; l < this.points.length; l++) {
                            Point first  = this.points[i];
                            double slopeWithSecond = first.slopeTo(this.points[j]);
                            double slopeWithThird = first.slopeTo(this.points[k]);
                            double slopeWithForth = first.slopeTo(this.points[l]);

                            if (slopeWithSecond == slopeWithThird && slopeWithSecond == slopeWithForth) {
                                results.add(new LineSegment(first, this.points[l]));
                            }
                        }
                    }
                }
            }

            this.segments = results.toArray(new LineSegment[] {});
        }

        return this.segments;
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segments.length;
    }
}
