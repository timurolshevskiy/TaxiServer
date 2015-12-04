import com.scholota.taxi.PassengerQuery;
import com.scholota.taxi.Taxist;
import database.dao.DaoFactory;
import database.dao.TaxistDao;
import database.logic.TaxistHibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Timur on 17.10.2015.
 */
public class TaxiManager {

    private static int R = 6371;
    private static TaxiManager TAXI_MANAGER_INSTANCE = new TaxiManager();

    //private Map<Taxist, LatLng> taxists = new Map<Taxist, LatLng>();
    private Map<String, List<Taxist>> regions = new HashMap<String, List<Taxist>>();
    private Map<Taxist, PassengerQuery> taxists = new HashMap<Taxist, PassengerQuery>();

    private static final TaxistDao TAXIST_DAO = DaoFactory.getInstance().getTaxistDao();

    private TaxiManager() {
//        Taxist taxist = new Taxist();
//        taxist.setLatLng(50, 36);
//        taxist.setRegion("Ð¥Ð°Ñ\u0080Ñ\u008CÐºÐ¾Ð²");
//        regions.put("Ð¥Ð°Ñ\u0080Ñ\u008CÐºÐ¾Ð²", new ArrayList<Taxist>());
//        regions.get("Ð¥Ð°Ñ\u0080Ñ\u008CÐºÐ¾Ð²").add(taxist);
    }

    public static synchronized TaxiManager getInstance() {
        return TAXI_MANAGER_INSTANCE;
    }


    public static synchronized TaxistDao dao() {
        return TAXIST_DAO;
    }

    public synchronized Taxist getBest(PassengerQuery passengerQuery) {
        //! ���� ��� �������

        List<Taxist> taxistsInRegion = regions.get(passengerQuery.getRegion());
        if (taxistsInRegion.size() == 0) {
            return null;
        }
        Taxist best = taxistsInRegion.get(0);
        double bestDistance = calculateDistance(best.getLat(), best.getLng(), passengerQuery.getLat(), passengerQuery.getLng());
        for(Taxist tx : taxistsInRegion) {
            double tempDistance = calculateDistance(tx.getLat(), tx.getLng(), passengerQuery.getLat(), passengerQuery.getLng());
            if (bestDistance > tempDistance) {
                best = tx;
                bestDistance = tempDistance;
            }
        }
        taxistsInRegion.remove(best);
        taxists.put(best, passengerQuery);

        //notifyAll();
        return best;
    }

    private static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lng2 - lng1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;

        return distance;
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    public synchronized void addTaxist(Taxist taxist) {
        String txRegion = taxist.getRegion();
        if(!regions.containsKey(txRegion)) regions.put(txRegion, new ArrayList<Taxist>());
        List<Taxist> tx = regions.get(taxist.getRegion());
        taxists.remove(taxist);
        tx.remove(taxist); //ensure, that list doesnt contain taxist
        tx.add(taxist);
    }

    public synchronized void removePair(PassengerQuery passengerQuery) {
        taxists.remove(getTaxist(passengerQuery));
    }

    public synchronized void removeTaxist(Taxist taxist) {
        regions.get(taxist.getRegion()).remove(taxist);
    }

    public synchronized PassengerQuery getPassenger(Taxist taxist) {
        return taxists.get(taxist);
    }

    public void clear() {
        TAXI_MANAGER_INSTANCE = new TaxiManager();
    }

    public Taxist getTaxist(PassengerQuery passengerQuery) {
        for(Taxist taxist : taxists.keySet()) {
            if(taxists.get(taxist).equals(passengerQuery)) return taxist; // trebuet equals
        }
        throw new IllegalArgumentException();
    }

    public synchronized boolean changeTaxistLoc(Taxist taxist) {
        if (taxists.containsKey(taxist)) {
            PassengerQuery fromMap = taxists.remove(taxist);
            taxists.put(taxist, fromMap);
            return true;
        }
        return false;
    }

}
