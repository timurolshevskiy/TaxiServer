package database.dao;

import database.dao.impl.TaxistDaoImpl;

/**
 * Created by Timur on 26.10.2015.
 */
public class DaoFactory {

    private static TaxistDao taxistDao = null;
    private static DaoFactory instance = null;

    public static synchronized DaoFactory getInstance(){
        if (instance == null){
            instance = new DaoFactory();
        }
        return instance;
    }

    public TaxistDao getTaxistDao(){
        if (taxistDao == null){
            taxistDao = new TaxistDaoImpl();
        }
        return taxistDao;
    }
}
