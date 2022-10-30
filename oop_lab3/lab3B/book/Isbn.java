package book;
import java.io.Serializable;

/** This class represents ISBN of the book which identifies the registrant
 *  as well as the specific title, edition and format.
 * @author Yasir Riyadh (KTH TIDAA)
 * @since   2021-10-10
 */
public class Isbn implements Serializable {
    private final String isbnStr;
    private static final String isbnPattern = "[0-9]{13}";

    /** private Parametric constructor
     * @param isbnStr represents ISBN string of the book
     * @throws IllegalIsbnException if ISBN is not match the pattern (13 digits)
     */
    private Isbn(String isbnStr) throws IllegalIsbnException{
        isbnStr = isbnStr.replace("-", "");
        boolean match = isbnStr.matches(isbnPattern);
        if (!match)
            throw new IllegalIsbnException("illegal ISBN: " + isbnStr);
        this.isbnStr = isbnStr;
    }

    /** Method to call the private constructor and create new ISBN object
     * @param isbnStr represents ISBN string of the book
     * @throws IllegalIsbnException if ISBN is not match the pattern (13 digits)
     */
    public static Isbn createIsbn(String isbnStr) throws IllegalIsbnException {
        return new Isbn(isbnStr);
    }

    /** Method to access class private data member
     * @return the ISBN of the book
     */
    public String getIsbnStr() {
        return isbnStr;
    }

    /** Method to test equality for two ISBN objects
     * @param other is other object
     * @return  true if two ISBN objects are equal,
     */
    @Override
    public boolean equals(Object other) {
        if(other instanceof Isbn) {
            Isbn otherIsbn = (Isbn) other;
            return this.isbnStr.equals(otherIsbn.isbnStr);
        }
        return false;
    }

    /** Method to format the ISBN detail.
     * @return  string contains formatted ISBN detail
     */
    @Override
    public String toString() {
        return "  ISBN: " + getIsbnStr() ;
    }
}
