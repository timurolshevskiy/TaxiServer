import com.google.gson.Gson;
import com.scholota.taxi.PassengerQuery;
import com.scholota.taxi.Taxist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Timur on 11.11.2015.
 */
public class RefreshLocPassenger extends HttpServlet {

    Gson gson = new Gson();
    TaxiManager tm = TaxiManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String query = req.getParameter("passenger_query");
        PassengerQuery passengerQuery = gson.fromJson(query, PassengerQuery.class);
        Taxist taxist = tm.getTaxist(passengerQuery);

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(taxist));
        writer.flush();
    }
}
