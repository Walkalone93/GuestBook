package ua.com.codefire.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.codefire.dao.GuestBookDao;
import ua.com.codefire.entities.Book;
import ua.com.codefire.entities.Guest;
import ua.com.codefire.entities.Result;

@Service
public class GuestBookServiceImpl implements GuestBookService {

    @Autowired
    private GuestBookDao guestBookDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Result> getAllRecords(Integer offset) {
        return guestBookDao.getAllRecords(offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCountRecords() {
        return guestBookDao.getCountRecords();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveGuest(String login, String password) {
        Guest guest = new Guest();
        guest.setLogin(login);
        guest.setPassword(password);
        String result = guestBookDao.insertNewGuest(guest);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String enterGuest(String login, String password) {
        Guest guest = new Guest();
        guest.setLogin(login);
        guest.setPassword(password);
        String result = guestBookDao.enterGuest(guest);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveRecord(String login, String message, String date) {
        Guest guest = new Guest();
        guest.setLogin(login);
        Book book = new Book();
        book.setRecord(message);
        book.setDate(date);
        guestBookDao.insertNewRecord(guest, book);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRecord(String login, String message, String date) {
        Guest guest = new Guest();
        guest.setLogin(login);
        Book book = new Book();
        book.setRecord(message);
        book.setDate(date);
        guestBookDao.deleteRecord(guest, book);
    }

}
