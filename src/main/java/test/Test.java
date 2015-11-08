package test;

import com.google.gson.Gson;
import com.scholota.taxi.Taxist;
import database.dao.DaoFactory;
import database.logic.TaxistHibernate;

import java.sql.SQLException;
import java.util.Timer;

/**
 * Created by Timur on 28.10.2015.
 */
public class Test {

    public static void main(String[] args) {
        Worker worker = new Worker();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runner(worker, false)).start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runner(worker, true)).start();
    }

}
