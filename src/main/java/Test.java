import com.google.gson.Gson;
import com.scholota.taxi.Taxist;

/**
 * Created by Timur on 28.10.2015.
 */
public class Test {

     static Gson gson = new Gson();

    public static void main(String[] args) {
        Taxist tx = new Taxist("pass", "emil", "name", 6, "model", "color", "number");
        String json = gson.toJson(tx);
        //json = json.replace("6", "\"9\"");
        Taxist txResult = gson.fromJson(json, Taxist.class);
        System.out.println(txResult.getPhoneNumber());
    }

}
