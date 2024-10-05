package service;

import java.util.List;
import model.Opiskelija;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(opiskelija);
            session.getTransaction().commit();
            return opiskelija;
        } catch (Exception e) {
            throw e;
        }
    }

    public Opiskelija updateOpiskelija(Long id, Opiskelija opiskelija) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            opiskelija.setOpiskelija_id(id);
            session.update(opiskelija);
            session.getTransaction().commit();
            return opiskelija;
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteOpiskelija(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Opiskelija opiskelija = session.get(Opiskelija.class, id);
            if (opiskelija != null) {
                session.delete(opiskelija);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }
}