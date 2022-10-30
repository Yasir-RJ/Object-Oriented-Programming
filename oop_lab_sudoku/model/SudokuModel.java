package kth.yasir.sudoku.model;
import java.io.IOException;
import java.util.Random;

/**
 * class to implement model for Sudoku game
 * @author Yasir Riyadh 1/12/2021
 */
public class SudokuModel {
    private final SudokuData sudokuData;
    private final MessageBox messageBox;
    private int keyPres;
    private boolean clear;
    private boolean needHint;

    /**
     * default constructor ... initialize data members,
     * generate random matrix for level  medium
     */
    public SudokuModel() {
        this.keyPres = -1;
        this.clear = false;
        this.needHint=false;
        sudokuData=new SudokuData(SudokuUtilities.SudokuLevel.MEDIUM); // default level
        setUserData(GenerateRandData(getDataLevel()));
        setInitialData(getUserData());
        messageBox= new MessageBox();
    }

    /**
     * generate random matrix for required level
     * the random matrix is one of three random versions
     * @param level is games level (3 levels )
     * @return random 3D matrix for the required level
     */
    public int[][][] GenerateRandData(SudokuUtilities.SudokuLevel level){
        int[][][] NewVerData3D = new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE][2];
        int[][][] OriginalData3D = SudokuUtilities.generateSudokuMatrix(level);
        Random rand = new Random();
        int randNum=rand.nextInt(3);

        for (int deep = 0; deep <2; deep ++)
            for (int row = 0; row <SudokuUtilities.GRID_SIZE; row ++)
                for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                    // randNum=0 original data without flipping (as it is)
                    if (randNum == 0) NewVerData3D[row][col][deep] = OriginalData3D[row][col][deep];
                    // randNum=1 new version of data with horizontal flipping
                    else if (randNum == 1)
                        NewVerData3D[row][col][deep] = OriginalData3D[row][SudokuUtilities.GRID_SIZE - 1 - col][deep];
                    // randNum=2 new version of data with vertical flipping
                    else NewVerData3D[row][col][deep] = OriginalData3D[SudokuUtilities.GRID_SIZE - 1 - row][col][deep];
                }
        return NewVerData3D;
    }

    /**
     * set the initial 2D data (unchanged numbers) for the game
     * @param Data3D the 3D matrix for the specified game level
     */
    public void setInitialData(int[][][] Data3D) {
        int [][] Data2D = new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        for (int row = 0; row <SudokuUtilities.GRID_SIZE; row ++)
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++)
                Data2D[row][col]=Data3D[row][col][0];
        sudokuData.setInitialData(Data2D);
    }

    /**
     * Model methods to get and set the data and flags during the game playing
     */
    public void setInitialData(int[][] Data2D) { sudokuData.setInitialData(Data2D); }
    public int[][] getInitialData() { return sudokuData.getInitialData().clone(); }
    public int[][][] getUserData() { return sudokuData.getData(); }
    public void setUserData(int[][][] Data) { sudokuData.setData(Data); }
    public void setDataLevel(SudokuUtilities.SudokuLevel level) { sudokuData.setLevel(level); }
    public SudokuUtilities.SudokuLevel getDataLevel() { return sudokuData.getLevel(); }
    public int getDataKeyPres() { return keyPres; }
    public void setDataKeyPres(int keyPres) { this.keyPres = keyPres; this.clear =false;}
    public boolean isDataClear() { return clear; }
    public void setDataClear(boolean clear) { this.clear = clear; }
    public boolean isNeedHint() { return needHint; }
    public void setNeedHint(boolean needHint) { this.needHint = needHint; }

    /**
     * Method to load the stored position and continue playing
     * @param filename is the file that stored a position
     */
    public void loadData(String filename){
        try {
            SudokuData data = SudokuIO.deSerializeFromFile(filename);
            setUserData(data.getData());
            setInitialData(data.getInitialData());
            setDataLevel(data.getLevel());
            setDataKeyPres(-1);
        }
        catch (IOException | ClassNotFoundException error) {
            messageBox.MsgBox("Could not load data from file! Error:"+error,"Warning!");
        }
        messageBox.MsgBox("Game data has been loaded successfully from the file:\n"+filename+"  Level: "+getDataLevel().toString(),"Load Game!");
    }

    /**
     * Method to save the position to a file
     * @param filename is the file that stored a position
     */
    public void saveData(String filename){
        try {
            SudokuIO.serializeToFile(filename,sudokuData);
        } catch (IOException error) {
            messageBox.MsgBox("Could not save to file! Error:"+error,"Warning!");
        }
        messageBox.MsgBox("Game data has been saved successfully to the file:\n"+filename,"Save Game!");
    }
}
