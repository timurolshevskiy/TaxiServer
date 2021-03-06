import com.google.gson.Gson;
import com.scholota.taxi.PassengerQuery;
import com.scholota.taxi.Taxist;
import database.dao.DaoFactory;
import database.logic.TaxistHibernate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Timur on 22.10.2015.
 */
public class TaxistServlet extends HttpServlet {

    TaxiManager tm = TaxiManager.getInstance();
    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String jsonTaxist = req.getParameter("taxist");
        Taxist taxist = gson.fromJson(jsonTaxist, Taxist.class);
        tm.addTaxist(taxist);
        PassengerQuery answer = null;

        while(answer == null) {
            answer = tm.getPassenger(taxist);
        }

        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        pw.print(gson.toJson(answer));
        //pw.print(gson.toJson(new PassengerQuery("start", "finish", 10, 1.2, 1.2, "Parasha")));
        pw.flush();
        //tm.clear();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
