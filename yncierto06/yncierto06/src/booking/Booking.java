package booking;

import customer.Passenger;

public class Booking {
    private Passenger passenger;
    private String flightClass;

    // constructor
    public Booking(Passenger passenger, String flightClass) {
        this.passenger = passenger;
        this.flightClass = flightClass;
    }

    // getter for the passenger's name
    public String getPassengerName() {
        return passenger.getFullName();
    }

    // getter for the passenger's age
    public int getAge(){
        return passenger.getAge();
    }

    // getter for the passenger's type
    public String getPassengerType() {
        return passenger.getType();
    }

    // getter for the flight's class/type
    public String getSeatClass() {
        return this.flightClass;
    }

    // getter for the passenger
    public Passenger getPassenger() {
        return this.passenger;
    }
}
