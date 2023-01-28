package airline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import booking.Booking;
import booking.Flight;
import customer.Passenger;

public class Airline {
    private String name;
    private HashMap<String, Flight> flightMap = new HashMap<String, Flight>();
    private HashMap<String, ArrayList<Flight>> bookMap = new HashMap<String, ArrayList<Flight>>();
    public static final String FILTER_ABOVE = "above";
    public static final String FILTER_BELOW = "below";

    // constructor
    public Airline(String name) {
        this.name = name;
    }

    // add flight
    public void addFlight(Flight flight) {
        flightMap.put(flight.getCode(), flight);
    }

    // book a flight
    public void book(Passenger passenger, Flight flight, String flightClass) {
        // check if key is not existing
        if (!bookMap.containsKey(flight.getCode())) {
            // create new arraylist
            ArrayList<Flight> flightList = new ArrayList<Flight>();
            // add flight to arraylist
            flightList.add(flight);
            // add arraylist to hashmap
            bookMap.put(flight.getCode(), flightList);
        }
        // do not uncomment this, this is only used for trials while doing the exercise
        // else {
        //     // get arraylist from hashmap
        //     ArrayList<Flight> flightList = bookMap.get(flight.getCode());
        //     // add flight to arraylist
        //     flightList.add(flight);
        //     // add arraylist to hashmap
        //     bookMap.put(flight.getCode(), flightList);
        // }
        Booking book = new Booking(passenger, flightClass); // pass the passenger and flight class to the booking constructor
        flight.addBooking(book); // add a booking to the flight
        passenger.updateBooking(flight); // update the flights booked by the passenger
        System.out.println("Done booking process for " + passenger.getFullName() + "[" + passenger.getType() + "] for flight " + flight.getCode() + "(" + flightClass + ")\n");
    }

    // displays the passengers per flight
    public void showPassengerNamesPerFlight() {
        System.out.println("**************************\nFlights and passenger names");
        for (String key : bookMap.keySet()) { // iterate the key in the booking map
            System.out.println("Flight: " + key);
            System.out.println("\tPassengers:");
            for (Flight flight:bookMap.get(key)) { // iterate the flights associated with a particular key
                int i = 1;
                for (Booking book: flight.getBooking()) { // iterate the bookings of a particular flight
                    System.out.println("\t" + i + ". " + book.getPassengerName());
                    i++;
                }
            }
        }
        System.out.println("**************************\n");
    }

    // displays the minimum and maximum age of the passengers in a flight
    public void showMinMaxAgePerFlight() {
        System.out.println("**************************\nFlights and min/max age");
        int max = 0, min = 0;
        for (String key : bookMap.keySet()) { // iterate key in the booking map
            System.out.println("Flight: " + key);
            for (Flight flight:bookMap.get(key)) { // iterate the flights associated with a particular key
                ArrayList<Integer> ages = new ArrayList<Integer>();
                for (Booking book: flight.getBooking()) { // iterate the bookings of a particular flight
                    ages.add(book.getAge()); // get the age of the passenger who booked the flight and add it to the array of ages
                }
                max = Collections.max(ages); // get the maximum among the age element in the ages array
                min = Collections.min(ages); // get the minimum among the age element in the ages array
            }
            System.out.println("\tMinimum Age: " + min);
            System.out.println("\tMaximum Age: " + max);
        }
        System.out.println("**************************\n");
    }

    // display the passenger count with filter of their type
    public void showFlightPassengersCountByPassengerType(String passengerType) {
        System.out.println("**************************\nFlights and passenger count (" + passengerType + ")");
        for (String key : bookMap.keySet()) { // iterate key in the booking map
            int count = 0;
            System.out.print("Flight: " + key + ";");
            for (Flight flight:bookMap.get(key)) { // iterate the flights associated with a particular key
                for (Booking book: flight.getBooking()) { // iterate the bookings of a particular flight
                    if (book.getPassengerType().equals(passengerType)) { // check if the type of the passenger who own the booking is the same with the filter passenger type that we are finding
                        count++;
                    }
                }
                System.out.println("\t" + "Passengers count (" + passengerType + "): " + count);
            }
        }
        System.out.println("**************************\n");
    }

