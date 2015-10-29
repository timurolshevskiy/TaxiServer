import com.google.gson.Gson;
import com.scholota.taxi.Taxist;
import database.dao.DaoFactory;
import database.logic.TaxistHibernate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Timur on 08.10.2015.
 */
public class RegistrationServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String reqString = req.getParameter("taxist");
        Taxist reqTaxist = gson.fromJson(reqString, Taxist.class);
        PrintWriter writer = resp.getWriter();
//        try {
//            DaoFactory.getInstance().getTaxistDao().addTaxist(new TaxistHibernate(reqTaxist));
//            writer.print(gson.toJson("ZAREGANO"));
//        } catch (SQLException e) {
//            writer.print(gson.toJson("FAIL " + e.getMessage()));
//        }
        writer.print("privet");
        writer.flush();
    }
}
