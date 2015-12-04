import com.google.gson.Gson;
import com.scholota.taxi.Taxist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Timur on 11.11.2015.
 */
public class RemoveTaxistServlet extends HttpServlet { //net mappinga

    Gson gson = new Gson();
    TaxiManager tm = TaxiManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonTaxist = req.getParameter("taxist");
        Taxist taxist = gson.fromJson(jsonTaxist, Taxist.class);
        tm.removeTaxist(taxist); // trebuet equals

        // ne dodelano
    }
}
