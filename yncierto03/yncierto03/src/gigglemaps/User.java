package gigglemaps;

public class User {
	// attributes
	int id;
	int prefferedRating;
	int guestNum;
	int locCounter = 0;
	int restoCounter = 0;

	float money;

	String username;

	Location[] locVisited;
	Restaurant[] restoVisited;

	final static int MAX_LOCVISITED = 10;
	final static int MAX_RESTOVISITED = 10;

	// constructor
	User(int id, String name, int guestNum, float money, int rating) {
		this.id = id;
		this.username = name;
		this.guestNum = guestNum;
		this.money = money;
		this.prefferedRating = rating;
		this.locVisited = new Location[MAX_LOCVISITED];
		this.restoVisited = new Restaurant[MAX_RESTOVISITED];
	 }

	// methods
	void enters(Location location) {
		this.locVisited[locCounter++] = location; // add location to location visited
		System.out.println("*** " + this.username + " entered location " + location.name + "...");
		System.out.println();
	}

	void searchForRestaurants() {
		System.out.println("*** " + this.username + " is searching for nearby restaurants...");
		if(this.locVisited[this.locCounter-1].restoCounter == 0) { // no restaurant near the location
			System.out.println("No restaurant found near " + this.locVisited[this.locCounter-1].name);
		} else {
			System.out.println("Found " + this.locVisited[this.locCounter-1].restoCounter + " restaurant/s near " + this.locVisited[this.locCounter-1].name  + ":");
			for(int i=0; i<this.locVisited[this.locCounter-1].restoCounter; i++) { // iterate through the restaurants near the location
				System.out.print(i+1 + ". " + this.locVisited[this.locCounter-1].restaurants[i].name + " ");
				if(this.money >= (this.locVisited[this.locCounter-1].restaurants[i].cost*this.guestNum)) { // money fit??
					System.out.print("(fits the budget! go!");
					if(this.locVisited[this.locCounter-1].restaurants[i].capacity < this.guestNum) { // enough capacity??
						System.out.print("... fake news. Hindi kasya");
					}
				}
				if(this.locVisited[this.locCounter-1].restaurants[i].rating < this.prefferedRating) { // rating fit??
					System.out.print(".... restaurant rating falls short");
				}
				System.out.println(")");

//				if(this.money >= (this.locVisited[this.locCounter-1].restaurants[i].cost*this.guestNum)) { // money fit??
//					if(this.locVisited[this.locCounter-1].restaurants[i].capacity >= this.guestNum) { // enough capacity??
//						if(this.locVisited[this.locCounter-1].restaurants[i].rating >= this.prefferedRating) { // rating fit??
//							System.out.println("(fits the budget! go!)");
//						} else {
//							System.out.println("(fits the budget! go!... restaurant rating falls short)");
//						}
//					} else if(this.locVisited[this.locCounter-1].restaurants[i].rating < this.prefferedRating) {
//						System.out.println("(fits the budget! go!... fake news. Hindi kasya.... restaurant rating falls short)");
//					} else {
//						System.out.println("(fits the budget! go!... fake news. Hindi kasya)");
//					}
//				}
			}
			System.out.println();
		}
	}

