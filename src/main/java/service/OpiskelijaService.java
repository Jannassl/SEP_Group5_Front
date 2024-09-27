package service;

import java.util.List;

import model.Opiskelija;
import model.Kurssi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import util.HibernateSessionFactoryManager;

public class OpiskelijaService {
    private final SessionFactory sessionFactory;

    public OpiskelijaService() {
        this.sessionFactory = HibernateSessionFactoryManager.getSessionFactory();
    }

    public List<Opiskelija> getAllOpiskelijat() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Opiskelija", Opiskelija.class).list();
        }
    }

    public Opiskelija getOpiskelijaById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Opiskelija.class, id);
        }
    }

    public Opiskelija createOpiskelija(Opiskelija opiskelija) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(opiskelija);
            transaction.commit();
            return opiskelija;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Opiskelija updateOpiskelija(Long id, Opiskelija opiskelija) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            opiskelija.setOpiskelija_id(id);
            session.update(opiskelija);
            transaction.commit();
            return opiskelija;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteOpiskelija(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Opiskelija opiskelija = session.get(Opiskelija.class, id);
            if (opiskelija != null) {
                session.delete(opiskelija);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

}