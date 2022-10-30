package kth.yasir.sudoku.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kth.yasir.sudoku.model.MessageBox;
import kth.yasir.sudoku.model.SudokuModel;
import kth.yasir.sudoku.model.SudokuUtilities;

/**
 * class to implement view to set the GUI controls on the window
 * @author Yasir Riyadh 1/12/2021
 */
public class SudokuView extends BorderPane {
    private final SudokuModel model;
    private final SudokuController controller;
    private final Label[] [] numberTiles;
    private final BorderPane borderPane;
    private final TilePane numberPane;
    private Button CheckButton,HintButton,ClrKey;
    private Button[] NumKey;
    private MenuItem loadItem,saveItem,exitItem,newItem;
    private MenuItem easyItem,mediumItem,hardItem,clrAllItem, howItem,aboutItem;

    /**
     * copy constructor
     * initialize the area to 4 regions with Border pane layout
     * top region: Menu items
     * left region: VBox for check and hint buttons
     * right region: VBox for 1-9 and clear buttons
     * middle region: Tile Pane for 9x9 labels
     * @param model is a model object
     */
    public SudokuView(SudokuModel model) {
        this.model=model;
        MessageBox messageBox = new MessageBox();
        controller = new SudokuController(model,this, messageBox);
        borderPane = new BorderPane();
        numberTiles = new Label [SudokuUtilities.GRID_SIZE] [SudokuUtilities.GRID_SIZE];

        VBox VBoxL=LeftBox();
        VBox VBoxR=RightBox();
        MenuBar menuBar=CreateMenus();
        initNumberTiles();
        numberPane=makeNumberPane();

        setMargin(numberPane, new Insets(10,0,0,0));
        setMargin(VBoxL, new Insets(10,0,0,0));
        setMargin(VBoxR, new Insets(10,0,0,0));

        borderPane.setTop(menuBar);
        borderPane.setRight(VBoxR);
        borderPane.setLeft(VBoxL);
        borderPane.setCenter(numberPane);
        addEventHandlers(controller);
        this.getChildren().add(borderPane);
    }

    /**
     * Method to create left box layout
     * @return VBox
     */
    private VBox LeftBox(){
        VBox vbox = new VBox(5);
        CheckButton = new Button("Check");
        HintButton = new Button("Hint");
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinWidth(60);
        vbox.getChildren().add(CheckButton);
        vbox.getChildren().add(HintButton);
        return vbox;
    }
    /**
     * Method to create right box layout
     * @return VBox
     */
    private VBox RightBox(){
        VBox vbox = new VBox(3);
        NumKey =new Button[9];
        ClrKey=new Button(" C ");
        vbox.setMinWidth(50);
        vbox.setAlignment(Pos.CENTER);
        for (int i=1; i<10; i++) {
            NumKey[i-1]=new Button(" "+ i +" ");
            vbox.getChildren().add(NumKey[i-1]);
        }
        vbox.getChildren().add(ClrKey);
        return vbox;
    }

    /**
     * Method to create bar for menu items
     * @return MenuBar
     */
    private MenuBar CreateMenus() {
        MenuBar menuBar = new MenuBar();
        menuBar.setMinWidth(400);
        loadItem = new MenuItem("Load game");
        saveItem = new MenuItem("Save game");
        exitItem = new MenuItem("Exit");
        newItem = new MenuItem("New game");
        easyItem = new MenuItem("Easy");
        mediumItem = new MenuItem("Medium");
        hardItem = new MenuItem("Difficult");
        clrAllItem=new MenuItem("Clear all boxes");
        howItem = new MenuItem("How to play");
        aboutItem = new MenuItem("About ...");
        Menu fileMenu = new Menu("File");
        Menu gameMenu = new Menu("Game");
        Menu optionMenu = new Menu("Level");
        Menu helpMenu = new Menu("Help");
        fileMenu.getItems().addAll(loadItem,saveItem,exitItem);
        gameMenu.getItems().addAll(newItem,optionMenu);
        optionMenu.getItems().addAll(easyItem,mediumItem,hardItem);
        helpMenu.getItems().addAll(clrAllItem,howItem,aboutItem);
        menuBar.getMenus().addAll(fileMenu,gameMenu,helpMenu);
        return menuBar;
    }

