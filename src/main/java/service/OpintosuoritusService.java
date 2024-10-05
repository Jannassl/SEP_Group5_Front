package service;

import model.Opintosuoritus;
import model.Opiskelija;
import model.Kurssi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import util.HibernateSessionFactoryManager;

import java.util.List;

public class OpintosuoritusService {
    private final SessionFactory sessionFactory;

    public OpintosuoritusService() {
        this.sessionFactory = HibernateSessionFactoryManager.getSessionFactory();
    }

    public List<Opintosuoritus> getAllOpintosuoritukset() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Opintosuoritus", Opintosuoritus.class).list();
        }
    }

    public Opintosuoritus getOpintosuoritusById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Opintosuoritus.class, id);
        }
    }

    public Opintosuoritus createOpintosuoritus(Opintosuoritus opintosuoritus) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(opintosuoritus);
            transaction.commit();
            return opintosuoritus;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Opintosuoritus updateOpintosuoritus(Long id, Opintosuoritus opintosuoritus) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            opintosuoritus.setSuoritus_id(id);
            session.update(opintosuoritus);
            transaction.commit();
            return opintosuoritus;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteOpintosuoritus(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Opintosuoritus opintosuoritus = session.get(Opintosuoritus.class, id);
            if (opintosuoritus != null) {
                session.delete(opintosuoritus);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Opintosuoritus> getOpintosuorituksetByOpiskelija(Opiskelija opiskelija) {
        try (Session session = sessionFactory.openSession()) {
            Query<Opintosuoritus> query = session.createQuery(
                    "FROM Opintosuoritus o WHERE o.opiskelija = :opiskelija", Opintosuoritus.class);
            query.setParameter("opiskelija", opiskelija);
            return query.list();
        }
    }

    public List<Opintosuoritus> getOpintosuorituksetByKurssi(Kurssi kurssi) {
        try (Session session = sessionFactory.openSession()) {
            Query<Opintosuoritus> query = session.createQuery(
                    "FROM Opintosuoritus o WHERE o.kurssi = :kurssi", Opintosuoritus.class);
            query.setParameter("kurssi", kurssi);
            return query.list();
        }
    }

    public Double getAverageGradeForKurssi(Kurssi kurssi) {
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createQuery(
                    "SELECT AVG(o.arvosana) FROM Opintosuoritus o WHERE o.kurssi = :kurssi", Double.class);
            query.setParameter("kurssi", kurssi);
            return query.uniqueResult();
        }
    }
}