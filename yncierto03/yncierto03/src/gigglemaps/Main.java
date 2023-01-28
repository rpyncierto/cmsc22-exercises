/********************************************************************************************************************************************
 * Good day Ma'am,																															*
 *																																			*
 * I apologize for my late submission. I had not been feeling well for the past weeks, I was constantly dizzy and had headaches, which is 	*
 * why I decided to take a break and step away from acads for the time being because I had a feeling that my body would collapse at any   	*
 * time since I am going back and forth from home and campus. I also apologize for taking so long to complete this exercise; I did   		*
 * not want to complete it just for submission, but also for learning, so I was constantly reading about this concept in order for it to	*
 * instill in me since I am aware that this is a very important concept to learn. I accept full responsibility and am willing to accept     *
 * deductions. I appreciate your assistance and consideration. Please accept my heartfelt apologies. Thank you and stay safe!		     	*
 *******************************************************************************************************************************************/

/*******************************************************************************************************************************************
 * This is an implementation of Giggle maps, employed using abstraction, which help users find nearby restaurants and receive              *
 * recommendations based on user funds, location, restaurant capacity, and ratings. It keeps track of the user's current location          *
 * and record previous locations and restaurants visited.                                                                                  *
 *                                                                                                                                         *
 *                                                                                                                                         *
 * @author Reymond P. Yncierto																											   *
 * @created_date 2022-10-06 11:57																										   *
 * @finished_date 2022-11-21 22:22																									       *
 *																																		   *
 *******************************************************************************************************************************************/

package gigglemaps;

/**
 *
 * CMSC 22: Introduction to Object-Oriented Programming
 * Laboratory exercise
 * Topic: Abstraction
 *
 * Giggle maps has many features and among these is the ability to assist its users find nearby restaurants and
 * get recommendations based on the user funds and several other factors.  Giggle maps keeps track of the user’s
 * current location, and stores the history of locations visited.
 *
 * A location record has the following: location id and location name.
 *
 * Everytime a giggle maps user record is created, the user  record id is set, and relevant basic information is
 * specified.  Relevant basic information are as follows: user’s name, number of guests, money in the digital wallet,
 * and preferred restaurant rating.  A giggle maps user may view a list of nearby restaurants based on its current
 * location.  Not all of the restaurants are suitable for the user, however.  It depends on the combination of user
 * funds, restaurant capacity, and restaurant rating (i.e. rating is measured by the number of stars wherein higher
 * numbers correspond to higher preference).  A user can afford to eat in a restaurant if the money in the digital
 * wallet is enough to pay for the total cost.  The total cost is obtained by multiplying the number of guests by the
 * restaurant’s cost (per person). To know if the restaurant is available, its capacity should be greater than the
 * number of guests. The user also gets information about whether the restaurant’s rating falls short of the user’s
 * preference.
 *
 * The user may then decide whether to reserve or not. When reserving, the user specifies the corresponding item
 * number of the restaurant as shown in the search results. In order for reservation to succeed, the user should be
 * able to afford the restaurant’s rate.  This is computed by multiplying the user’s number of guests by the
 * restaurant's cost per person, and then comparing the result to the amount in the user’s digital wallet. The
 * restaurant must also be able to accommodate based on its current capacity. The user also needs to pay for the
 * cost of food upon reservation.  The restaurant’s capacity is also reduced upon reservation, also based on the
 * user’s guest count.  Note that a low restaurant rating does not prevent reservation. However, a warning is
 * displayed (i.e. making a reservation in spite of the low stars).  When viewing a user record, the following are
 * shown: user basic information, locations visited, current location, restaurant/s successfully reserved, and
 * current restaurant occupied (empty if not applicable).
 *
 * A restaurant record initially has the following: restaurant id, name, rating, capacity, cost per person, and
 * sales (i.e. no sales or 0 amount at the start).  A restaurant can be considered “near” certain locations and
 * keeps a record of these locations (can belong to one or more locations a.k.a “scope”).  Whenever a restaurant is
 * reserved by a giggle maps user, the restaurant’s capacity is reduced by the number of guests, and the cost of food
 * is recorded as sales. The cost of food is computed by multiplying the number of guests by the restaurant’s rate.
 * The restaurant keeps track of the last reservation received.  When viewing a restaurant record, the following are
 * shown: restaurant’s basic information, total sales, a list of locations where it is considered to be near, list of
 * user reservations, and last reservation received.
 *
 *
 * (c) Institute of Computer Science, CAS, UPLB
 *
 * @date 2022-09-06 08:00
 *
 */
