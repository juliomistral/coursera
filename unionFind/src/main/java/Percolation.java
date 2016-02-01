import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private static final int CLOSED_NODE = 1;
    private static final int OPEN_NODE = 0;

    private int topRoot = 0;
    private int bottomRoot = 0;
    private int gridSize = 0;
    private WeightedQuickUnionUF unionFinder;
    private int[] nodeStatus;


    public Percolation(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Grid size was <= 0");
        }

        this.gridSize = size;
        int flattenedSize = size * size;
        int arraySize = flattenedSize + 2; // 1rst and last element are top and bottom roots

        this.bottomRoot = arraySize - 1;
        this.unionFinder = new WeightedQuickUnionUF(arraySize);

        nodeStatus = new int[arraySize];

        for (int idx = 1; idx < arraySize; idx++) {
            nodeStatus[idx] = CLOSED_NODE;
            if (idx <= this.gridSize) {
                unionFinder.union(this.topRoot, idx);
            }
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int row, int col) {
        assertValidCoordinates(row, col);

        // Open the node
        int nodeIdx = toIndex(row, col);
        this.nodeStatus[nodeIdx] = OPEN_NODE;

        // Link to any open neighbors
        int[] neighborIndexes = calculateNeighborIndexes(row, col);
        for (int idx = 0; idx < neighborIndexes.length; idx++) {
            if (neighborIndexes[idx] != 0) {
                linkNeighbors(nodeIdx, neighborIndexes[idx]);
            }
        }

        // If a bottom row node, link to bottom root
        int minLastRowIndex = (gridSize * gridSize) - this.gridSize;
        if (nodeIdx > minLastRowIndex) {
            unionFinder.union(this.bottomRoot, nodeIdx);
        }
    }

    // clockwise: T, R, B, L
    private int[] calculateNeighborIndexes(int row, int col) {
        int topIdx = 0;
        int bottomIdx = 0;
        int leftIdx = 0;
        int rightIdx = 0;

        // Top neighbor
        if (row - 1 > 0) {
             topIdx = toIndex(row - 1, col);
        }
        // bottom neighbor
        if (row + 1 <= this.gridSize) {
            bottomIdx = toIndex(row + 1, col);
        }
        // left neighbor
        if (col - 1 > 0) {
            leftIdx = toIndex(row, col - 1);
        }
        // right neighbor
        if (col + 1 <= this.gridSize) {
            rightIdx = toIndex(row, col + 1);
        }

        return new int[] { topIdx, rightIdx, bottomIdx, leftIdx };
    }

    private void linkNeighbors(int nodeIdx, int neighborIdx) {
        if (this.nodeStatus[neighborIdx] == OPEN_NODE) {
            unionFinder.union(neighborIdx, nodeIdx);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int row, int col) {
        assertValidCoordinates(row, col);
        return isOpen(toIndex(row, col));
    }

    private boolean isOpen(int nodeIdx) {
        return this.nodeStatus[nodeIdx] == OPEN_NODE;
    }

    // is site (row i, column j) full?
    public boolean isFull(int row, int col) {
        assertValidCoordinates(row, col);
        int nodeIdx = toIndex(row, col);

        // If I'm not open, I'm no fullsies!
        if (!isOpen(nodeIdx)) {
            return false;
        }

        // If I'm connected to the top, I'm full
        if (unionFinder.connected(topRoot, nodeIdx)) {
            return true;
        }

        return false;
    }

    public boolean percolates() {
        return unionFinder.connected(topRoot, bottomRoot);
    }

    private void assertValidCoordinates(int row, int column) {
        if (row <= 0 || column <=0) {
            throw new IndexOutOfBoundsException("Row/Column was <= 1");
        }

        if (row > this.gridSize || column > this.gridSize) {
            throw new IndexOutOfBoundsException("Row/Coumn was > grid size: " + gridSize);
        }
    }

    private int toIndex(int row, int col) {
        int rowFactor = (row - 1) * this.gridSize;
        return rowFactor + col;
    }

    private int[] toCoordinates(int nodeIdx) {
        int row = nodeIdx / this.gridSize + 1;
        int col = nodeIdx % this.gridSize;
        col = (col == 0) ? this.gridSize : col;

        return new int[] { row, col };
    }
}