    // dislay the flight bookings count of the passengers
    public void showFlightsCountPerPassenger() {
        System.out.println("**************************\nFlights per passenger");
        ArrayList<Passenger> passengers = new ArrayList<Passenger>(); // array list to store the passengers
        for (String key : bookMap.keySet()) { // iterate the key
            for (Flight flight:bookMap.get(key)) { // iterate the flights
                for (Booking book: flight.getBooking()) { // iterate the bookings
                    if(!passengers.contains(book.getPassenger())) { // if the passenger who own the booking is still not in the passengers array, add it
                        passengers.add(book.getPassenger());
                    }
                }
            }
        }
        for(Passenger passenger:passengers) { // iterate the passengers
            System.out.print("Passenger: " + passenger.getFullName() + ";   ");
            System.out.print("\tFlight bookings: " + passenger.getFlights().size() + "\n");
        }
        System.out.println("**************************\n");
    }

    // display the passenger count per flight
    public void showPassengerCountPerFlight() {
        System.out.println("**************************\nFlights and passenger count");
        for (String key : bookMap.keySet()) { // iterate the key
            System.out.print("Flight: " + key + ";");
            for (Flight flight:bookMap.get(key)) { // iterate the flights
                System.out.println("\tPassenger count: " + flight.getBooking().size()); // get the bookings of that particular flight
            }
        }
        System.out.println("**************************\n");
    }

    // display the top i flights with highest number of passengers
    public void showTopPassengerCountPerFlight(int i) {
        System.out.println("**************************\nTop " + i + " flights with highest number of passengers");
        HashMap<Integer, String> passengerFlight = new HashMap<Integer, String>(); // create a hashmap to store the flight code and the number of passengers
        for (String key : bookMap.keySet()) { // iterate the key
            for (Flight flight:bookMap.get(key)) { // iterate the flights
                passengerFlight.put(flight.getBooking().size(), key); // get the number of bookings on that flight
            }
        }
        // sort the hashmap (based on https://www.interviewkickstart.com/learn/sort-hashmap and https://stackoverflow.com/questions/12184378/sorting-linkedhashmap)
        Map<Integer, String> sortedPassengerFlight = passengerFlight.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        // print the sorted hashmap
        int count = 0;
        for (Map.Entry<Integer, String> entry : sortedPassengerFlight.entrySet()) { // iterate the sorted linked LinkedHashMap
            if (count < i) {
                System.out.println("Passenger count: " + entry.getKey() + ": " + entry.getValue());
                count++;
            }
        }
        System.out.println("**************************\n");
    }

    // display the flights with passengers whose age is above or below the limit
    public void showFlightsWithAgeFilter(String filterType, int limit) {
        System.out.println("**************************\nList  of flights with passengers whose age is " + filterType + " " + limit + ":");
        for (String key : bookMap.keySet()) { // iterate the key
            int count = 0;
            for (Flight flight:bookMap.get(key)) { // iterate the flights
                for (Booking book: flight.getBooking()) { // iterate the bookings
                    if (filterType.equals("above")) { // limit type is above
                        if (book.getAge() > limit) { // check if the passenger who owned the booking exceed the limit
                            count++;
                        }
                    } else if (filterType.equals("below")) { // limit type is below
                        if (book.getAge() < limit) { // // check if the passenger who owned the booking not reach the limit
                            count++;
                        }
                    }
                }
            }
            if (count > 0) {
                System.out.println("Flight: " + key + ";   " + "count: " + count);
            }
        }
        System.out.println("End of list.\n**************************\n");
    }

