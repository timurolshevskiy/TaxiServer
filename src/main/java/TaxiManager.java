import com.scholota.taxi.PassengerQuery;
import com.scholota.taxi.Taxist;
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
    private static final TaxiManager TAXI_MANAGER_INSTANCE = new TaxiManager();

    //private Map<Taxist, LatLng> taxists = new Map<Taxist, LatLng>();
    private Map<String, List<Taxist>> regions = new HashMap<String, List<Taxist>>();
    private Map<Taxist, PassengerQuery> taxists = new HashMap<Taxist, PassengerQuery>();

    private TaxiManager() {
        Taxist taxist = new Taxist();
        taxist.setLatLng(50, 36);
        taxist.setRegion("Kharkiv");
        regions.put("Kharkiv", new ArrayList<Taxist>());
        regions.get("Kharkiv").add(taxist);
    }

    public static synchronized TaxiManager getInstance() {
        return TAXI_MANAGER_INSTANCE;
    }

    public synchronized Taxist getBest(PassengerQuery passengerQuery) {
        //! если нет региона

        List<Taxist> taxistsInRegion = regions.get(passengerQuery.getRegion());
        Taxist best = taxistsInRegion.get(0);
        double bestDistance = calculateDistance(best.getLat(), best.getLng(), passengerQuery.getLat(), passengerQuery.getLng());
        for(Taxist tx : taxistsInRegion) {
            double tempDistance = calculateDistance(best.getLat(), best.getLng(), passengerQuery.getLat(), passengerQuery.getLng());
            if (bestDistance > tempDistance) {
                best = tx;
                bestDistance = tempDistance;
            }
        }
        taxists.put(best, passengerQuery);
        taxistsInRegion.remove(best);
        //notifyAll();
        //! если список пустой
        return best;
    }

    static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lng2-lng1);
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
        tx.add(taxist);
    }

    public synchronized void removeTaxist(Taxist taxist) {
        taxists.remove(taxist);
    }

    public PassengerQuery getPassenger(Taxist taxist) {
        return taxists.get(taxist);
    }

}
