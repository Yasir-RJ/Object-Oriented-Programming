package kth.yasir.sudoku.model;
import java.io.Serializable;

/**
 * class to implement Data arrays for player
 * @author Yasir Riyadh 1/12/2021
 */
public class SudokuData implements Serializable {
    private int[][] InitialData;
    private int[][][] UserData;
    private SudokuUtilities.SudokuLevel level;
    /**
     * default constructor
     */
    public SudokuData() { }
    /**
     * copy constructor
     * @param level is games level (3 level with 3 versions each)
     */
    public SudokuData(SudokuUtilities.SudokuLevel level) {
        UserData= new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE][2];
        InitialData= new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        this.level = level;
    }
    /**
     * methods to get and set the data during the game playing
     */
    public int[][] getInitialData() { return InitialData.clone(); }
    public void setInitialData(int[][] InitialData) {
        this.InitialData = InitialData;
    }
    public int[][][] getData() {
        return UserData.clone();
    }
    public void setData(int[][][] UserData) {
        this.UserData = UserData;
    }
    public SudokuUtilities.SudokuLevel getLevel() {
        return level;
    }
    public void setLevel(SudokuUtilities.SudokuLevel level) {
        this.level = level;
    }
}