public class Main {

	public static void main(String[] args) {

		/* First, create location records.
		 *
		 * When creating Location objects, the following are set:
		 *  location id, location name
		 */
		Location lb1 = new Location(1, "Upper LB");
		Location lb2 = new Location(2, "Downtown LB");
		Location lb3 = new Location(3, "Lower LB");

		Location bay1 = new Location(4, "Upper Bay");
		Location bay2 = new Location(5, "Bay proper");
		Location bay3 = new Location(6, "Lower Bay");

		/* Next, create Giggle Maps users.
		 *
		 * When creating User objects, the following are specified
		 *  (in the same order as it appears in the code below):
		 *  user id, user's name, no. of guests, money in digital wallet,
		 *  budget per person, and preferred restaurant rating
		 */
		User user1 = new User(1, "Mylah", 4, 5000f, 4);
		User user2 = new User(2, "Meg", 3, 10000f, 4);
		User user3 = new User(3, "Marga", 2, 8000f, 4);


		/* Finally, create restaurant records.
		 *
		 * When creating Restaurant objects, the following are specified
		 *  (in the same order as it appears in the code below):
		 *  restaurant id, restaurant name, rating, capacity, cost per person
		 */
		Restaurant restaurant1 = new Restaurant(1, "Spice jar", 4, 10, 250f);
		restaurant1.addLocation(lb2);

		Restaurant restaurant2 = new Restaurant(2, "MedyoOK Cafe", 2, 3, 100f);
		restaurant2.addLocation(lb3);
		restaurant2.addLocation(bay1);

		Restaurant restaurant3 = new Restaurant(3, "Pots&Kettles", 4, 20, 200f);
		restaurant3.addLocation(lb3);
		restaurant3.addLocation(bay1);

		/*
		 * Show the status of newly-created objects
		 */
		user1.viewState();
		user2.viewState();
		user3.viewState();
		lb1.viewState();
		lb2.viewState();
		lb3.viewState();
		bay1.viewState();
		bay2.viewState();
		bay3.viewState();
		restaurant1.viewState();
		restaurant2.viewState();
		restaurant3.viewState();

		/*
		 * Start describing user actions:
		 *
		 * User enters a location and searches for nearby restaurants
		 */
		user1.enters(lb2);
		user1.searchForRestaurants();

		/*
		 * User travels further and enters a location. Searches for nearby restaurants
		 */
		user1.enters(lb3);
		user1.searchForRestaurants();

		/*
		 * User tries to make a reservation in the first restaurant
		 * in the list (of search results)
		 */
		user1.reserve(1);


		/*
		 * Show the status of objects
		 */
		user1.viewState();

		/*
		 * User travels further and enters a location. Searches for nearby restaurants
		 */
		user1.enters(bay1);
		user1.searchForRestaurants();
		/*
		 * User tries to make a reservation in the second restaurant
		 * in the list (of search results)
		 */
		user1.reserve(2);


		/*
		 * User tries to make a reservation in the first restaurant
		 * in the list (of search results)
		 */
		user1.reserve(1);

		/*
		 * Another user enters a location, searches for nearby restaurants,
		 * and tries to make a reservation
		 */
		user2.enters(bay1);
		user2.searchForRestaurants();
		user2.reserve(1);


		/*
		 * Another user enters a location, searches for nearby restaurants,
		 * and tries to make a reservation
		 */
		user3.enters(bay1);
		user3.searchForRestaurants();
		user3.reserve(2);

		/*
		 * Show the status of objects
		 */
		user1.viewState();
		user2.viewState();
		user3.viewState();
		restaurant1.viewState();
		restaurant2.viewState();
		restaurant3.viewState();

	}



}
