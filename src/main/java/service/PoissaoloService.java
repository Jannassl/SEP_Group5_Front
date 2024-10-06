package service;

import model.Poissaolo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateSessionFactoryManager;

public class PoissaoloService {

    private final SessionFactory sessionFactory;

    public PoissaoloService() {
        this.sessionFactory = HibernateSessionFactoryManager.getSessionFactory();
    }

    public Poissaolo createPoissaolo(Poissaolo poissaolo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(poissaolo);
            session.getTransaction().commit();
            return poissaolo;
        } catch (Exception e) {
            throw e;
        }
    }
}