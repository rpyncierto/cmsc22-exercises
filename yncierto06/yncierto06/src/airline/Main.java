/*******************************************************************************************************
 * This is a program that uses polymorphism to manage and display information about flights, bookings, *
 * and passenger details for an airline. It allows you to view these details and perform operations    *
 * such as retrieving information about flights and their associated passengers and so on.			   *
 * 																									   *
 * @author Reymond P. Yncierto																		   *
 * @created 2022-23-12 23:23																		   *
 * @finished 2022-25-12 18:36																		   *
 *******************************************************************************************************/

package airline;

import customer.*;

import java.time.ZoneId;
import java.time.*;
import booking.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 
 * 
 * 
 * References: 
 * 
 * Routes: https://www.flightradar24.com/
 * How to use routes reference:
 * Choose a route
 * - click on an airplane image
 * - note the 3-letter IATA code of origin and destination, as well as the average flight time
 * e.g. ORD Chicago to CDG Paris; average flight time 7:20)
 * 
 * 
 * Airport codes:  https://www.world-airport-codes.com/
 * How to use airport code reference:
 * Based on route: note the time zone for origin and duration of flight;
 * You may use the IATA 3-letter code for origin and destination.
 * - "Search for an airport": you may use the 3-letter IATA code as search string
 * - e.g. ORD for Chicago O'Hare International Airport, CDG for Charles De Gaulle International Airport
 * - Note the time zone
 * - e.g. America/Chicago and Europe/Paris)
 * 
 * Time library (you may use this to represent ETD/ETA sa ZonedDateTime objects)
 * https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html
 * https://mkyong.com/java8/java-8-zoneddatetime-examples/
 * 
 * Zone list
 * https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
 * 
 */

public class Main {
	
	public static void main(String[] args) {
		
		Airline airline = new Airline("American Airlines");
		
		Passenger passenger1 = new Passenger(1, "Grant", "Hugh", "Mungo", 66, Passenger.MALE, Passenger.ELITE);
		passenger1.viewState();
		
		Passenger passenger2 = new Passenger(2, "Roberts", "Julia", "Fiona", 54, Passenger.FEMALE, Passenger.PRESTIGE);
		passenger2.viewState();
		
		Passenger passenger3 = new Passenger(3, "Perry", "Katy", "Hudson", 37, Passenger.MALE, Passenger.NORMAL);
		passenger3.viewState();
		
		Passenger passenger4 = new Passenger(4, "Kinnear","Greg", "Buck", 59, Passenger.MALE, Passenger.ELITE);
		passenger4.viewState();
		
		Passenger passenger5 = new Passenger(5, "Swift", "Taylor", "Alison", 32, Passenger.FEMALE, Passenger.NORMAL);
		passenger5.viewState();
		
		
	      DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM uuuu");
	      LocalDateTime localT = LocalDateTime.of(2022, Month.AUGUST, 22, 14, 30);
	      ZonedDateTime t1 = localT.atZone(ZoneId.of("Asia/Seoul"));
	      //flight duration = 12 hours
	      ZonedDateTime t2 = t1.withZoneSameInstant(ZoneId.of("America/Bahia_Banderas")).plusHours(12);
		
		Flight flight1 = new Flight(1,"AA280", "ICN", t1, "DFW", t2);
		flight1.viewState();
		
		
	      localT = LocalDateTime.of(2022, Month.SEPTEMBER, 3, 1, 10);
	      t1 = localT.atZone(ZoneId.of("America/Matamoros"));
	      //flight duration = 2 hours and 20 mins
	      t2 = t1.withZoneSameInstant(ZoneId.of("America/New_York")).plusHours(2).plusMinutes(20);
		
		Flight flight2 = new Flight(2, "AA506", "MEX", t1, "MIA", t2);
		flight2.viewState();
	      localT = LocalDateTime.of(2022, Month.MAY, 17, 5, 10);
	      t1 = localT.atZone(ZoneId.of("America/Chicago"));
	      //flight duration = 7 hours and 20 mins
	      t2 = t1.withZoneSameInstant(ZoneId.of("Europe/Paris")).plusHours(7).plusMinutes(20);
		
		Flight flight3 = new Flight(3,"AA151", "ORD", t1, "CDG", t2);
		flight3.viewState();
		
		airline.addFlight(flight1);
		airline.addFlight(flight2);
		airline.addFlight(flight3);
		
		System.out.println("\nAirline processes booking for passenger...");
		airline.book(passenger1, flight1, Flight.BUSINESS);

		System.out.println("\nAirline processes booking for passenger...");
		airline.book(passenger2, flight1, Flight.ECONOMY);

		System.out.println("\nAirline processes booking for passenger...");
		airline.book(passenger3, flight2, Flight.ECONOMY);

		System.out.println("\nAirline processes booking for passenger...");
		airline.book(passenger4, flight2, Flight.ECONOMY);

		System.out.println("\nAirline processes booking for passenger...");
		airline.book(passenger5, flight3, Flight.ECONOMY);

		System.out.println("\nAirline processes booking for passenger...");
		airline.book(passenger5, flight2, Flight.ECONOMY);
		
		airline.showPassengerNamesPerFlight();
		airline.showMinMaxAgePerFlight();
		airline.showFlightPassengersCountByPassengerType(Passenger.ELITE);
		airline.showFlightPassengersCountByPassengerType(Passenger.PRESTIGE);
		airline.showFlightsCountPerPassenger();
		
		airline.showPassengerCountPerFlight();
		airline.showFlightPassengersCountBySeatClass(Flight.BUSINESS);
		airline.showFlightPassengersCountBySeatClass(Flight.ECONOMY);
		
		airline.showTopPassengerCountPerFlight(3);
		airline.showFlightsWithAgeFilter(Airline.FILTER_ABOVE, 65);
		airline.showFlightsWithAgeFilter(Airline.FILTER_BELOW, 5);
		
		airline.displayFlightAndPassengers(null); //show all; null for no exclusions
		airline.displayFlightAndPassengersExcludeSeatClass(Flight.ECONOMY);
		airline.displayFlightAndPassengers(new ArrayList<String>(Arrays.asList(Passenger.PRESTIGE,Passenger.ELITE)));
		
	}
	
}
