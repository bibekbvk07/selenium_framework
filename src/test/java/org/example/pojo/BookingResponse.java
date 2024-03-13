package org.example.pojo;

public class BookingResponse {
    private int bookingid;
    private Booking booking;

    public int getBookingid() {
        return bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
