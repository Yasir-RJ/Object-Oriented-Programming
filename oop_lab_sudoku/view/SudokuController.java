package kth.yasir.sudoku.view;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kth.yasir.sudoku.model.MessageBox;
import kth.yasir.sudoku.model.SudokuModel;
import kth.yasir.sudoku.model.SudokuUtilities;
import java.io.File;

/**
 * class to implement controller to handel all game events and update data
 * @author Yasir Riyadh 1/12/2021
 */
public class SudokuController {
    private final SudokuModel model;
    private final SudokuView view;
    private final MessageBox messageBox;

    /**
     * copy constructor
     * @param model is a model object
     * @param view is a view object
     * @param messageBox is a messageBox object
     */
    public SudokuController(SudokuModel model, SudokuView view, MessageBox messageBox) {
        this.model = model;
        this.view=view;
        this.messageBox=messageBox;
    }

    /**
     * method to handel check button if clicked event
     * compare the original data with user data to check if numbers are correct
     */
    public void handleCheckAction() {
        int[][][] userData = model.getUserData();
        boolean gameSolved = true;
        for (int r = 0; r < SudokuUtilities.GRID_SIZE; r++)
            for (int c = 0; c < SudokuUtilities.GRID_SIZE; c++)
                if (userData[r][c][0] != userData[r][c][1]) {
                    gameSolved = false;
                    break;
                }
        if (gameSolved)
            messageBox.MsgBox("Congratulation!\n You have solved Sudoku problem. ", "Checking ...");
        else
            messageBox.MsgBox("Game is not solved!\n (There is some cells are empty or not correct)", "Checking ...");
    }

    /**
     * method to update new numbers in the 9x9 tiles on the view window
     * check the type of the number:
     * black: for original number
     * gray: for number that entered by player
     * red: for hint number
     * @param numberTiles is the tiles current states
     */
    public void updateTiles(Label[][] numberTiles){
        int[][][] Data3d = model.getUserData();
        int[][] Data2d=model.getInitialData();
        model.setDataKeyPres(-1);
        model.setDataClear(false);
        model.setNeedHint(false);
        for (int row = 0; row <SudokuUtilities.GRID_SIZE; row ++)
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                // select between black or gray color
                if(Data2d[row][col] != 0) numberTiles[row][col].setTextFill(Color.BLACK);
                else numberTiles[row][col].setTextFill(Color.GRAY.brighter());
                //select between number or empty cell
                if(Data3d[row][col][0]!=0) numberTiles[row][col].setText(Integer.toString(Data3d[row][col][0]));
                else numberTiles[row][col].setText(" ");
            }
    }

    /**
     * method to update new numbers in the data model matrix
     * according to the flags states
     * @param numberTiles is the tiles current states
     * @param row the row of matrix
     * @param col the column of matrix
     */
    public void updateGameData(Label[][] numberTiles, int row, int col){
        int[][][] Data = model.getUserData();
        int[][] initialData=model.getInitialData();
        if(model.isNeedHint() && (numberTiles[row][col].getText().equals(" "))){
            Data [row][col][0]=Data [row][col][1];
            numberTiles[row][col].setText(Integer.toString(Data [row][col][1]));
            numberTiles[row][col].setTextFill(Color.RED);
            return;
        }
        if((model.getDataKeyPres() != -1) && (numberTiles[row][col].getText().equals(" "))){
            Data [row][col][0]=model.getDataKeyPres();
            model.setUserData(Data);
            numberTiles[row][col].setText(Integer.toString(model.getDataKeyPres()));
            numberTiles[row][col].setTextFill(Color.GRAY.brighter());
            return;
        }
        if(model.isDataClear() && (!numberTiles[row][col].getText().equals(" "))){
            if(initialData[row][col] == 0) {   // don't clear initial data
                Data[row][col][0] = 0;
                model.setUserData(Data);  // update new data
                numberTiles[row][col].setText(" ");
            }
        }
    }

    /**
     * controller methods to handel the menu items
     */
    public void handleExitAction(){ Platform.exit(); }
    public void handleEasyAction(){ model.setDataLevel(SudokuUtilities.SudokuLevel.EASY); }
    public void handleMediumAction(){ model.setDataLevel(SudokuUtilities.SudokuLevel.MEDIUM);}
    public void handleHardAction(){ model.setDataLevel(SudokuUtilities.SudokuLevel.HARD);}
    public void handleAboutAction(){
        String aboutStr="Prepared by: Yasir Riyadh Jabbar\nObject-oriented programming / Laboratory 5 \nKTH 1/12/2021";
        messageBox.MsgBox(aboutStr,"About ...");
    }
    public void handleClearAction(){
        model.setDataKeyPres(-1);
        model.setDataClear(true);
        model.setNeedHint(false);
    }
    public void handleClearAllAction(Label [][] numberTiles){
        int[][] InitialData=model.getInitialData();
        int[][][] UserData= model.getUserData();
        for (int r = 0; r < SudokuUtilities.GRID_SIZE; r++)
            for (int c = 0; c < SudokuUtilities.GRID_SIZE; c++)
                UserData[r][c][0]=InitialData[r][c];
        model.setUserData(UserData);
        model.setDataKeyPres(-1);
        model.setDataClear(false);
        model.setNeedHint(false);
        updateTiles(numberTiles);
    }
    public void handleKeyAction(int keyPres){
        model.setDataKeyPres(keyPres);
        model.setDataClear(false);
        model.setNeedHint(false);
    }
    public void handleHintAction(){
        model.setDataKeyPres(-1);
        model.setDataClear(false);
        model.setNeedHint(true);
    }
    public void handleNewAction(Label [][] numberTiles){
        model.setUserData(model.GenerateRandData(model.getDataLevel()));
        model.setInitialData(model.getUserData());
        updateTiles(numberTiles);
    }
    public void handleLoadAction(Label [][] numberTiles){
        String filename;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sudoku Files",
                "*.sudoku"));
        File selectedFile = fileChooser.showOpenDialog(Stage.getWindows().get(0));
        if(selectedFile != null){
            filename=selectedFile.getAbsolutePath();
            model.loadData(filename);
            updateTiles(numberTiles);
        }
    }
    public void handleSaveAction(){
        String filename;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("game.sudoku");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sudoku Files",
                "*.sudoku"));
        File selectedFile = fileChooser.showSaveDialog(Stage.getWindows().get(0));
        if(selectedFile != null) {
            filename = selectedFile.getAbsolutePath();
            model.saveData(filename);
        }
    }
    public void handleHowAction(){
        String instStr= """
                Every sudoku puzzle involves a 9×9 grid of squares subdivided into 3×3 boxes.
                In total there are 81 squares on a sudoku grid and when the puzzle is completed each square will contain exactly one number.
                 . Each 3×3 box can only contains a number 1-9 once.
                 . Each vertical column can only contains a number 1-9 once.
                 . Each horizontal row can only contains a number 1-9 once.
                _____________________________________________________________
                Steps to play:
                 1) By default game started with <Medium> level.
                 2) Select any game level from menu <Game -> Level>
                 3) Select <New game> to start (there is 3 randoms versions for each level).
                 4) Use buttons <1> to <9> then click LMouse on any cell to fill it with number.
                 5) Use button <C> to clear any cell.
                 6) You can use button <Hint> then click LMouse  on any cell to see the correct number.
                 7) After all cells having numbers, you can use button <Check> to test the position.
                 8) You can use <Save game> or <Load game> at any time while playing to store or load game position.
                _____________________________________________________________
                """;
        messageBox.MsgBox(instStr,"How to play...");
    }
}
