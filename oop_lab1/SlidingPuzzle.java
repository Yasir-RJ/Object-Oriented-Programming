package model;

import java.util.Random;
import java.util.Arrays;

/**
 * This class represents logic and data for a sliding puzzle, a n-by-n matrix of
 * numbers, [1 .. (n*n - 1)] and one empty position.
 * <p>
 * Only positions adjacent to the empty positions can be moved. The goal is to
 * arrange the numbers in ascending order, with the empty position last. 
 */
public class SlidingPuzzle {
    /**
     * Represents valid puzzle dimensions. E.g. FIFTEEN represents a 4-by-4
     * puzzle (with one empty position).
     */
    public enum Dimension {
        THREE, EIGHT, FIFTEEN
    }

    private int noOfMoves;
    private int rows, cols;
    // NB! Internal representation as a one dimensional array makes it easier to
    // randomize a solvable starting position.
    private int[] values;

    /**
     * Constructs a new SlidingPuzzle with the specified dimension.
     *
     * @param dimension The dimensions of the board.
     */
    public SlidingPuzzle(Dimension dimension) {
        // ...
        // reallocate new size for values array
        switch (dimension){
            case THREE -> {values= new int[4];break;}
            case EIGHT -> {values= new int[9]; break;}
            case FIFTEEN -> {values= new int[16]; break;}
        }
        rows=cols=(int) Math.sqrt(values.length);
        // Make solvable SlidingPuzzle state
        shuffle();
    }

    /**
     * Constructs a new SlidingPuzzle with dimension FIFTEEN.
     */
    public SlidingPuzzle() {
        // ...
        // reallocate new size [16] for values array
        values= new int[16];
        rows=cols=4;
        // Make solvable SlidingPuzzle state
        shuffle();
    }

    /**
     * Resets the numbers in ascending order, with the empty position last.
     */
    public final void reset() {
        // ...
        noOfMoves=0;
        for (int i = 0; i < (values.length-1); i++)
            values[i]=i+1;
        values[values.length-1]=0;
    }

    /**
     * Shuffles the numbers. The new state is guaranteed to be solvable.
     */
    public void shuffle() {
        // shuffle until solvable
        // ...
        do {
            reset();
            randomize();
        } while(!isSolvable());
    }

    /**
     * Returns the number of moves since the last reset or shuffle.
     *
     * @return number of moves
     */
    public int getNoOfMoves() {
        // ...
        return noOfMoves;
    }

    /**
     * Returns whether the number can be moved or not, i.e. if the number is
     * adjacent to the empty position.
     *
     * @param number the number to move
     * @return true if the number can be moved
     */
    public boolean isLegalMove(int number) {
        // ...
        int numIndex=getPos(number); // get 1D index of the number in values array
        int emptyIndex=getPos(0); // get 1D index of zero in values array
        // convert 1D numIndex to 2D row and column
        int numRow=numIndex / rows;
        int numCol=numIndex % rows;
        // convert 1D emptyIndex to 2D row and column
        int emptyRow=emptyIndex / rows;
        int emptyCol=emptyIndex % rows;

        if((Math.abs(numCol-emptyCol)==1 && numRow==emptyRow) || (Math.abs(numRow-emptyRow)==1 && numCol==emptyCol))
            return true;
        else
            return false;
    }

    /**
     * Swaps the positions of number and empty position. In case the move is
     * illegal the state is unchanged.
     *
     * @param number the number to move
     * @return true if the number was moved
     */
    public boolean makeMove(int number) {
        // ...
        if(!isLegalMove(number)) return false;
        int numIndex=0,emptyIndex=0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == number) numIndex = i;
            if (values[i] == 0) emptyIndex = i;
        }
        values[numIndex]=0;
        values[emptyIndex]=number;
        noOfMoves++;
        return true;
    }

    /**
     * Returns whether the puzzle is solved, i.e. all numbers in ascending order
     * with the empty position last, or not.
     *
     * @return true if the puzzle is solved
     */
    public boolean isSolved() {
        // ...
        if(values[values.length - 1] != 0) return false;
        for(int i = 0; i < (values.length - 1); i++)
            if(values[i] != i+1) return false;
        return true;
    }

    /**
     * Returns a copy of the board, in the form of a 2-dim int matrix,
     * dimensions rows*columns. The empty position is represented with 0 (zero).
     *
     * @return an int[rows][columns] representation of the board.
     */
    public int[][] copyOfBoard() {
        // ...
        int [][] board= new int [rows][cols];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                board [i][j] = values[(i*rows)+j];
        return board;
    }

    /**
     * Returns a String-representation of this SlidingPuzzle.
     */
    @Override
    public String toString() {
        // ...
        // StringBuffer recommended
        String StringBuffer = new String("");
        int [][] Board = copyOfBoard();
        for(int i = 0; i < rows; i++)
            StringBuffer = StringBuffer + Arrays.toString(Board[i]) + "\n";
        StringBuffer = StringBuffer.replace(',',' ') ;
        return StringBuffer;
    }

    // the position (index) of the number in the internal 1-dimensional array
    private int getPos(int number) {
        // ...
        int pos=0;
        for(int i = 0; i < values.length; i++)
            if (values[i] == number) pos=i;
        return pos;
         }

    // randomize the numbers
    // NB! Not guaranteed to generate a solvable state
    private void randomize() {
        // Fisher-Yates shuffling recommended
        // ...
        // Ref: https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
        Random random = new Random();
        for (int i = values.length-1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }
    }

    // Check if this set of values do represent a solvable puzzle
    // Theory:
    // http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
    private boolean isSolvable() {
        int n = noOfInversions();
        if (cols % 2 == 0) { // width even
            int ePos = getPos(0);
            int eRow = ePos / cols;
            // empty position on even row, counting from bottom?
            if ((rows - eRow) % 2 == 0) {
                return n % 2 != 0;
            } else {
                return n % 2 == 0;
            }
        } else { // width odd
            return n % 2 == 0;
        }
    }

    // http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
    private int noOfInversions() {
        // count number of inversions, n
        int n = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] != 0) {
                int val = values[k];
                for (int l = k + 1; l < values.length; l++) {
                    if (values[l] != 0 && val > values[l]) {
                        n++;
                    }
                }
            }
        }
        return n;
    }
}
