package book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** This class represents a book which identifies the title, isbn, authors,
 *  genre and ranking of the book
 * @author Yasir Riyadh (KTH TIDAA)
 * @since   2021-10-10
 */

    public class Book implements Comparable<Book>,Serializable {

    private final String title;
    private final Isbn isbn;
    private final Genre genre;

    private int rating;
    private ArrayList <Author> theAuthors=new ArrayList<>();

    /** Parametric constructor
     * @param title the title of book
     * @param rating integer [1..5]
     * @param isbn is 13 digits number represents ISBN of the book
     * @param genre represents books category
     */
    public Book(String title, int rating, Isbn isbn, Genre genre) {
        this.title = title;
        this.rating=rating;
        this.isbn = isbn;
        this.genre = genre;
    }

    /** Method to get the title of the book */
    public String getTitle() {
        return title;
    }
    /** Method to get the ISBN of the book */
    public Isbn getIsbn() {
        return isbn;
    }
    /** Method to get the genre of the book */
    public Genre getGenre() {
        return genre;
    }
    /** Method to get the rating of the book */
    public int getRating() {
        return rating;
    }

    /** Method to add new author to the authors field of the book
     * @param author is name of the author
     */
    public void addAuthor(Author author){
        theAuthors.add(author);
    }

    /** Method to return a copy of list of authors */
    public List<Author> getTheAuthors() {
    return (List<Author>) theAuthors.clone();
    }

    /** Method to compares two objects lexicographically.
     * first key: title and second key: rating
     * @param other is a book object
     * @return  integer value (0 for equal),
     */
    @Override
    public int compareTo(Book other) {
        int res = this.title.compareTo(other.title);
        if ((res == 0) && (this.rating == other.rating)) return 0;
        else return res;
    }

    /** Method to test equality for two Books objects
     * equals should be consistent with compareTo (equals=true <=> compareTo=0)
     * @param other is other object
     * @return  true if two Books objects are equal,
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Book) {
            Book book = (Book) other;
            if(this.compareTo(book)==0) return true;
        }
        return false;
    }

    /** Method to format the book details.
     * @return  string contains formatted book details
     */
    @Override
    public String toString() {
        String info= "Book: \"" + getTitle() + "\"\n";
        for(Author author: getTheAuthors())
            info = info + ("  " + author);
        info = info + "\n" + getIsbn() + "\n" + "  Genre: " + getGenre() +
                "\n" + "  Rating: " + getRating();
        return info;
    }
}

