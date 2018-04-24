package ua.beast.oun.rest.appmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import ua.beast.oun.rest.models.AuthenticationToken;
import ua.beast.oun.rest.models.Booking;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class SessionHelper {
  AuthenticationToken authToken;
  private ApplicationManager app;

  public SessionHelper(ApplicationManager app) {
    this.app = app;
  }

  public SessionHelper() {

  }

  public void updateBooking() throws JsonProcessingException {
      Map bookingdates = new HashMap();
      bookingdates.put("checkin", "2013-02-23");
      bookingdates.put("checkout", "2014-11-23");

      Booking booking = new Booking();
      booking.setFirstname("NotSally");
      booking.setLastname("NotBrown");
      booking.setTotalprice(111);
      booking.setDepositpaid(true);
      booking.setAdditionalneeds("NotBreakfast");
      booking.setBookingDates(bookingdates);

      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(booking);

      RestAssured.baseURI = "https://restful-booker.herokuapp.com";
      RequestSpecification request = given();
      request.header("Content-Type", "application/json")
              .cookie("token", authToken.getToken());
      request.body(json);
      Response response = request.put("/booking/7");
      int statusCode = response.getStatusCode();
      assertEquals(statusCode, 200);
  }

  public void deleteBooking() {

      RestAssured.baseURI = "https://restful-booker.herokuapp.com";
      RequestSpecification request = given()
              .header("Content-Type", "application/json")
              .cookie("token", authToken.getToken());
      Response response = request.delete("/booking/1");
      int code = response.statusCode();
      System.out.println(response.getStatusLine());
      Assert.assertEquals(code, 204); //DELETE endpoint provides Code 201 instead 204!!!!
  }

  public Set<Booking> getBookings() throws IOException {
      String json = RestAssured.get(
              "https://restful-booker.herokuapp.com/booking").asString();
      JsonElement parsed = new JsonParser().parse(json);
      Set<Booking> bookingsList = new Gson()
              .fromJson(parsed.getAsJsonArray(), new TypeToken<Set<Booking>>() {
      }.getType());
      return bookingsList;
  }

  public void createBooking() throws IOException {
      Map bookingdates = new HashMap();
      bookingdates.put("checkin", "2013-02-23");
      bookingdates.put("checkout", "2014-10-23");

      Booking booking = new Booking();
      booking.setFirstname("Sally");
      booking.setLastname("Brown");
      booking.setTotalprice(111);
      booking.setDepositpaid(true);
      booking.setAdditionalneeds("Breakfast");
      booking.setBookingDates(bookingdates);

      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(booking);

      RestAssured.baseURI = "https://restful-booker.herokuapp.com";
      RequestSpecification request = given();
      request.header("Content-Type", "application/json");
      request.body(json);
      Response response = request.post("/booking");
      int statusCode = response.getStatusCode();
      assertEquals(statusCode, 200);
  }
}
