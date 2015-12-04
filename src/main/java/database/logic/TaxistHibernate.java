package database.logic;

import com.scholota.taxi.PassengerQuery;
import com.scholota.taxi.Taxist;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Timur on 22.10.2015.
 */

@Entity
@Table(name = "taxist")
public class TaxistHibernate {

    private PassengerQuery passengerQuery;

    private int id;
    private String password;
    private String email;
    private String name;
    private long phoneNumber;
    private String model;
    private String color;
    private String carNumber;

    private double price;
    private double rating;
    private int count;

    public TaxistHibernate() {}

    public TaxistHibernate(Taxist taxist) {
        password = taxist.getPassword();
        email = taxist.getEmail();
        name = taxist.getName();
        phoneNumber = taxist.getPhoneNumber();
        model = taxist.getModel();
        color = taxist.getColor();
        carNumber = taxist.getCarNumber();
        price = taxist.getPrice();
        rating = taxist.getRating();
        count = taxist.getCount();
    }

    public void setPassengerQuery(PassengerQuery passengerQuery) {
        this.passengerQuery = passengerQuery;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_taxist")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "phone_number")
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "car_number")
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "rating")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized void setPassenger(PassengerQuery p) {
        passengerQuery = p;
    }

    public synchronized PassengerQuery passenger() {
        return passengerQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxistHibernate)) return false;

        TaxistHibernate that = (TaxistHibernate) o;

        if (phoneNumber != that.phoneNumber) return false;
        if (passengerQuery != null ? !passengerQuery.equals(that.passengerQuery) : that.passengerQuery != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        return !(carNumber != null ? !carNumber.equals(that.carNumber) : that.carNumber != null);

    }

    @Override
    public int hashCode() {
        int result = passengerQuery != null ? passengerQuery.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (carNumber != null ? carNumber.hashCode() : 0);
        return result;
    }
}
