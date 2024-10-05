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

    // Add other methods as needed, e.g., findPoissaolo, deletePoissaolo, etc.
}