    // display passenger count of a particular flight class
    public void showFlightPassengersCountBySeatClass(String flightClass) {
        System.out.println("**************************\nFlights and seat class count");
        for (String key : bookMap.keySet()) { // iterate keys
            int count = 0;
            for (Flight flight:bookMap.get(key)) { // iterate flights
                for (Booking book: flight.getBooking()) { // iterate bookings
                    if (book.getSeatClass().equals(flightClass)) { // check if the booking type of passenger equals to the flight type we are finding
                        count++;
                    }
                }
            }
            if (count > 0) {
                System.out.println("Flight: " + key + ";   " + "Passenger count (" + flightClass + "): " + count);
            }
        }
        System.out.println("**************************\n");
    }

    // display the flights and passengers in alphabetical order which supports exclusion of passengers
    public void displayFlightAndPassengers(ArrayList<String> excludedPassenger) {
        System.out.print("**************************\nList of flights and passengers (");
        HashMap<Integer, ArrayList<Passenger>> list = new HashMap<Integer, ArrayList<Passenger>>(); // hashmap to store the flight id and its list of passengers
        if (excludedPassenger == null) {
            System.out.println("in alphabetical order - last name)");
        } else {
            System.out.println("excluding " + excludedPassenger + ")");
        }
        for (String key : bookMap.keySet()) { // iterate keys
            for (Flight flight:bookMap.get(key)) { // iterate flights
                ArrayList<Passenger> passengers = new ArrayList<Passenger>(); // array list to store passengers
                for (Booking book: flight.getBooking()) { // iterate bookings
                    if (excludedPassenger == null) { // if exclusion is null, means we are not excluding any passenger
                        passengers.add(book.getPassenger()); // add the passenger to the passengers
                    } else { // if with exclusion
                        if (!excludedPassenger.contains(book.getPassenger().getType())) { // check if the current owner of the booking is included in the excluded passengers
                            passengers.add(book.getPassenger());
                        }
                    }
                }
                Collections.sort(passengers, new Comparator<Passenger>() { // sort passengers using their last names
                    @Override
                    public int compare(Passenger p1, Passenger p2) {
                        return p1.getLastName().compareTo(p2.getLastName());
                    }
                });
                list.put(flight.getId(), passengers); // put the flight id and the sorted passengers in the hashmap
            }
        }
        // display the flight and passenger
        for (Integer key : list.keySet()) { // iterate keys
            System.out.println("\nFlight ID: " + key + "\nPassengers: ");
            int count = 1;
            for (Passenger passenger: list.get(key)) { // iterate flights
                System.out.println("\t" + count + ". " + passenger.getFullName() + " (" + passenger.getType() + ")");
                count++;
            }
        }
        System.out.println("\nEnd of list.\n**************************\n");
    }

    // display the flights and passengers in alphabetical order which supports exclusion of passengers booked in a specific flight class
    public void displayFlightAndPassengersExcludeSeatClass(String excludedFlight) {
        System.out.println("**************************\nList of flights and corresponding passengers (excluding " + excludedFlight + "):");
        int count = 1;
        for (String key : bookMap.keySet()) { // iterate keys
            for (Flight flight:bookMap.get(key)) { // iterate flights
                HashMap<Passenger, String> passengers = new HashMap<Passenger, String>(); // map of passenger and their seat class
                for (Booking book: flight.getBooking()) { // iterate bookings
                    if (!book.getSeatClass().equals(excludedFlight)) { // check if the booked flight type of the passenger is the same with what we're looking for
                        passengers.put(book.getPassenger(), book.getSeatClass());
                    }
                }
                Map<Passenger, String> sortedPassengers = passengers.entrySet().stream() // sort the hashmap using the key's last name
                        .sorted(Map.Entry.comparingByKey(Comparator.comparing(Passenger::getLastName)))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                System.out.println(count + ". Flight: " + flight.getCode());
                for (Passenger passenger: sortedPassengers.keySet()) { // iterate passengers
                    System.out.println("\t" + passenger.getFullName() + " (" + sortedPassengers.get(passenger) + ")");
                }
                count++;
            }
        }
        System.out.println("\nEnd of list.\n**************************\n");
    }
}