package ua.beast.oun.rest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
public class Booking {
    private Integer id;
    private Integer totalprice;
    private Integer bookingid;
    private String firstname;
    private String lastname;
    private String additionalneeds;
    private boolean depositpaid;
    private Map<String, String> bookingdates;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalprice() {

        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {

        this.totalprice = totalprice;
    }

    public Integer getBookingid() {

        return bookingid;
    }

    public void setBookingid(Integer bookingid) {

        this.bookingid = bookingid;
    }

    public void setBookingdates(Map<String, String> bookingdates) {

        this.bookingdates = bookingdates;
    }

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public String getAdditionalneeds() {

        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {

        this.depositpaid = depositpaid;
    }
 //Example of Influence interface instead Setters
    public Booking withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingid=" + bookingid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        return bookingid == booking.bookingid;
    }

    public Map getBookingdates() {
        return bookingdates;
    }

    public void setBookingDates(Map bookingdates) {

        this.bookingdates = bookingdates;
    }
}