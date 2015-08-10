package ua.com.codefire.dao;

import java.util.List;
import ua.com.codefire.entities.Book;
import ua.com.codefire.entities.Guest;
import ua.com.codefire.entities.Result;

public interface GuestBookDao {

    /**
     * The method allows to receive 10 records from the database for a particular shift
     *
     * @param offset it allows to skip a specified number of rows in the database 
     * before the receive 10 entries
     * @return a list of records from the database
     */
    public List<Result> getAllRecords(Integer offset);

    /**
     * The method allows to obtain the number of records in the table
     *
     * @return the number of records in the table
     */
    public Integer getCountRecords();

    /**
     * The method allows to verify the existence of a specific user. 
     * In case of absence of the user - to add it to the database
     *
     * @param guest tabular representation of the user
     * @return the result of saving user in the database (stored - true)
     */
    public String insertNewGuest(Guest guest);

    /**
     * The method allows to verify the existence user in the database
     *
     * @param guest tabular representation of the user
     * @return the result of verifying the existence user in the database (there is - true)
     */
    public String enterGuest(Guest guest);

    /**
     * The method allows you to add a specific record in the database
     *
     * @param guest tabular representation of the user
     * @param book tabular representation of the record
     */
    public void insertNewRecord(Guest guest, Book book);

    /**
     * The method allows to delete an entry from the database
     *
     * @param guest tabular representation of the user
     * @param book tabular representation of the record
     */
    public void deleteRecord(Guest guest, Book book);
}
