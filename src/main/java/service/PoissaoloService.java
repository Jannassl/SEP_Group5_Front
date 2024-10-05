package service;

import model.Poissaolo;
import model.Opiskelija;
import model.Oppitunti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import util.HibernateSessionFactoryManager;

import java.util.List;

public class PoissaoloService {
    private final SessionFactory sessionFactory;

    public PoissaoloService() {
        this.sessionFactory = HibernateSessionFactoryManager.getSessionFactory();
    }

    public List<Poissaolo> getAllPoissaolot() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Poissaolo", Poissaolo.class).list();
        }
    }

    public Poissaolo getPoissaoloById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Poissaolo.class, id);
        }
    }

    public Poissaolo createPoissaolo(Poissaolo poissaolo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(poissaolo);
            transaction.commit();
            return poissaolo;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Poissaolo updatePoissaolo(Long id, Poissaolo poissaolo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            poissaolo.setPoissaolo_id(id);
            session.update(poissaolo);
            transaction.commit();
            return poissaolo;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deletePoissaolo(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Poissaolo poissaolo = session.get(Poissaolo.class, id);
            if (poissaolo != null) {
                session.delete(poissaolo);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Poissaolo> getPoissaolotByOpiskelija(Opiskelija opiskelija) {
        try (Session session = sessionFactory.openSession()) {
            Query<Poissaolo> query = session.createQuery(
                    "FROM Poissaolo p WHERE p.opiskelija = :opiskelija", Poissaolo.class);
            query.setParameter("opiskelija", opiskelija);
            return query.list();
        }
    }

    public List<Poissaolo> getPoissaolotByOppitunti(Oppitunti oppitunti) {
        try (Session session = sessionFactory.openSession()) {
            Query<Poissaolo> query = session.createQuery(
                    "FROM Poissaolo p WHERE p.oppitunti = :oppitunti", Poissaolo.class);
            query.setParameter("oppitunti", oppitunti);
            return query.list();
        }
    }
}