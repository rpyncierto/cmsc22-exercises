package booking;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Flight implements Business, Economy {
    private int id;
    private String code, origin, finalETD, destination, finalETA;
    private ZonedDateTime rawETD, rawETA;
    private long duration;
    private DateTimeFormatter flightFormat = DateTimeFormatter.ofPattern("HHmm, dd MMM uuuu zzz");
    private ArrayList<Booking> bookings;

    // constructor
    public Flight(int id, String code, String origin, ZonedDateTime t1, String destination, ZonedDateTime t2) {
        this.bookings = new ArrayList<Booking>();
        this.id = id;
        this.code = code;
        this.origin = origin;
        this.destination = destination;
        this.rawETD = t1;
        this.rawETA = t2;
        this.duration = Duration.between(t1, t2).toHours();
        this.finalETD = flightFormat.format(t1);
        this.finalETA = flightFormat.format(t2);
    }

    // add booking to a flight
    public void addBooking(Booking book) {
        bookings.add(book);
    }

    // displays information about the flights
    public void viewState() {
        System.out.println("========== FLIGHT INFORMATION =============");
        System.out.println("Flight ID:\t" + this.id);
        System.out.println("Flight info:\tflight code " + this.code + "; " + this.origin + " to " + this.destination);
        System.out.println("Departure:\t" + this.finalETD + " or " + this.rawETD);
        System.out.println("Arrival:\t" + this.finalETA + " or " + this.rawETA);
        System.out.println("Duration:\t" + this.duration + " hours");
        System.out.println("===========================================");
    }

    // getter for the flight's code
    public String getCode() {
        return this.code;
    }

    // getter for the flight's booking
    public ArrayList<Booking> getBooking() {
        return this.bookings;
    }

    // getter for the flight's id
    public Integer getId() {
        return this.id;
    }
}
