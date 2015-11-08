import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scholota.taxi.PassengerQuery;
import com.scholota.taxi.Taxist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Timur on 17.10.2015.
 */
public class PassengerServlet extends HttpServlet {

    private final TaxiManager taxiManager = TaxiManager.getInstance();
    private final Gson gson;

    PassengerServlet() {
        GsonBuilder gb = new GsonBuilder().setPrettyPrinting();
        gson = gb.create();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("passenger_query");
        PassengerQuery passengerQuery = gson.fromJson(query, PassengerQuery.class);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        Taxist taxist = taxiManager.getBest(passengerQuery);
        //Taxist taxist = new Taxist("pass", "emal", "name", 111, "mod", "col", "nemb");

        writer.print(gson.toJson(taxist));
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
