package service;

import java.util.List;

import model.Kurssi;
import model.Opiskelija;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryManager;

import java.time.LocalDate;

public class KurssiService {
    private final SessionFactory sessionFactory;

    public KurssiService() {
        this.sessionFactory = HibernateSessionFactoryManager.getSessionFactory();
    }

    public List<Kurssi> getAllKurssit() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Kurssi", Kurssi.class).list();
        }
    }

    public Kurssi getKurssiById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Kurssi.class, id);
        }
    }

    public Kurssi createKurssi(Kurssi kurssi) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(kurssi);
            transaction.commit();
            return kurssi;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Kurssi updateKurssi(Long id, Kurssi kurssi) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            kurssi.setKurssi_id(id);
            session.update(kurssi);
            transaction.commit();
            return kurssi;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteKurssi(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Kurssi kurssi = session.get(Kurssi.class, id);
            if (kurssi != null) {
                session.delete(kurssi);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
    public List<Kurssi> getKurssitByDate(LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<Kurssi> query = session.createQuery(
                    "FROM Kurssi k WHERE k.alkupvm <= :date AND k.loppupvm >= :date", Kurssi.class);
            query.setParameter("date", java.sql.Date.valueOf(date));
            return query.list();
        }
    }

    public List<Kurssi> getKurssitByOpettajaId(Long opettajaId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Kurssi> query = session.createQuery(
                    "FROM Kurssi k WHERE k.opettaja.opettaja_id = :opettajaId", Kurssi.class);
            query.setParameter("opettajaId", opettajaId);
            return query.list();
        }
    }

    public List<Kurssi> getKurssitByOpiskelija(Opiskelija opiskelija) {
        try (Session session = sessionFactory.openSession()) {
            Query<Kurssi> query = session.createQuery(
                    "SELECT DISTINCT ki.kurssi FROM KurssiIlmoittautuminen ki WHERE ki.opiskelija = :opiskelija", Kurssi.class);
            query.setParameter("opiskelija", opiskelija);
            return query.list();
        }
    }
}