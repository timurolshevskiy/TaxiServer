import com.google.gson.Gson;
import com.scholota.taxi.Birdman;
import com.scholota.taxi.Taxist;
import database.dao.DaoFactory;
import database.dao.TaxistDao;
import database.logic.TaxistHibernate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Timur on 08.10.2015.
 */
public class RegistrationServlet extends HttpServlet {

    Gson gson = new Gson();
    TaxistDao dao = TaxiManager.dao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String reqString = req.getParameter("taxist");

        Taxist reqTaxist = gson.fromJson(reqString, Taxist.class);
        PrintWriter writer = resp.getWriter();
        try {
            List<TaxistHibernate> allTaxists = dao.getAllTaxists();
            for (TaxistHibernate temp : allTaxists) {
                if (temp.getEmail().equals(reqTaxist.getEmail())) {
                    writer.print(gson.toJson("email exists"));
                    return;
                }
            }
            TaxistHibernate th = new TaxistHibernate(reqTaxist);
            dao.addTaxist(th);
            writer.print(gson.toJson("ok"));
        } catch (SQLException e) {
            //writer.print(gson.toJson("FAIL " + e.getMessage()));
        }
        writer.flush();
    }
}


