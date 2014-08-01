package com.adfhomebrew.jsoup.cronjob.pubmed;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;

import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_DRIVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TARGET_SERVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TRANSACTION_TYPE;
import org.eclipse.persistence.config.TargetServer;

public class PersistenceUtil {

    public void persist(List<Serializable> persisList) {
        EntityManagerFactory emf = getOutsideContainerEM();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        for (Serializable entity : persisList) {
            em.persist(entity);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    /**
     *http://wiki.eclipse.org/EclipseLink/Examples/JPA/OutsideContainer
     * @return
     */
    private EntityManagerFactory getOutsideContainerEM() {
        Map properties = new HashMap();
        // Ensure RESOURCE_LOCAL transactions is used.
        properties.put(TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
        // Configure the internal EclipseLink connection pool
        properties.put(JDBC_DRIVER, "com.mysql.jdbc.Driver");
        properties.put(JDBC_URL, "jdbc:mysql://localhost:3306/dbname");
        properties.put(JDBC_USER, "user");
        properties.put(JDBC_PASSWORD, "pass");
        properties.put(TARGET_SERVER, TargetServer.None);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("core", properties);
        return emf;
    }
}
