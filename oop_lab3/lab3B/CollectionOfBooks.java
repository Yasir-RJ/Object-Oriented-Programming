
import book.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** This class represents a CollectionOfBooks which handles books operations
 *  like add book , delete book, get current books, and search for specific book
 * @author Yasir Riyadh (KTH TIDAA)
 * @since   2021-10-10
 */

public class CollectionOfBooks {
    private static ArrayList<Book> theBooks;
    private static int noOfBooks;

    /** Default constructor
     * Create list of books object and initialize the books counter
     */
    public CollectionOfBooks() {
        theBooks= new ArrayList<>();
        noOfBooks=0;
    }

    /** Method to get copy of the books list  */
    public List<Book> getBooks() {     // get coby of books
        return (List<Book>) theBooks.clone();
    }

    /** Method to get number of the books */
    public int getNoOfBooks() {
        return noOfBooks;
    }

    /** Method to add new book to the list and increment the books counter
    * @param book is new book object
    * @throws NullPointerException if received null object
    */
    public void addBook(Book book){
        if(book==null)
            throw new NullPointerException("Null Book");
        theBooks.add(book);
        noOfBooks++;
    }

    /** Method to delete an existing book from the list and decrement the books counter
     * @param book is the book to be deleted
     * @return false if list is empty
     * @throws NullPointerException if received null object
     */
    public boolean deleteBook(Book book){
        if(book==null)
            throw new NullPointerException("Null object");
        for(Book b: theBooks){
            if(b.equals(book)) {
                theBooks.remove(book);
                noOfBooks--;
                return true;
            }
        }
        return false;
    }

    /** Method to delete all existing books and clear the books counter
     * @return false if list is empty
     */
    public boolean deleteAllBooks(){
        if(getNoOfBooks()>0){
            theBooks.clear();
            noOfBooks=0;
            return true;
        }
        else return false;
    }

    /** Method to search all existing books for matching string (title, author, or ISBN)
     * @param matcher is a book object to match
     * @return sorted list of matched books that be found
     */
    public List<Book> searchForBooks(BookMatcher matcher){
            ArrayList<Book> resultBooks = new ArrayList<>();
            for (Book x : theBooks)
                if (matcher.matches(x)) resultBooks.add(x);
            Collections.sort(resultBooks, Book::compareTo);
            return resultBooks;
    }
}
