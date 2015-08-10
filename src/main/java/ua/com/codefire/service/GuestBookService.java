package ua.com.codefire.service;

import java.util.List;
import ua.com.codefire.entities.Result;

public interface GuestBookService {

    /**
     * The method allows to obtain records from the database for a particular shift
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
     * The method allows to keep the user in the database
     *
     * @param login user login
     * @param password user password
     * @return the result of saving user in the database (stored - true)
     */
    public String saveGuest(String login, String password);

    /**
     * The method allows to verify the existence of a specific user.
     * In case of absence of the user - to add it to the database
     *
     * @param login user login
     * @param password user password
     * @return the result of verifying the existence of the user in the database (there is - true)
     */
    public String enterGuest(String login, String password);

    /**
     * The method allows you to add a specific record to the database
     *
     * @param login user login
     * @param message record
     * @param date the date and time when the record was added
     */
    public void saveRecord(String login, String message, String date);

    /**
     * The method allows to delete an entry from the database
     *
     * @param login user login
     * @param message record
     * @param date the date and time when the record was added
     */
    public void deleteRecord(String login, String message, String date);

}
