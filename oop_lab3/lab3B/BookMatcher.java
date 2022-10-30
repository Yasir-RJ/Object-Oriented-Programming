import book.Book;

/** This interface represents a Book Matcher
 * that contains abstract method to match book for searching
 */
public interface BookMatcher {
    abstract boolean matches(Book BookToMatch);
}
