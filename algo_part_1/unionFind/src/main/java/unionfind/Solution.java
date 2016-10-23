// Given a number of rows and columns write a function
// that prints a matrix (an array of arrays) of numbers in a spiral pattern

// For example,
// spiral(3,4)

// prints:
// [[ 1,  2,  3, 4],
//  [10, 11, 12, 5],
//  [ 9,  8,  7, 6]]

public class Solution {
    // So something I thought of after the fact...
    // Should have created a Cursor object

    class Cursor {
        private int row, column, currentRow, currentColumn;

        Cursor(int row, int column) {
            this.row = row;
            this.column = column;

            this.currentRow = 0;
            this.currentColumn = 0;
        }

        void left() {
            if (currentColumn == 0) {
                throw new CursorOutOfBoundException();
            }
            this.currentColumn--;
        }

        void right() {
            if (currentColumn == this.column) {
                throw new CursorOutOfBoundException();
            }
            this.currentColumn++;
        }

        void up() {
            if (currentRow == 0) {
                throw new CursorOutOfBoundException();
            }
            this.currentRow--;
        }

        void down() {

        }
    }

    class CursorOutOfBoundException extends RuntimeException {}


    public static void main(String[] args) {
        spiral(3,4);
    }

    private static void spiral(int row, int col) {
        int[][] matrix = new int[row][col];
        int r = 0, c = 0; // row index, col index
        int counter = 1;

        boolean keepGoing = true;
        int rowAdjuster = +1;
        int colAdjuster = +1;
        boolean freezeCol = false;
        boolean freezeRow = true;

        while (keepGoing) {
            System.out.println(" r, c = " + r + ", " + c);
            matrix[r][c] = counter++;

            if (columnIndexOutOfUpperBounds(c, col)) {
                colAdjuster = -1;
                c--;
                freezeRow = false;
            } else if (columnIndexOutOfLowerBounds(c, 0)) {
                colAdjuster = 1;
                c++;
                freezeRow = true;
            }

            if (rowIndexOutOfUpperBounds(r, row)) {
                rowAdjuster = -1;
                r--;
                freezeCol = false;
            } else if (rowIndexOutOfLowerBounds(r, 0)) {
                rowAdjuster = 1;
                r++;
                freezeCol = true;
            }

            if (!freezeCol) c += colAdjuster;
            if (!freezeRow) r += rowAdjuster;

            if (counter > row  * col) {
                keepGoing = false;
            }
        }

        printMatrix(matrix);
    }

    private static boolean columnIndexOutOfUpperBounds(int c, int colMax) {
        return c >= colMax;
    }

    private static boolean columnIndexOutOfLowerBounds(int c, int colMin) {
        return c<= colMin;
    }

    private static boolean rowIndexOutOfUpperBounds(int r, int rowMax) {
        return r >= rowMax;
    }

    private static boolean rowIndexOutOfLowerBounds(int r, int rowMin) {
        return r <= rowMin;
    }

    public static void printMatrix(int[][] matrix) {
        // TODO:  pring matrix
    }
}