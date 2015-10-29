package database.dao.impl;

import database.dao.TaxistDao;
import database.logic.TaxistHibernate;
import database.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur on 26.10.2015.
 */
public class TaxistDaoImpl implements TaxistDao {

    public List<TaxistHibernate> getAllTaxists() throws SQLException {
        Session session = null;
        List<TaxistHibernate> taxists = new ArrayList<TaxistHibernate>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            taxists = session.createCriteria(TaxistHibernate.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return taxists;
    }

    public TaxistHibernate getTaxistById(int id) throws SQLException {
        Session session = null;
        TaxistHibernate taxistHibernate = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            taxistHibernate = (TaxistHibernate) session.load(TaxistHibernate.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return taxistHibernate;
    }

    public void deleteTaxist(TaxistHibernate taxist) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(taxist);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateTaxist(TaxistHibernate taxist) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(taxist);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void addTaxist(TaxistHibernate taxist) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(taxist);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
