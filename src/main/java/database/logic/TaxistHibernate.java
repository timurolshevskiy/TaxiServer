package database.logic;

import com.scholota.taxi.PassengerQuery;
import com.scholota.taxi.Taxist;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Timur on 22.10.2015.
 */

@Entity
@Table(name = "user")
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

    public TaxistHibernate(Taxist taxist) {
        password = taxist.getPassword();
        email = taxist.getEmail();
        name = taxist.getName();
        phoneNumber = taxist.getPhoneNumber();
        model = taxist.getModel();
        color = taxist.getColor();
        carNumber = taxist.getCarNumber();
    }

    public PassengerQuery getPassengerQuery() {
        return passengerQuery;
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

    public synchronized void setPassenger(PassengerQuery p) {
        passengerQuery = p;
    }

    public synchronized PassengerQuery getPassenger() {
        return passengerQuery;
    }

}
