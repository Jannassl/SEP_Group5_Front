package service;

import java.util.List;

import model.Oppitunti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import util.HibernateSessionFactoryManager;

public class OppituntiService {
    private final SessionFactory sessionFactory;

    public OppituntiService() {
        this.sessionFactory = HibernateSessionFactoryManager.getSessionFactory();
    }

    public List<Oppitunti> getAllOppitunnit() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Oppitunti", Oppitunti.class).list();
        }
    }

    public Oppitunti getOppituntiById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Oppitunti.class, id);
        }
    }

    public Oppitunti createOppitunti(Oppitunti oppitunti) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(oppitunti);
            transaction.commit();
            return oppitunti;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Oppitunti updateOppitunti(Long id, Oppitunti oppitunti) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            oppitunti.setOppitunti_id(id);
            session.update(oppitunti);
            transaction.commit();
            return oppitunti;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteOppitunti(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Oppitunti oppitunti = session.get(Oppitunti.class, id);
            if (oppitunti != null) {
                session.delete(oppitunti);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Oppitunti> getOppitunnitByKurssiId(Long kurssiId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Oppitunti> query = session.createQuery(
                    "FROM Oppitunti o WHERE o.kurssi.kurssi_id = :kurssiId", Oppitunti.class);
            query.setParameter("kurssiId", kurssiId);
            return query.list();
        }
    }
}