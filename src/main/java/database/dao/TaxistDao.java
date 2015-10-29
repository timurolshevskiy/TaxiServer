package database.dao;

import database.logic.TaxistHibernate;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Timur on 26.10.2015.
 */
public interface TaxistDao {

    public void addTaxist(TaxistHibernate taxist) throws SQLException;
    public void updateTaxist(TaxistHibernate taxist) throws SQLException;
    public void deleteTaxist(TaxistHibernate taxist) throws SQLException;
    public TaxistHibernate getTaxistById(int id) throws SQLException;
    public List<TaxistHibernate> getAllTaxists() throws SQLException; //!

}
