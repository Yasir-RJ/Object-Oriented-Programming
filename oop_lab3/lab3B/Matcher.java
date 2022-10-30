import book.Author;
import book.Book;
import java.util.List;

/** This class represents an implementation of the Book Matcher interface
 * @author Yasir Riyadh (KTH TIDAA)
 * @since   2021-10-10
 */
public class Matcher implements BookMatcher {
    private String searchStr;

    /** Parametric constructor
     * @param searchStr is searching string represents title, author, or ISBN
     */
    public Matcher(String searchStr) {
        this.searchStr = searchStr;
    }

    /** Method to find a book that matches the specified search string for
     * three cases (title, author, or ISBN)
     * @param BookToMatch is a book object to match
     * @return true if the book matches the specified search string
     */
    @Override
    public boolean matches(Book BookToMatch) {
        String title =BookToMatch.getTitle().toLowerCase();
        List<Author> theAuthors=BookToMatch.getTheAuthors();
        String isbn =BookToMatch.getIsbn().getIsbnStr();
        if(searchStr.matches("[0-9]{13}"))
            return isbn.equals(searchStr);
        searchStr=searchStr.toLowerCase();
        if(title.contains(searchStr))
            return true;
        for(Author x:theAuthors) {
            String name=x.getName().toLowerCase();
            if (name.contains(searchStr)) return true;
        }
        return false;
    }
}
