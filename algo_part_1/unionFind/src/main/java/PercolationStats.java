import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final boolean DEBUG_LOGGING = false;
    private int gridSize;
    private int testRuns;
    private double[] thresholds;
    private double mean;
    private double stdDeviation;
    private double intervalFactor;


    public PercolationStats(int gridSize, int testRuns) {
        if (gridSize <= 0 || testRuns <= 0) {
            throw new IllegalArgumentException("gridSize and testRuns <= 0!!");
        }

        this.gridSize = gridSize;
        this.testRuns = testRuns;
        this.thresholds = new double[this.testRuns];

        log("Test Runs:  " + this.testRuns);
        log("Grid Size:  " + this.gridSize);

        executeTestRuns();
        calculateStats();
    }

    private void executeTestRuns() {
        double totalNodes = this.gridSize * this.gridSize;

        for (int runNumber = 0; runNumber < testRuns; runNumber++) {
            log("Starting test run:  " + runNumber);
            double numberOfOpenNodes = executePercolationRun();
            this.thresholds[runNumber] = numberOfOpenNodes / totalNodes;
        }
    }

    private void calculateStats() {
        this.mean = StdStats.mean(this.thresholds);
        this.stdDeviation = StdStats.stddev(this.thresholds);

        double rootOfNumberOfRuns = Math.sqrt(this.testRuns);
        double deviationFactor = 1.96 * this.stdDeviation;
        this.intervalFactor = deviationFactor / rootOfNumberOfRuns;
    }

    private double executePercolationRun() {
        Percolation percolator = new Percolation(this.gridSize);

        double count = 0;
        while (!percolator.percolates()) {
            int[] coord = generateRandomCoordinates();
            int row = coord[0];
            int col = coord[1];
            log(String.format("...generated coords:  %s , %s", row, col));

            if (!percolator.isOpen(row, col)) {
                log(String.format("......opening:  %s , %s", row, col));
                percolator.open(row, col);
                count++;
            }
        }

        log(String.format("......count = %s", count));
        return count;
    }

    private int[] generateRandomCoordinates() {
        int row = StdRandom.uniform(1, this.gridSize + 1);
        int col = StdRandom.uniform(1, this.gridSize + 1);

        return new int[] { row, col };
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stdDeviation;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean - this.intervalFactor;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean + this.intervalFactor;
    }

    private void log(String msg) {
        if (DEBUG_LOGGING) {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage:  java PercolationStats [grid size] [test runs]");
            return;
        }

        int gridSize = Integer.parseInt(args[0]);
        int testRuns = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(gridSize, testRuns);

        System.out.println(String.format("mean                    = %s", stats.mean()));
        System.out.println(String.format("stddev                  = %s", stats.stddev()));
        System.out.println(String.format("95%% confidence interval = %f, %f", stats.confidenceLo(), stats.confidenceHi()));
    }
}
