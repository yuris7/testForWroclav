package ua.beast.oun.rest.tests;

import org.testng.annotations.Test;
import ua.beast.oun.rest.models.Booking;
import java.io.IOException;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

    @Test
    public void testCreateBooking() throws IOException {
        Set<Booking> oldBookings = app.getSessionHelper().getBookings();
        app.getSessionHelper().createBooking();
        Set<Booking> newBookings = app.getSessionHelper().getBookings();
        assertEquals(newBookings.size() - 1, oldBookings.size());
    }
    @Test
    public void testUpdateBooking() throws IOException {
        Set<Booking> oldBookings = app.getSessionHelper().getBookings();
        app.getSessionHelper().updateBooking();
        Set<Booking> newBookings = app.getSessionHelper().getBookings();
        assertEquals(newBookings.size(), oldBookings.size());
    }
//TEST IS NOT VALID, DELETE endpoint provides Code 201 instead 204
    @Test
    public void testDeleteBooking() throws IOException {
        Set<Booking> oldBookings = app.getSessionHelper().getBookings();
        app.getSessionHelper().deleteBooking();
        Set<Booking> newBookings = app.getSessionHelper().getBookings();
        assertEquals(newBookings.size(), oldBookings.size() - 1);
    }
}