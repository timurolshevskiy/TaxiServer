import com.google.gson.Gson;
import com.scholota.taxi.PassengerQuery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Timur on 15.11.2015.
 */
public class MeetingServlet extends HttpServlet {

    TaxiManager tm = TaxiManager.getInstance();
    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("passenger_query");
        PassengerQuery passengerQuery = gson.fromJson(query, PassengerQuery.class);
        tm.removePair(passengerQuery);
    }
}
