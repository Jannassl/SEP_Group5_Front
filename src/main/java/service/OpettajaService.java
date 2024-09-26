package service;

import java.util.List;

import model.Opettaja;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import util.HibernateSessionFactoryManager;
import org.mindrot.jbcrypt.BCrypt;

public class OpettajaService {
    private final SessionFactory sessionFactory;

    public OpettajaService() {
        this.sessionFactory = HibernateSessionFactoryManager.getSessionFactory();
    }

    public List<Opettaja> getAllOpettajat() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Opettaja", Opettaja.class).list();
        }
    }

    public Opettaja getOpettajaById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Opettaja.class, id);
        }
    }

    public Opettaja createOpettaja(Opettaja opettaja) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();


            String hashedPassword = BCrypt.hashpw(opettaja.getSalasana(), BCrypt.gensalt());
            opettaja.setSalasana(hashedPassword);

            session.save(opettaja);
            transaction.commit();
            return opettaja;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Opettaja updateOpettaja(Long id, Opettaja opettaja) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Opettaja existingOpettaja = session.get(Opettaja.class, id);
            if (existingOpettaja != null) {
                existingOpettaja.setEtunimi(opettaja.getEtunimi());
                existingOpettaja.setSukunimi(opettaja.getSukunimi());
                existingOpettaja.setSahkoposti(opettaja.getSahkoposti());


                if (opettaja.getSalasana() != null && !opettaja.getSalasana().isEmpty()) {
                    String hashedPassword = BCrypt.hashpw(opettaja.getSalasana(), BCrypt.gensalt());
                    existingOpettaja.setSalasana(hashedPassword);
                }

                session.update(existingOpettaja);
            }

            transaction.commit();
            return existingOpettaja;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteOpettaja(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Opettaja opettaja = session.get(Opettaja.class, id);
            if (opettaja != null) {
                session.delete(opettaja);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Opettaja login(String email, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<Opettaja> query = session.createQuery(
                    "FROM Opettaja o WHERE o.sahkoposti = :email", Opettaja.class);
            query.setParameter("email", email);
            Opettaja opettaja = query.uniqueResult();

            if (opettaja != null && verifyPassword(password, opettaja.getSalasana())) {
                return opettaja;
            }
            return null;
        }
    }

    /*private boolean verifyPassword(String inputPassword, String storedHash) {
        return BCrypt.checkpw(inputPassword, storedHash);
    }*/
    private boolean verifyPassword(String inputPassword, String storedHash) {
        // Temporarily use plain text comparison for testing
        return inputPassword.equals(storedHash);
    }
}