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
import java.util.List;

/**
 * Created by Timur on 25.11.2015.
 */
public class LoginServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        PrintWriter pw = resp.getWriter();
        List<TaxistHibernate> taxists = null;
        Taxist result = null;
        try {
            taxists = DaoFactory.getInstance().getTaxistDao().getAllTaxists();

            for (TaxistHibernate temp : taxists) {
                if (temp.getEmail().equals(email) && temp.getPassword().equals(password)) {
                    result = new Taxist(temp.getPassword(),
                            temp.getEmail(),
                            temp.getName(),
                            temp.getPhoneNumber(),
                            temp.getModel(),
                            temp.getColor(), temp.getCarNumber());
                    result.setPrice(temp.getPrice());
                    result.setCount(temp.getCount());
                    result.setRating(temp.getRating());
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result == null) result = new Taxist("1", email + "no", "1", 1, "1", "1", "1");

        pw.print(gson.toJson(result));
    }
}
