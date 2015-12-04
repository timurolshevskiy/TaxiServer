import com.google.gson.Gson;
import com.scholota.taxi.Taxist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Timur on 14.11.2015.
 */
public class RefreshLocTaxist extends HttpServlet {

    Gson gson = new Gson();
    TaxiManager tm = TaxiManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String query = req.getParameter("taxist");
        Taxist taxist = gson.fromJson(query, Taxist.class);
        boolean result = tm.changeTaxistLoc(taxist);

        PrintWriter pw  = resp.getWriter();
        pw.print(result);
    }
}
