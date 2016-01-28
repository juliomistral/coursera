import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private static final int CLOSED_NODE = 1;
    private static final int OPEN_NODE = 0;

    private int topRoot = 0;
    private int bottomRoot = 0;
    private int gridSize = 0;
    private int flattenedSize = 0;
    private WeightedQuickUnionUF unionFinder;
    private int[] nodeStatus;


    public Percolation(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Grid size was <= 0");
        }

        this.gridSize = size;
        this.flattenedSize = size ^ 2;
        this.bottomRoot = flattenedSize;

        int arraySize = flattenedSize + 2; // 1rst and last element are top and bottom roots
        unionFinder = new WeightedQuickUnionUF(arraySize);
        for (int idx = 0; idx < arraySize; idx++) {
            
        }
    }

    public void open(int i, int j) {

    }          // open site (row i, column j) if it is not open already

    public boolean isOpen(int i, int j) {
        return false;
    }    // is site (row i, column j) open?

    public boolean isFull(int i, int j) {
        return false;
    }     // is site (row i, column j) full?

    public boolean percolates() {
        return false;
    }

    public void assertValidCoordinates(int row, int column) {
        if (row <= 0 || column <=0) {
            throw new IndexOutOfBoundsException("Row/Coumn was <= 1");
        }

        if (row > this.gridSize || column > this.gridSize) {
            throw new IndexOutOfBoundsException("Row/Coumn was > grid size: " + gridSize);
        }
    }

    public static void main(String[] args)  {

    }
}
