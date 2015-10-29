import com.google.gson.Gson;
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
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
//        TaxistHibernate taxistHibernate = new TaxistHibernate(); //db.get(login);
//        tm.addTaxist(taxistHibernate);
//
//        while(taxistHibernate.getPassenger() == null);

        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        pw.print(gson.toJson("ddos"));
        pw.flush();
    }
}
