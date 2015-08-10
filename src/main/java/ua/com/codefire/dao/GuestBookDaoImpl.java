package ua.com.codefire.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.codefire.entities.Book;
import ua.com.codefire.entities.Guest;
import ua.com.codefire.entities.Result;

@Repository
public class GuestBookDaoImpl implements GuestBookDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Result> getAllRecords(Integer offset) {
        Session session = null;
        List<Result> list = new ArrayList();
        try {
            session = sessionFactory.openSession();
            List<Object[]> l = session.createQuery("select g.login, b.record, b.date "
                    + "from Book b "
                    + "INNER JOIN b.guestId g "
                    + "ORDER BY b.id DESC")
                    .setFirstResult(offset)
                    .setMaxResults(offset + 10)
                    .list();
            for (int i = 0; i < l.size(); i++) {
                Result result = new Result();
                result.setLogin((String) l.get(i)[0]);
                result.setRecord((String) l.get(i)[1]);
                result.setDate((String) l.get(i)[2]);
                list.add(i, result);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCountRecords() {
        Session session = null;
        Integer count = null;
        try {
            session = sessionFactory.openSession();
            count = Integer.parseInt(session.createQuery("select count(*) from Book").uniqueResult().toString());
        } catch (HibernateException ex) {
            System.out.println(ex);
        } catch (NumberFormatException ex) {
            System.out.println(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String insertNewGuest(Guest guest) {
        Session session = null;
        Integer count = null;
        try {
            session = sessionFactory.openSession();
            count = Integer.parseInt(session.
                    createQuery("select count(*) from Guest g where g.login=:login")
                    .setParameter("login", guest.getLogin())
                    .uniqueResult().toString());
            if (count == 0) {
                session.save(guest);
                return "true";
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return "false";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String enterGuest(Guest guest) {
        Session session = null;
        Integer count = null;
        try {
            session = sessionFactory.openSession();
            count = Integer.parseInt(session.
                    createQuery("select count(*) from Guest g where g.login=:login and g.password=:password")
                    .setParameter("login", guest.getLogin())
                    .setParameter("password", guest.getPassword())
                    .uniqueResult().toString());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        if (count == 0) {
            return "false";
        } else {
            return "true";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertNewRecord(Guest guest, Book book) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Guest g = (Guest) session.createQuery("from Guest g where g.login=:login")
                    .setString("login", guest.getLogin())
                    .uniqueResult();
            book.setGuestId(g);
            session.save(book);
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRecord(Guest guest, Book book) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Guest g = (Guest) session.createQuery("from Guest g where g.login=:login")
                    .setString("login", guest.getLogin())
                    .uniqueResult();
            session.createQuery("delete Book where guest_id = :guest_id")
                    .setParameter("guest_id", g.getId()).executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}