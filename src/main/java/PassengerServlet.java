import com.google.gson.Gson;
import com.scholota.taxi.PassengerQuery;

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
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("passenger_query");
        PassengerQuery passengerQuery = gson.fromJson(query, PassengerQuery.class);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        List<String> taxists = taxiManager.getTaxists();
        String answerString = taxists.get(0) + "for" + passengerQuery.getQueryStart();
        writer.print(gson.toJson(answerString));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
