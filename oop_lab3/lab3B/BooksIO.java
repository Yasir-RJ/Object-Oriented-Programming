
import book.Book;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Hints on how to implement serialization and deserialization
 * in class CollectionOfBooks.
 */
public class BooksIO {
    /**
     * Call this method before the application exits, to store the books,
     * in serialized form, on file the specified file.
     */
    public void serializeToFile(String filename, List<Book> books) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(books);
        }
        finally {
            try {
                if(out != null)	out.close();
            } catch(Exception e) {}
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the books
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public List<Book> deSerializeFromFile(String filename) throws IOException, ClassNotFoundException {

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            // readObject returns a reference of type Object, hence the down-cast
            List<Book> books = (List<Book>) in.readObject();
            return books;
        }
        finally {
            try {
                if(in != null)	in.close();
            } catch(Exception e) {}
        }
    }
}


