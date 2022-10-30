package book;

import java.io.Serializable;
import java.time.LocalDate;

/** This class represents the details of author which identifies
 * the name and date of birth for an author
 * @author Yasir Riyadh (KTH TIDAA)
 * @since   2021-10-10
 */
public class Author implements Serializable {
    private final String name;
    private final LocalDate dateOfBirth;

    /** Parametric constructor
     * @param name is name of the author
     * @param dateOfBirth is date of birth of the author
     */
    public Author(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    /** Method to get the name of the author */
    public String getName() {
        return name;
    }
    /** Method to get the date of birth of the author */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /** Method to format the author details.
     * @return  string contains formatted author details
     */
    @Override
    public String toString() {
        return  getName() + " (" + getDateOfBirth() + ")";
    }
}
