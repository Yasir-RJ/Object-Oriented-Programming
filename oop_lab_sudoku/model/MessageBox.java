package kth.yasir.sudoku.model;
import javafx.scene.control.Alert;

public class MessageBox {
    public MessageBox(){ }

    /**
     * Method to show a message in window box
     * @param message the alert message
     * @param title the title of window box
     */
    public void MsgBox(String message, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("");
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
