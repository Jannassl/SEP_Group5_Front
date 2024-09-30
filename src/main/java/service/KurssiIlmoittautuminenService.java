package service;

import java.util.List;
import java.util.Date;

import model.KurssiIlmoittautuminen;
import model.Opiskelija;
import model.Kurssi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import util.HibernateSessionFactoryManager;

public class KurssiIlmoittautuminenService {
    private final SessionFactory sessionFactory;

    public KurssiIlmoittautuminenService() {
        this.sessionFactory = HibernateSessionFactoryManager.getSessionFactory();
    }

    public List<KurssiIlmoittautuminen> getAllIlmoittautumiset() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM KurssiIlmoittautuminen", KurssiIlmoittautuminen.class).list();
        }
    }

    public KurssiIlmoittautuminen getIlmoittautuminenById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(KurssiIlmoittautuminen.class, id);
        }
    }

    public KurssiIlmoittautuminen createIlmoittautuminen(KurssiIlmoittautuminen ilmoittautuminen) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(ilmoittautuminen);
            transaction.commit();
            return ilmoittautuminen;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public KurssiIlmoittautuminen updateIlmoittautuminen(Long id, KurssiIlmoittautuminen ilmoittautuminen) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            ilmoittautuminen.setIlmoittautuminen_id(id);
            session.update(ilmoittautuminen);
            transaction.commit();
            return ilmoittautuminen;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteIlmoittautuminen(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            KurssiIlmoittautuminen ilmoittautuminen = session.get(KurssiIlmoittautuminen.class, id);
            if (ilmoittautuminen != null) {
                session.delete(ilmoittautuminen);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<KurssiIlmoittautuminen> getIlmoittautumisetByKurssiId(Long kurssiId) {
        try (Session session = sessionFactory.openSession()) {
            Query<KurssiIlmoittautuminen> query = session.createQuery(
                    "FROM KurssiIlmoittautuminen ki WHERE ki.kurssi.kurssi_id = :kurssiId",
                    KurssiIlmoittautuminen.class
            );
            query.setParameter("kurssiId", kurssiId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}