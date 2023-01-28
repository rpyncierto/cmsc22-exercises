package customer;

import java.util.ArrayList;
import java.util.HashMap;

import booking.Flight;

public class Passenger implements Normal, Elite, Prestige {
    private int id, age;
    private String lastName, firstName, middleName, gender, passengerClass;
    private ArrayList<Flight> flights;
    public final static String MALE = "Male";
    public final static String FEMALE = "Female";

    // constructor
    public Passenger(int id, String lastName, String firstName, String middleName, int age, String gender, String type) {
        this.flights = new ArrayList<Flight>(); // initialize the array list
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
        this.gender = gender;
        this.passengerClass = type;
    }

     // prints out the passenger information
    public void viewState() {
        System.out.println("========== PASSENGER INFORMATION =============");
        System.out.println("ID:\t" + this.id);
        System.out.println("Name:\t" + this.getFullName());
        System.out.println("Gender:\t" + this.gender);
        System.out.println("Age:\t" + this.age);
        System.out.println("Type:\t" + this.passengerClass);
        System.out.println("===========================================");
    }

    // setter for flight bookings of a passenger
    public void updateBooking(Flight flight) {
        this.flights.add(flight);
    }

    // getter for the passenger's full name
    public String getFullName() {
        return this.lastName + ", " + this.firstName + " " + this.middleName;
    }

    // getter for the passenger's type
    public String getType() {
        return this.passengerClass;
    }

    // getter for the passenger's age
    public int getAge() {
        return this.age;
    }
    
    
    // getter for the flights booked by the passenger
    public HashMap<String, Flight> getFlights() {
        HashMap<String, Flight> flightMap = new HashMap<String, Flight>();
        for (Flight flight : this.flights) {
            flightMap.put(flight.getCode(), flight);
        }
        return flightMap;
    }

    // getter for the passenger's last name
    public String getLastName() {
        return this.lastName;
    }
}
