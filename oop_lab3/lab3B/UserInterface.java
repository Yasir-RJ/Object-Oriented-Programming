import book.Author;
import book.Book;
import book.Genre;
import book.Isbn;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/** This class represents a user interface to deal with the collection of books class
 * contains all methods that manipulate book operations
 * @author Yasir Riyadh (KTH TIDAA)
 * @since   2021-10-10
 */
public class UserInterface {
    private static CollectionOfBooks theCollection=new CollectionOfBooks();
    private static final String fileName="yasirBooks.txt";

    /** This is the main method which makes use of books operations.
     * and where execution of the code begins
     * it shows books menu and take and handle the user response
     *  @param args Unused
     */
    public static void main(String[] args)  {
        char ch0;
        Scanner input= new Scanner(System.in);
        do {
            Menu();
            System.out.print(">> Please, enter your selection: ");
            String response = input.next();
            response = response.toUpperCase();
            ch0 = response.charAt(0); // first character
            switch (ch0) {
                case '1' -> loadBooks();
                case '2' -> listBooks();
                case '3' -> addBooks();
                case '4' -> deleteBook();
                case '5' -> deleteAllBooks();
                case '6' -> searchBook();
                case '7' -> saveAndExit();
                default -> System.out.println(">> Illegal command");
            }
        } while (ch0 != '7');
    }

    /** Method to show the book menu to the user */
    public static void Menu() {
        System.out.println();
        System.out.println("---------Books Management Program-------------");
        System.out.println("1 Load books from file");
        System.out.println("2 List all books");
        System.out.println("3 Add new book");
        System.out.println("4 Delete book");
        System.out.println("5 Delete All books");
        System.out.println("6 Search for a book by title, author or ISBN");
        System.out.println("7 Exit and write all information to file");
        System.out.println("-----------------------------------------------");
    }

    /** Method to load all books that saved previously from the specified  file
     * (deserialize the books from a file)
     */
    public static void loadBooks() {
        List<Book> books=new ArrayList<>();
        BooksIO booksIO =new BooksIO();
        try {
            books=booksIO.deSerializeFromFile(fileName);
        }
        catch (IOException | ClassNotFoundException error) {
            System.out.println(error);
        }
        for(Book x: books)
            theCollection.addBook(x);
        System.out.println(">> " + theCollection.getNoOfBooks() + " Books have been loaded!");
    }

    /** Method to add a new book to the list
     * It takes the book details from the user
     * @throws InputMismatchException if rating or genre is out of range
     */
    public static void addBooks() {
        ArrayList<Author> theAuthors=new ArrayList<>(10);
        Scanner scan= new Scanner(System.in).useDelimiter("\n");   // allowed spaces
        System.out.print("Enter book title: ");
        String title = scan.next();
        System.out.print("Enter rating [1..5]: ");
        int rating=scan.nextInt();
        if(rating<1 || rating>5)
            throw new InputMismatchException("Unexpected value: " + rating);

        while(true) {
            System.out.print("Enter author name (0 if no more): ");
            String author = scan.next();
            if(author.charAt(0)=='0') break;
            System.out.print("Enter date Of Birth (yyyy-mm-dd): ");
            String date = scan.next();
            LocalDate dateOfBirth = LocalDate.parse(date);
            theAuthors.add(new Author(author,dateOfBirth));
        }

        System.out.print("Enter ISBN (13 digits): ");
        String isbnStr=scan.next();

        System.out.print("Enter genre (1.DRAMA, 2.ROMANCE, 3.CRIME, 4.HORROR): ");
        int genreInt = scan.nextInt();
        Genre genre;
        switch (genreInt) {
            case 1 -> genre = Genre.DRAMA;
            case 2 -> genre = Genre.ROMANCE;
            case 3 -> genre = Genre.CRIME;
            case 4 -> genre = Genre.HORROR;
            default -> throw new InputMismatchException("Unexpected value: " + genreInt);
        }
        Book newBook = new Book(title, rating, Isbn.createIsbn(isbnStr), genre);
        for(Author x: theAuthors)
            newBook.addAuthor(x);
        theCollection.addBook(newBook);
        System.out.println(">> The Book has been added successfully!");
    }

    /** Method to delete a book from the list
     * It takes the book title and rating from the user
     * @throws InputMismatchException if rating is out of range
     */
    public static void deleteBook() {
        if(theCollection.getNoOfBooks()>0) {
            Scanner scan= new Scanner(System.in).useDelimiter("\n");   // allowed spaces
            System.out.print(">> Enter book title to remove from list: ");
            String title=scan.next();
            System.out.print(">> Enter book ranking [1..5]: ");
            int rating=scan.nextInt();
            if(rating<1 || rating>5)
                throw new InputMismatchException ("Unexpected value: " + rating);
            Book other=new Book(title,rating,null,null);
            if(theCollection.deleteBook(other))
                System.out.println(">> The Book has been removed successfully!");
            else
                System.out.println(">> There is no such a book to delete!");
        }
        else System.out.println(">> Library is empty!");
    }

    /** Method to delete all books from the list
     */
    public static void deleteAllBooks() {
        if(theCollection.getNoOfBooks()>0) {
            System.out.println(">> Are you sure to delete all books in library? (Y/N): ");
            Scanner input = new Scanner(System.in);
            String res = input.next();
            if (res.toUpperCase().charAt(0) == 'Y') {
                theCollection.deleteAllBooks();
                System.out.println(">> All books in library have been deleted!");
            }
        }
        else System.out.println(">> Library is empty!");
    }

    /** Method to show all current books details in the list
     */
    public static void listBooks() {
        if(theCollection.getNoOfBooks()>0) {
            System.out.println(">> Listing of " + theCollection.getNoOfBooks() + " Books");
            for (Book book : theCollection.getBooks())
                System.out.println(book);
        }
        else System.out.println(">> Library is empty!");
    }

    /** Method to save all books to the specified  file then terminate the program
     * (serialize the books to a file)
     */
    public static void saveAndExit()  {
        BooksIO booksIO =new BooksIO();
        try {
            booksIO.serializeToFile(fileName,theCollection.getBooks());
        } catch (IOException error) {
            System.out.println(error);
            System.exit(1);
        }
        System.out.println(">> " + theCollection.getNoOfBooks() +
                " Books have been saved successfully in the file <"+fileName+"> !");
    }

    /** Method to search all existing books for matching string (title, author, or ISBN)
     * it then shows the sorted list of matched books that be found
     */
    public static void searchBook() {
        if(theCollection.getNoOfBooks()>0) {
            System.out.print("Enter a key word for title, author, or ISBN: ");
            Scanner input = new Scanner(System.in).useDelimiter("\n");
            String searchStr= input.next();
            List<Book> resultBooks;
            BookMatcher matcher = new Matcher(searchStr);
            resultBooks = theCollection.searchForBooks(matcher);
            if (resultBooks.size()>0) {
                System.out.println(">> Found [" + resultBooks.size() + "] books match!" );
                for(Book x: resultBooks)
                    System.out.println(x);
            }
            else
                System.out.println(">> There is no book with this key word!");

        }
        else System.out.println(">> Library is empty!");
    }
}

