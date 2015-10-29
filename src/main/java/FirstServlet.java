import com.google.gson.Gson;
import com.scholota.taxi.Birdman;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Timur on 19.09.2015.
 */
public class FirstServlet extends HttpServlet{

    //final List<Integer> numbers = new ArrayList<Integer>();
    final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        //resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String answer = gson.toJson(new Birdman(1));
        writer.print(answer);
        /*writer.append("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<title>App</title>" +
                "</head>" +
                "<body>" +
                "<h2>Nastroyenie prosto super</h2>" +
                "</body>" +
                "</html>");*/
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Birdman answer = new Birdman(Integer.parseInt(req.getParameter("age")) + 1);
        String post = req.getParameter("param");
        Birdman birdman = gson.fromJson(post, Birdman.class);
        int age = birdman.getAge();
        //System.out.println(post);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        //if(answer != null)
            //writer.print(gson.toJson(answer));
        //else
       writer.print(gson.toJson(new Birdman(age + 2)));
       writer.flush();
    }

}
