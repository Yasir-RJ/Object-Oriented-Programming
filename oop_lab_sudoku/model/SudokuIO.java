package kth.yasir.sudoku.model;
import java.io.*;

/**
 * class to implement serialization and deserialization
 * @author Anders Lindström, anderslm@kth.se
 */
public class SudokuIO {
    /**
     * method to store game data in serialized form, on file the specified file.
     */
    public static void serializeToFile(String filename, SudokuData Data) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(Data);
        }
        finally {
            try { if(out != null)	out.close(); }
            catch(Exception e) { e.printStackTrace(); }
        }
    }
    /**
     * method to deserialize game data from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public static SudokuData deSerializeFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            // readObject returns a reference of type Object, hence the down-cast
            SudokuData Data=new SudokuData();
            Data = (SudokuData) in.readObject();
            return Data;
        }
        finally {
            try { if(in != null)	in.close(); }
            catch(Exception e) { e.printStackTrace(); }
        }
    }
}
