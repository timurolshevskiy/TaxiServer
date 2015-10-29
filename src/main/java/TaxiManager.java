import database.logic.TaxistHibernate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur on 17.10.2015.
 */
public class TaxiManager {

    private static final TaxiManager TAXI_MANAGER_INSTANCE = new TaxiManager();

    private List<String> taxists;

    private TaxiManager() {
        taxists = new ArrayList<String>();
        taxists.add("bombila");
    }

    public static TaxiManager getInstance() {
        return TAXI_MANAGER_INSTANCE;
    }

    public List<String> getTaxists() {
        return taxists;
    }

    public synchronized void addTaxist(TaxistHibernate taxistHibernate) {

    }

}
