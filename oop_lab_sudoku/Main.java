package kth.yasir.sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kth.yasir.sudoku.model.SudokuModel;
import kth.yasir.sudoku.view.SudokuView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SudokuModel model = new SudokuModel();
        SudokuView view = new SudokuView(model);
        Scene scene = new Scene(view,400,335);
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