    /**
     * Method to set controls states to handle functions
     * @param controller is a controller object
     */
    private void addEventHandlers(SudokuController controller) {
        CheckButton.setOnAction(e -> controller.handleCheckAction());
        HintButton.setOnAction(e -> controller.handleHintAction());
        loadItem.setOnAction(e -> controller.handleLoadAction(numberTiles));
        saveItem.setOnAction(e -> controller.handleSaveAction());
        exitItem.setOnAction(e -> controller.handleExitAction());
        newItem.setOnAction(e -> controller.handleNewAction(numberTiles));
        easyItem.setOnAction(e -> controller.handleEasyAction());
        mediumItem.setOnAction(e -> controller.handleMediumAction());
        hardItem.setOnAction(e -> controller.handleHardAction());
        clrAllItem.setOnAction(e -> controller.handleClearAllAction(numberTiles));
        howItem.setOnAction(e -> controller.handleHowAction());
        aboutItem.setOnAction(e -> controller.handleAboutAction());
        ClrKey.setOnAction(e ->controller.handleClearAction());
        for (int i=1; i<10; i++) {
            int finali = i;
            NumKey[i-1].setOnAction(e -> controller.handleKeyAction(finali));
        }
    }

    /**
     * Method to get mouse event
     */
    private final EventHandler<MouseEvent> tileCLickHandler;
    {
        tileCLickHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++)
                    for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++)
                        if (event.getSource() == numberTiles[row][col]) {
                            // we got the row and column - now call the appropriate controller method
                            controller.updateGameData(numberTiles,row,col);
                            return;
                        }
            }
        };
    }
    /**
     * Method to prepare 9x9 cells
     */
    private void initNumberTiles () {
        int[][][] Data = model.getUserData();
        Font font = Font.font ("Monospaced", FontWeight.NORMAL, 20);
        Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                null, new BorderWidths(.05)));
        for (int row = 0; row <SudokuUtilities.GRID_SIZE; row ++) {
            for (int col = 0; col <SudokuUtilities.GRID_SIZE; col ++) {
                Label tile = new Label (Integer.toString(Data[row][col][0])); // data from model
                if (Data[row][col][0]==0) tile.setText(" ");
                tile.setPrefWidth (32);
                tile.setPrefHeight (32);
                tile.setFont (font);
                tile.setAlignment (Pos.CENTER);
                tile.setBorder(border);
                tile.setOnMouseClicked (tileCLickHandler);
                numberTiles [row] [col] = tile;
            }
        }
    }

    /**
     * Method to set 3x3 cells in 81 cells grid
     * @return TilePane
     */
    private TilePane makeNumberPane () {
        // create the root tile pane
        TilePane root = new TilePane ();
        root.setPrefColumns (SudokuUtilities.SECTIONS_PER_ROW);
        root.setPrefRows (SudokuUtilities.SECTIONS_PER_ROW);
        root.setStyle ("-fx-border-color: black; -fx-border-width: 1.1px;");
        Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                null, new BorderWidths(.16)));
       for (int srow = 0; srow <SudokuUtilities.SECTIONS_PER_ROW; srow ++) {
            for (int scol = 0; scol <SudokuUtilities.SECTIONS_PER_ROW; scol ++) {
                TilePane section = new TilePane ();
                section.setPrefColumns (SudokuUtilities.SECTION_SIZE);
                section.setPrefRows (SudokuUtilities.SECTION_SIZE);
                section.setBorder(border);
                // add number tiles to this section
                for (int row = 0; row <SudokuUtilities.SECTION_SIZE; row ++) {
                    for (int col = 0; col <SudokuUtilities.SECTION_SIZE; col ++) {
                        // calculate which tile and add
                        section.getChildren ().add (numberTiles [srow * SudokuUtilities.SECTION_SIZE +
                                row] [scol * SudokuUtilities.SECTION_SIZE + col]);
                    }
                }
                root.getChildren().add(section);
            }
        }
        return root;
    }
}