	void reserve(int id) {
		// index is id-1 since 0 indexing
		System.out.println(this.username + " tries to make reservation for " + this.locVisited[this.locCounter-1].restaurants[id-1].name + "...");
		if(this.locVisited[this.locCounter-1].restaurants[id-1].rating < this.prefferedRating) { // rating fit??
			System.out.println("Making a reservation in spite of the low stars...");
		}
		if(this.money >= (this.locVisited[this.locCounter-1].restaurants[id-1].cost*this.guestNum)) {
			if(this.locVisited[this.locCounter-1].restaurants[id-1].capacity >= this.guestNum) {
				System.out.println("Reservation for " + this.locVisited[this.locCounter-1].restaurants[id-1].name + " successful");
				this.restoVisited[restoCounter++] = this.locVisited[this.locCounter-1].restaurants[id-1]; //add restaurant to user restoVisited
				this.money -= this.locVisited[this.locCounter-1].restaurants[id-1].cost * guestNum; // update user money
				this.locVisited[this.locCounter-1].restaurants[id-1].capacity -= this.guestNum; // update restaurant capacity
				this.locVisited[this.locCounter-1].restaurants[id-1].sales += (this.locVisited[this.locCounter-1].restaurants[id-1].cost * guestNum); // update restaurant sales
				this.locVisited[this.locCounter-1].restaurants[id-1].addReservee(this); // add user to reservee of the restaurant
				System.out.println();
				return;
			}
		}
		System.out.println("Ang kulit, di nga pwede sa " + this.locVisited[this.locCounter-1].restaurants[id-1].name);

//		if(this.money >= (this.locVisited[this.locCounter-1].restaurants[id-1].cost*this.guestNum)) { // money fit??
//			if(this.locVisited[this.locCounter-1].restaurants[id-1].capacity >= this.guestNum) { // enough capacity??
//				if(this.locVisited[this.locCounter-1].restaurants[id-1].rating < this.prefferedRating) { //rating fit??
//					System.out.println("Making a reservation in spite of the low stars...");
//				}
//				System.out.println("Reservation for " + this.locVisited[this.locCounter-1].restaurants[id-1].name + " successful");
//				this.restoVisited[restoCounter++] = this.locVisited[this.locCounter-1].restaurants[id-1]; //add restaurant to user restoVisited
//				this.money -= this.locVisited[this.locCounter-1].restaurants[id-1].cost * guestNum; // update user money
//				this.locVisited[this.locCounter-1].restaurants[id-1].capacity -= this.guestNum; // update restaurant capacity
//				this.locVisited[this.locCounter-1].restaurants[id-1].sales += (this.locVisited[this.locCounter-1].restaurants[id-1].cost * guestNum); // update restaurant sales
//				this.locVisited[this.locCounter-1].restaurants[id-1].addReservee(this); // add user to reservee of the restaurant
//			} else if(this.locVisited[this.locCounter-1].restaurants[id-1].rating < this.prefferedRating) {
//				System.out.println("Making a reservation in spite of the low stars...");
//				System.out.println("Ang kulit, di nga pwede sa " + this.locVisited[this.locCounter-1].restaurants[id-1].name);
//			}
//		}
		System.out.println();
	}

	void viewState() {
		System.out.println("------------ USER -------------");
		System.out.println("ID: " + this.id);
		System.out.println("Name: " + this.username);
		System.out.println("No. of guests: " + this.guestNum);
		System.out.println("Wallet amount: " + this.money);
		System.out.print("Current Location: ");
		if(this.locCounter == 0) {
			System.out.println("none");
		} else {
			System.out.println(this.locVisited[this.locCounter-1].name);
		}
		System.out.print("Location/s visited: ");
		if(this.locCounter == 0) {
			System.out.println("No entry yet.");
		} else if(this.locCounter == 1) {
			System.out.println(this.locVisited[this.locCounter-1].name);
		} else {
			System.out.println();
			for(int i = 0; i<this.locCounter; i++) {
				System.out.println("\t" + (i+1) + ". " + this.locVisited[i].name);
			}
		}
		System.out.print("Restaurants successfully reserved: ");
		if(this.restoCounter == 0) {
			System.out.println("none");
		} else if (this.restoCounter == 1) {
			System.out.println(this.restoVisited[restoCounter-1].name);
		}  else {
			for(int i = 0; i<this.restoCounter; i++) {
				System.out.println(i+1 + ". " + this.restoVisited[i].name);
			}
		}
		System.out.print("Restaurant currently occupied: " );
		if(restoCounter == 0) {
			System.out.println("none");
		} else {
			System.out.println(this.restoVisited[this.restoCounter-1].name);
		}
		System.out.println("\n");

	}


}
